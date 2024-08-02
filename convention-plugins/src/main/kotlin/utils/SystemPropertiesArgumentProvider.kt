/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package utils

import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.process.CommandLineArgumentProvider

/**
 * A class that provides system properties as command line arguments.
 *
 * @property properties A provider of a map of system properties, where the key is the property name and the value is
 *   the property value.
 */
class SystemPropertiesArgumentProvider(
    private val properties: Provider<Map<String, String?>>,
) : CommandLineArgumentProvider {

    /**
     * Provides the input properties as a map, where the value is a boolean indicating whether the property is null.
     * This is used for incremental build analysis to track the presence of properties.
     */
    @Suppress("unused")
    @get:Input
    val inputs: Provider<Map<String, Boolean>>
        get() = properties.map { props -> props.mapValues { (_, v) -> v == null } }

    /**
     * Converts the properties into command line arguments.
     *
     * @return An iterable of command line arguments in the form of "-Dkey=value".
     */
    override fun asArguments(): Iterable<String> {
        // Retrieve the properties map, defaulting to an empty map if the provider is not present.
        val properties = properties.getOrElse(emptyMap())
            // Filter out properties with null values and convert each entry to a command line argument.
        return properties
            .filterValues { v -> v != null }
            .map { (k, v) -> "-D$k=$v" }
    }

    companion object {
        /**
         * Factory method to create a [systemPropertiesArgumentProvider] from a provider of a key-value pair.
         *
         * @param keyValue A provider of a pair of strings representing a system property key and its value.
         * @return A new instance of [systemPropertiesArgumentProvider] with the given property.
         */
        fun systemPropertiesArgumentProvider(keyValue: Provider<Pair<String, String?>>) =
            SystemPropertiesArgumentProvider(keyValue.map { mapOf(it) })
    }
}
