/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

rootProject.name = "strait-jakt"

pluginManagement {
   repositories {
      mavenCentral()
      gradlePluginPortal()
   }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
   repositoriesMode = RepositoriesMode.PREFER_SETTINGS

   repositories {
      mavenCentral()
      maven("https://oss.sonatype.org/content/repositories/snapshots/") {
         name = "SonatypeSnapshots"
         mavenContent { snapshotsOnly() }
      }

      //region Declare the Node.js & Yarn download repositories
      // Workaround https://youtrack.jetbrains.com/issue/KT-68533/
      ivy("https://nodejs.org/dist/") {
         name = "Node Distributions at $url"
         patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
         metadataSources { artifact() }
         content { includeModule("org.nodejs", "node") }
      }
      ivy("https://github.com/yarnpkg/yarn/releases/download") {
         name = "Yarn Distributions at $url"
         patternLayout { artifact("v[revision]/[artifact](-v[revision]).[ext]") }
         metadataSources { artifact() }
         content { includeModule("com.yarnpkg", "yarn") }
      }
      //endregion

      mavenLocal()
   }
}

plugins {
   id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include(":jakt")
