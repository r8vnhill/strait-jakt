/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyTemplate

plugins {
    id("kotlin-conventions")
}

kotlin {
    // FIXME: .konan\dependencies\msys2-mingw-w64-x86_64-2\bin\ld.gold invocation reported errors on Windows
    // This section is commented out due to issues with the linker on Windows,
    // which reports missing files when trying to compile for Linux targets.
    // Uncomment the following lines once the issue is resolved.
    // linuxX64()
    // linuxArm64()

    // Configure the Kotlin Multiplatform project for the MinGW (Windows) target.
    mingwX64()

    // Configure the Kotlin Multiplatform project for macOS targets.
    macosX64()
    macosArm64()

    // Configure the Kotlin Multiplatform project for tvOS targets.
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()

    // Configure the Kotlin Multiplatform project for watchOS targets.
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()

    // Configure the Kotlin Multiplatform project for iOS targets.
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Apply a hierarchy template for organizing source sets and targets.
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        // Define a group named "common" for common configurations.
        group("common") {
            // Define a subgroup named "native" for native targets.
            group("native") {
                // Include all native targets in the native group.
                withNative()
            }
        }
    }
}
