/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

import utils.ReleaseVersion

plugins {
    id("jakt-base")
    `maven-publish`
    signing
    id("dev.adamko.dev-publish")
}

val ossrhUsername: String by project
val ossrhPassword: String by project
val signingKey: String? by project
val signingPassword: String? by project

val releaseVersion = ReleaseVersion(versionCatalogs.named("libs"))

val mavenCentralRepoName = "Deploy"

signing {
    // Check if the signing key and signing password are not null or blank.
    // If both are provided, configure the signing process.
    if (!signingKey.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
        // Use the GPG command line tool for signing.
        useGpgCmd()
        // Use the provided signing key and password for in-memory PGP keys.
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
    // Sign all publications defined in the 'publishing' block.
    sign(publishing.publications)
    // Set the signing requirement based on whether it is a release version.
    // Signing is only required when the version is a release version.
    setRequired { releaseVersion.isRelease }
}

gradle.taskGraph.whenReady {
    // Determine if any of the tasks in the task graph are publishing to Maven Central.
    val isPublishingToMavenCentral = allTasks
        // Filter tasks that are instances of PublishToMavenRepository.
        .filterIsInstance<PublishToMavenRepository>()
        // Check if any of these tasks are targeting the Maven Central repository.
        .any { it.repository?.name == mavenCentralRepoName }

    // Set the signing requirement based on whether publishing to Maven Central is occurring.
    signing.setRequired({ isPublishingToMavenCentral })

    // Configure all Sign tasks.
    tasks.withType<Sign>().configureEach {
        // Redefine the variable for Config Cache compatibility.
        val isPublishingToMavenCentralRedefined = isPublishingToMavenCentral

        // Add the isPublishingToMavenCentral property to the inputs for Config Cache compatibility.
        inputs.property("isPublishingToMavenCentral", isPublishingToMavenCentralRedefined)

        // Configure the Sign tasks to only run if publishing to Maven Central.
        onlyIf("publishing to Maven Central") { isPublishingToMavenCentralRedefined }
    }
}

publishing {
    repositories {
        maven {
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            name = mavenCentralRepoName
            url = if (releaseVersion.isRelease) releasesRepoUrl else snapshotsRepoUrl
            credentials {
                username = System.getenv("OSSRH_USERNAME") ?: ossrhUsername
                password = System.getenv("OSSRH_PASSWORD") ?: ossrhPassword
            }
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

pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
    val javadocJar by tasks.registering(Jar::class) {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        description = "Create Javadoc JAR"
        archiveClassifier.set("javadoc")
    }

    publishing.publications.withType<MavenPublication>().configureEach {
        artifact(javadocJar)
    }

    
}