import org.jetbrains.dokka.gradle.DokkaTask

val kotestVersion = extra["kotest.version"] as String
val dokkaVersion = extra["dokka.version"] as String
val detektVersion = extra["detekt.version"] as String

plugins {
    `maven-publish`
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    kotlin("jvm")
    signing
}

group = "cl.ravenhill"
version = "1.3.0"
val projectVersion = version.toString()

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:$dokkaVersion")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<DokkaTask>().configureEach {
    outputDirectory.set(layout.buildDirectory.dir("dokka/html"))
}

val dokkaHtml by tasks.getting(DokkaTask::class)

val dokkaJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

kotlin {
    jvmToolchain(17)
}

detekt {
    // Configures the detekt task to use the default detekt configuration
    config.from(files("conf/detekt.yml"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group as String
            artifactId = "strait-jakt"
            description = "A Kotlin data validation library."
            from(components["kotlin"])
            artifact(dokkaJar)
            artifact(sourcesJar)
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
                scm {
                    url.set("https://github.com/r8vnhill/strait-jakt")
                    connection.set("scm:git:git://github.com/r8vnhill/strait-jakt.git")
                    developerConnection.set("scm:git:ssh://github.com/r8vnhill/strait-jakt.git")
                }
                developers {
                    developer {
                        id.set("r8vnhill")
                        name.set("Ignacio Slater M.")
                        email.set("reachme@ravenhill.cl")
                    }
                }
            }
        }
    }

    if (!projectVersion.endsWith("SNAPSHOT")) {
        signing {
            useGpgCmd()
            sign(publications)
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = if (projectVersion.endsWith("SNAPSHOT")) {
                uri("https://oss.sonatype.org/content/repositories/snapshots")
            } else {
                uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
            credentials {
                username = if (System.getProperty("os.name").startsWith("Windows")) {
                    System.getenv("SonatypeUsername")
                } else {
                    System.getProperty("SONATYPE_USERNAME")
                }.apply {
                    check(!isNullOrEmpty()) { "Sonatype username not found." }
                    password = if (System.getProperty("os.name").startsWith("Windows")) {
                        System.getenv("SonatypePassword")
                    } else {
                        System.getProperty("SONATYPE_PASSWORD")
                    }.apply { check(!isNullOrEmpty()) { "Sonatype password not found." } }
                }
            }
        }
    }
}
