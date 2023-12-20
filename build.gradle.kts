val kotestVersion = extra["kotest.version"] as String
val dokkaVersion = extra["dokka.version"] as String
val detektVersion = extra["detekt.version"] as String

plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
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
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:$dokkaVersion")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
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
