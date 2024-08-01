/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

plugins {
    alias(libs.plugins.kotlinMultiplatform)
//    alias(libs.plugins.androidLibrary)
    id("module.publication")
}

kotlin {
    jvm()
//    androidTarget {
//        publishLibraryVariants("release")
//        @OptIn(ExperimentalKotlinGradlePluginApi::class)
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_1_8)
//        }
//    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.framework.datatest)
                implementation(libs.kotest.property)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(libs.kotest.runner.junit5)
            }
        }
    }
}

//android {
//    namespace = "cl.ravenhill.jakt"
//    compileSdk = libs.versions.android.compileSdk.get().toInt()
//    defaultConfig {
//        minSdk = libs.versions.android.minSdk.get().toInt()
//    }
//}
