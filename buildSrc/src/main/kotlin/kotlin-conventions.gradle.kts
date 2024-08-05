/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import utils.SystemPropertiesArgumentProvider

plugins {
   id("jakt-base")
   kotlin("multiplatform")
}

tasks.withType<Test>().configureEach {
   useJUnitPlatform()
}

kotlin {
   @OptIn(ExperimentalKotlinGradlePluginApi::class)
   compilerOptions {
      freeCompilerArgs.add("-Xexpect-actual-classes")
   }
   sourceSets.configureEach {
      languageSettings {
         optIn("cl.ravenhill.jakt.ExperimentalJakt")
      }
   }
}
