plugins {
    kotlin("jvm") version "1.9.20"
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("org.jetbrains.dokka") version "1.8.20"
    `maven-publish`
    signing
    `java-library`
}

group = "cl.ravenhill"
version = "1.2.1"
val projectVersion = version.toString()

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.kotest:kotest-property:5.7.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    // region : -== DOKKA ==-
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.8.20")
    // endregion DOKKA

    // region : -== DETEKT ==-
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")
    // endregion DETEKT
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

detekt {
    // Configures the detekt task to use the default detekt configuration
    config.from(files("conf/detekt.yml"))
}

java {
    // Generates a Javadoc jar file containing the Javadoc for this project's public API
    withJavadocJar()
    // Generates a sources jar file containing the sources for this project
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            groupId = "cl.ravenhill"
            artifactId = "strait-jakt"
            description = "A Kotlin library for validating values."
        }
        withType<MavenPublication> {
            pom {
                packaging = "jar"
                name.set("strait-jakt")
                description.set("StraitJackt")
                url.set("https://github.com/r8vnhill/strait-jakt")
                inceptionYear.set("2023")
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
                        email.set("reachme@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:r8vnhill/strait-jakt.git")
                    developerConnection.set("scm:git:ssh:git@github.com:r8vnhill/strait-jakt.git")
                    url.set("https://github.com/r8vnhill/strait-jakt")
                }
            }
        }
    }
}
