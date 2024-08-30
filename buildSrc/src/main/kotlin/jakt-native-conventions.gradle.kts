import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinHierarchyTemplate


plugins {
        id("kotlin-conventions")
    }
/**
 * Checks if the current operating system is Linux.
 *
 * This function determines whether the operating system on which the JVM is running is Linux by checking the `os.name` system property.
 *
 * ## Usage:
 * Use this function to conditionally execute code based on the operating system.
 *
 * ### Example 1: Using isLinux Function
 *
 * ```kotlin
 * if (isLinux()) {
 *     println("Running on Linux")
 * } else {
 *     println("Not running on Linux")
 * }
 * ```
 *
 * @return `true` if the current operating system is Linux, `false` otherwise.
 */
fun isLinux(): Boolean {
    return System.getProperty("os.name").contains("Linux", ignoreCase = true)
}

/**
 * Configures Kotlin Multiplatform targets based on the current operating system.
 *
 * This block sets up the appropriate targets for various platforms, including Linux, Windows, macOS, tvOS, watchOS, and iOS.
 * The `isLinux` function is used to conditionally include Linux targets.
 */
kotlin {
    // Conditionally include Linux targets if the operating system is Linux
    if (isLinux()) {
        linuxX64()
        linuxArm64()
    }

    // Include Windows target
    mingwX64()

    // Include macOS targets
    macosX64()
    macosArm64()

    // Include tvOS targets
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()

    // Include watchOS targets
    watchosArm32()
    watchosArm64()
    watchosX64()
    watchosSimulatorArm64()

    // Include iOS targets
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Apply default hierarchy template for common and desktop targets
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        group("common") {
            group("desktop") {
                withNative()
            }
        }
    }
}
