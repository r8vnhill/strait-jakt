/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

import org.gradle.api.tasks.testing.Test

// Apply the required plugins to the project.
// The `jakt-base` plugin is a custom plugin specific to this project.
// The `kotlin("multiplatform")` plugin is used to enable Kotlin Multiplatform support, allowing the project to target
// multiple platforms (e.g., JVM, JavaScript, Native).
plugins {
    id("jakt-base")
    kotlin("multiplatform")
}

// Configure all tasks of type Test.
// This block specifies that the JUnit Platform should be used for running tests, which allows for running tests written
// with JUnit 5.
tasks.withType<Test> {
    useJUnitPlatform()
}

// Configure the Kotlin Multiplatform project.
// The `sourceSets.configureEach` block applies configurations to all source sets in the project.
// The `languageSettings` block within each source set is used to set language-specific settings.
kotlin {
    sourceSets.configureEach {
        languageSettings {
            // Enable the use of experimental features in the `cl.ravenhill.jakt` package by opting into the
            // `ExperimentalJakt` annotation.
            optIn("cl.ravenhill.jakt.ExperimentalJakt")
        }
    }
}
