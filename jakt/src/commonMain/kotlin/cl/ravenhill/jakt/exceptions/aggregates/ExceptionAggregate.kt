/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions.aggregates

import cl.ravenhill.jakt.Jakt

/**
 * Represents an aggregate exception containing multiple causes.
 *
 * The `ExceptionAggregate` class is used to encapsulate multiple exceptions within a single structure, providing a
 * unified message that details each exception type and message. Additionally, the message can include stack traces for
 * each exception if `Jakt.includeStackTrace` is enabled.
 *
 * This class is intended for use in scenarios where multiple exceptions occur independently but need to be reported
 * collectively.
 *
 * ## Usage:
 * Use this class to create a message aggregating multiple exceptions, optionally including stack traces based on the
 * `Jakt.includeStackTrace` setting.
 *
 * ### Example 1: Creating a Message with Multiple Exceptions
 * ```kotlin
 * val exceptions = listOf(
 *     IllegalArgumentException("Invalid argument"),
 *     IllegalStateException("Invalid state")
 * )
 * val message = ExceptionAggregate.createMessage(exceptions)
 * println(message)
 * // Prints: Multiple exceptions occurred -- { [IllegalArgumentException] Invalid argument },
 * //         { [IllegalStateException] Invalid state }
 *
 * // If `Jakt.includeStackTrace` is true, it appends stack traces for each exception.
 * ```
 *
 * @param message The message describing the aggregated exceptions.
 */
sealed class ExceptionAggregate(message: String) {

    companion object {
        /**
         * Creates a formatted message listing multiple exceptions, with optional stack traces.
         *
         * @param causes A list of exceptions to be aggregated in the message.
         * @return A formatted message containing each exception type and message. If `Jakt.includeStackTrace`
         * is `true`, stack traces are appended for each exception.
         */
        internal fun createMessage(causes: List<Throwable>): String =
            "Multiple exceptions occurred -- " +
                    causes.joinToString(",\n") { "{ [${it::class.simpleName}] ${it.message} }" } +
                    if (Jakt.includeStackTrace) causes.joinToString("\n") { it.stackTraceToString() } else ""
    }
}
