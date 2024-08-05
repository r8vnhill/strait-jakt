import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.common
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.jvm
import utils.ReleaseVersion
import utils.publishPlatformArtifactsInRootModule
import java.util.*

plugins {
    id("jakt-base")
    signing
    `maven-publish`
    id("dev.adamko.dev-publish")
}

// Define the release version using the version catalog named "libs".
val releaseVersion = ReleaseVersion(versionCatalogs.named("libs"))

// Set the group ID and version for the project.
group = "cl.ravenhill"
version = releaseVersion.jaktVersion

val properties = Properties().apply {
    val file = file("local-secrets.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}

// Retrieve sensitive information (credentials) from the project properties.
val ossrhUsername: String? = properties.getProperty("osshr.user")
val ossrhPassword: String? = properties.getProperty("osshr.password")

// Define the Maven Central repository name used for publishing.
val mavenCentralRepoName = "Deploy"

// Configure the signing task for the project.
signing {
    useGpgCmd()
}

// Configure the Gradle task graph to determine if publishing to Maven Central.
gradle.taskGraph.whenReady {
    // Check if any tasks of type PublishToMavenRepository are targeting Maven Central.
    val isPublishingToMavenCentral = allTasks
        .filterIsInstance<PublishToMavenRepository>()
        .any { it.repository?.name == mavenCentralRepoName }

    // Set signing as required only if publishing to Maven Central.
    signing.setRequired { isPublishingToMavenCentral }

    // Configure each Sign task to only execute if publishing to Maven Central.
    tasks.withType<Sign>().configureEach {
        // Redefine the variable for Config Cache compatibility.
        val publishingToMavenCentralFlag = isPublishingToMavenCentral
        inputs.property("isPublishingToMavenCentral", publishingToMavenCentralFlag)
        // Configure the Sign task to only run if publishing to Maven Central.
//      onlyIf("publishing to Maven Central") { publishingToMavenCentralFlag }
    }
}

publishing {
    repositories {
        // Configure the Maven Central repository for publishing.
        maven {
            // Define URLs for release and snapshot repositories.
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

            // Set the repository name.
            name = mavenCentralRepoName

            // Select the repository URL based on whether it's a release version or not.
            url = if (releaseVersion.isRelease) releasesRepoUrl else snapshotsRepoUrl

            // Configure credentials for publishing.
            credentials {
                // Retrieve credentials from environment variables if available, otherwise use project properties.
                username = System.getenv("OSSRH_USERNAME") ?: ossrhUsername
                password = System.getenv("OSSRH_PASSWORD") ?: ossrhPassword
            }
        }

        // Configure a local Maven repository for publishing artifacts within the project.
        maven(rootDir.resolve("build/maven-repo")) {
            // Name the local repository.
            name = "RootBuildDir"

            // Instructions for using the local repository:
            // To publish to this local repository, run: ./gradlew publishAllPublicationsToRootBuildDirRepository
            // After publishing, check the directory $rootDir/build/maven-repo/ to verify the published artifacts.
        }
    }

    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("StraitJakt")
            description.set("A Kotlin data validation library.")
            url.set("https://github.com/r8vnhill/strait-jakt")

            licenses {
                license {
                    name.set("BSD 2-Clause License")
                    url.set("https://opensource.org/licenses/BSD-2-Clause")
                }
            }
            developers {
                developer {
                    id.set("r8vnhill")
                    name.set("Ignacio Slater M.")
                    email.set("reachme@ravenhill.cl")
                }
            }
            scm {
                url.set("https://github.com/r8vnhill/strait-jakt")
                connection.set("scm:git:git://github.com/r8vnhill/strait-jakt.git")
                developerConnection.set("scm:git:ssh://github.com/r8vnhill/strait-jakt.git")
            }
        }
    }
}

// Apply configuration when the Kotlin Multiplatform plugin is applied
pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
    // Register a task to create a Javadoc JAR
    val javadocJar by tasks.registering(Jar::class) {
        group = JavaBasePlugin.DOCUMENTATION_GROUP // Categorize the task under documentation
        description = "Create Javadoc JAR" // Description of the task
        archiveClassifier.set("javadoc") // Set classifier to "javadoc" for the JAR
    }

    // Configure Maven publications to include the Javadoc JAR as an artifact
    publishing.publications.withType<MavenPublication>().configureEach {
        artifact(javadocJar)
    }

    // Ensure that platform-specific artifacts are published from the root module
    publishPlatformArtifactsInRootModule(project.tasks, project.logger)
}

// Apply configuration when the Java Gradle Plugin is applied
pluginManager.withPlugin("java-gradle-plugin") {
    // Configure the Java plugin extension to include a sources JAR
    extensions.configure<JavaPluginExtension> {
        withSourcesJar()
    }
}

// Define a BuildService to limit the number of parallel uploads to Maven Central
abstract class MavenPublishLimiter : BuildService<BuildServiceParameters.None>

val mavenPublishLimiter = gradle.sharedServices.registerIfAbsent("mavenPublishLimiter", MavenPublishLimiter::class) {
    maxParallelUsages = 1 // Limit to one parallel usage to avoid issues with Maven Central
}

// Configure tasks of type PublishToMavenRepository that are targeted at Maven Central
tasks.withType<PublishToMavenRepository>()
    .matching { it.name.endsWith("PublicationTo${mavenCentralRepoName}Repository") }
    .configureEach {
        usesService(mavenPublishLimiter) // Use the MavenPublishLimiter service to enforce parallel upload limits
    }

/**
 * Create a service for collecting the coordinates of all Jakt artifacts that should be included in the `jakt-bom`.
 */
abstract class JaktBomService : BuildService<BuildServiceParameters.None> {
    /** Coordinates that will be included in the Jakt BOM. */
    abstract val coordinates: SetProperty<String>
}

// Register the Jakt BOM service if it doesn't already exist
val kotestBomService: JaktBomService =
    gradle.sharedServices.registerIfAbsent("kotestBomService", JaktBomService::class).get()

// Add the Jakt BOM service to the project's extensions
extensions.add("kotestBomService", kotestBomService)

/** Controls whether the current subproject will be included in the Jakt BOM. */
val includeInJaktBom: Property<Boolean> =
    objects.property<Boolean>().convention(project.name != "kotest-bom")

// Add the includeInJaktBom property to the project's extensions
extensions.add<Property<Boolean>>("includeInJaktBom", includeInJaktBom)

// Configure Kotlin Multiplatform plugin to add artifact coordinates to the Jakt BOM service
pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
    extensions.configure<KotlinMultiplatformExtension> {
        // Filter targets to include only those that are publishable and not platform-specific
        targets
            .matching { target ->
                target.publishable &&
                        // Skip platform artifacts (like *-linuxx64, *-macosx64) to avoid inconsistency
                        // Platform-specific artifacts should be excluded because they can cause issues
                        // when publishing from different platforms (e.g., Linux vs. macOS)
                        // Consumers typically use the generic *-native artifact, and Gradle will
                        // choose the correct variant based on metadata attributes
                        (target.platformType == common || target.platformType == jvm)
            }
            .all {
                mavenPublication publication@{
                    // Add coordinates of the publication to the Jakt BOM service if the project is included
                    kotestBomService.coordinates.addAll(
                        providers
                            .provider {
                                "${this@publication.groupId}:${this@publication.artifactId}:${this@publication.version}"
                            }
                            .zip(includeInJaktBom) { coords, enabled ->
                                if (enabled) listOf(coords) else emptyList()
                            }
                    )
                }
            }
    }
}

// Fix Gradle error: Task <publish> uses this output of task <sign> without declaring an explicit or implicit dependency.
// See https://github.com/gradle/gradle/issues/26091 for details
tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks) // Ensure that the publish task runs after the sign task
}
