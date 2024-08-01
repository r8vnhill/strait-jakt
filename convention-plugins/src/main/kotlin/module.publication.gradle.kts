/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

val jaktVersion = extra["jakt.version"] as String

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
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

    if (!jaktVersion.endsWith("SNAPSHOT")) {
        signing {
            useGpgCmd()
            sign(publications)
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = if (project.version.toString().endsWith("SNAPSHOT")) {
                uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            } else {
                uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
            credentials {
                username = if (System.getProperty("os.name").startsWith("Windows")) {
                    System.getenv("SonatypeUsername")
                } else {
                    System.getenv("SONATYPE_USERNAME")
                }.apply { check(!isNullOrEmpty()) { "Sonatype username not found." } }
                password = if (System.getProperty("os.name").startsWith("Windows")) {
                    System.getenv("SonatypePassword")
                } else {
                    System.getenv("SONATYPE_PASSWORD")
                }.apply { check(!isNullOrEmpty()) { "Sonatype password not found." } }
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
