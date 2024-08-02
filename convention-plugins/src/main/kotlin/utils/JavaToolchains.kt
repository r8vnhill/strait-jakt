/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package utils

import org.gradle.api.provider.Provider
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

/**
 * Converts a [JavaLanguageVersion] to a corresponding [JvmTarget].
 *
 * @return The [JvmTarget] corresponding to the Java language version.
 */
internal fun JavaLanguageVersion.jvmTarget(): JvmTarget {
    val version = asInt()
    // For Java versions <= 8, the target is formatted as "1.x".
    // For Java versions > 8, the target is simply the version number.
    val target = if (version <= 8) "1.$version" else "$version"
    return JvmTarget.fromTarget(target)
}

/**
 * Converts a [Provider] of [JavaLanguageVersion] to a [Provider] of [JvmTarget].
 *
 * @return A provider of the corresponding [JvmTarget].
 */
internal fun Provider<JavaLanguageVersion>.jvmTarget(): Provider<JvmTarget> =
    map { it.jvmTarget() }

/**
 * Converts a [Provider] of [JavaLanguageVersion] to a [Provider] of [Int].
 *
 * @return A provider of the corresponding integer representation of the Java language version.
 */
internal fun Provider<JavaLanguageVersion>.asInt(): Provider<Int> =
    map { it.asInt() }

/**
 * Configures the Kotlin JVM compiler options to use a specific JDK release.
 *
 * @param version A provider of the [JavaLanguageVersion] to set as the JDK release.
 */
internal fun KotlinJvmCompilerOptions.jdkRelease(version: Provider<JavaLanguageVersion>) {
    // Adds a compiler argument to specify the JDK release version.
    // The argument is formatted as "-Xjdk-release=<target>".
    freeCompilerArgs.add(version.map {
        @Suppress("SpellCheckingInspection")
        "-Xjdk-release=${it.jvmTarget().target}"
    })
}
