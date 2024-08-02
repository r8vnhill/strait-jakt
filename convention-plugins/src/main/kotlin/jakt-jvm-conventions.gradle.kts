/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */
import org.jetbrains.kotlin.gradle.plugin.KotlinTargetWithTests
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import utils.SystemPropertiesArgumentProvider
import utils.asInt
import utils.jdkRelease
import utils.jvmTarget
import kotlin.jvm.optionals.getOrElse


plugins {
    id("kotlin-conventions")
}

// Retrieve the version catalog named "libs".
// A version catalog is a central place to define versions and dependencies that can be shared across different parts of
// the build.
private val libs: VersionCatalog = versionCatalogs.named("libs")

kotlin {
    // Configuring the JVM target with Java interoperability
    jvm {
        withJava()
    }

    // Configuring source sets
    sourceSets {
        // Getting the JVM test source set and adding dependencies
        getByName("jvmTest") {
            dependencies {
                // Adding the Kotlin reflect library as an implementation dependency
                implementation(kotlin("reflect"))
                // Adding the Kotest JUnit 5 runner as an implementation dependency
                implementation(
                    libs.findLibrary("kotest.runner.junit5")
                        .getOrElse { error("Missing Kotest JUnit 5 runner dependency") })
            }
        }
    }
}

// Extension function to find and provide a JavaLanguageVersion from the version catalog.
// The function takes a version name as a parameter and returns a provider for the JavaLanguageVersion.
private fun VersionCatalog.findJvmVersion(name: String): Provider<JavaLanguageVersion> = provider {
    // Attempt to find the version in the version catalog by name.
    val version = findVersion(name)
        .getOrElse {
            // If the version is not found, throw an error with a descriptive message.
            error("Missing '$name' version in libs.versions.toml")
        }
    // Return a JavaLanguageVersion based on the required version found in the catalog.
    JavaLanguageVersion.of(version.requiredVersion)
}

// Define the minimum Java version that Jakt supports.
private val jvmMinTargetVersion = libs.findJvmVersion("jvmMinTarget")

// Define the maximum Java version that Jakt supports.
private val jvmMaxTargetVersion = libs.findJvmVersion("jvmMaxTarget")

// Define the Java version used for compilation.
private val jvmCompilerVersion = libs.findJvmVersion("jvmCompiler")

// Configure the Java compiler language version based on the jvmCompilerVersion.
kotlin {
    jvmToolchain {
        languageVersion = jvmCompilerVersion
    }
}

// Configure all tasks of type KotlinJvmCompile.
// This block sets the JDK release and JVM target version for Kotlin compilation.
tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        // Set the JDK release version using the jvmMinTargetVersion provider.
        jdkRelease(jvmMinTargetVersion)
        // Set the JVM target version using the jvmMinTargetVersion provider.
        jvmTarget = jvmMinTargetVersion.jvmTarget()
    }
}

// Configure all tasks of type JavaCompile.
// This block sets the JDK release version for Java compilation.
tasks.withType<JavaCompile>().configureEach {
    // Set the JDK release version using the jvmMinTargetVersion provider converted to an integer.
    options.release = jvmMinTargetVersion.asInt()
}

// Configure all tasks of type Test.
// This block adds a system properties argument provider to the test tasks.
tasks.withType<Test>().configureEach {
    // Add a SystemPropertiesArgumentProvider to the jvmArgumentProviders list.
    jvmArgumentProviders.add(SystemPropertiesArgumentProvider.systemPropertiesArgumentProvider(
        // Map the javaLauncher provider to a key-value pair of "testJavaLauncherVersion" and the Java language version
        // as a string.
        javaLauncher.map { "testJavaLauncherVersion" to it.metadata.languageVersion.asInt().toString() }
    ))
}

kotlin {
    jvm {
        // Configure the default test run for the JVM target.
        testRuns.named(KotlinTargetWithTests.DEFAULT_TEST_RUN_NAME) {
            executionTask.configure {
                // Set the Java launcher for the default test run to use the minimum target JVM version.
                javaLauncher = javaToolchains.launcherFor { languageVersion = jvmMinTargetVersion }
            }
        }

        // Create a new test run configuration named "maxJdk".
        val maxJdk by testRuns.creating {
            executionTask.configure {
                // Set the Java launcher for the maxJdk test run to use the maximum target JVM version.
                javaLauncher = javaToolchains.launcherFor { languageVersion = jvmMaxTargetVersion }
            }
        }

        // Ensure that the "check" task depends on the execution of the maxJdk test run.
        // This ensures that tests will be run with both the minimum and maximum target JVM versions.
        tasks.check {
            dependsOn(maxJdk.executionTask)
        }
    }
}
