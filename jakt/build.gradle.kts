/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */
plugins {
   id("jakt-jvm-conventions")
   id("jakt-js-conventions")
   id("jakt-native-conventions")
   id("jakt-publishing-conventions")
   id("jakt-watchos-device-conventions")
}

/**
 * Configures Kotlin Multiplatform source sets and their dependencies.
 *
 * This block sets up the `commonMain` and `commonTest` source sets, specifying the dependencies required for each.
 */
kotlin {
   sourceSets {
      // Configure the commonMain source set directly
      getByName("commonMain") {
         dependencies {
            // Add Kotlin reflection library to the commonMain source set
            implementation(kotlin("reflect"))
         }
      }

      // Configure the commonTest source set directly
      getByName("commonTest") {
         dependencies {
            // Add Kotlin reflection library to the commonTest source set
            implementation(kotlin("reflect"))

            // Add Kotest libraries to the commonTest source set
            implementation(libs.kotest.assertions.core)
            implementation(libs.kotest.framework.engine)
            implementation(libs.kotest.framework.datatest)
            implementation(libs.kotest.property)
         }
      }
   }
}
