import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    id("kotlin-conventions")
}

kotlin {
    watchosDeviceArm64()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        group("common") {
            group("desktop") {
                withNative()
            }
        }
    }
}
