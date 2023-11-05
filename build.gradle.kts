plugins {
    kotlin("jvm") version "1.9.20"
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("org.jetbrains.dokka") version "1.8.20"
}

group = "cl.ravenhill"
version = "1.0-SNAPSHOT"

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