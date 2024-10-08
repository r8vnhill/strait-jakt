/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions

/**
 * Represents an exception that aggregates multiple individual exceptions into a single composite exception.
 *
 * Often used in scenarios where multiple operations can fail simultaneously, and there's a need to
 * capture all these failures rather than halting on the first one. Each underlying failure is represented
 * by a `Throwable` instance.
 *
 * ## Example Usage:
 *
 * Suppose you are running multiple asynchronous tasks and wish to capture any failures that might arise from
 * them without stopping the entire batch of tasks. On completion, you can aggregate any failures and throw
 * a single `CompositeException` to represent all of them.
 *
 * ```kotlin
 * val failures = listOf(
 *     Exception("Network error"),
 *     Exception("Database connection failed"),
 *     Exception("Timeout occurred")
 * )
 * if (failures.isNotEmpty()) {
 *     throw CompositeException(failures)
 * }
 * ```
 *
 * The resulting exception message will be:
 * `Multiple exceptions occurred: [{ Network error }, { Database connection failed }, { Timeout occurred }]`
 *
 * @property throwables A list of `Throwable` instances, each representing an individual failure or exception.
 *
 * @constructor Constructs an instance of `CompositeException` with the provided list of [throwables].
 */
class CompositeException(val throwables: List<Throwable>) : Exception(
    if (throwables.size == 1)
        "An exception occurred -- [${throwables[0]::class.simpleName}] ${throwables[0].message}"
    else
        "Multiple exceptions occurred -- " +
              throwables.joinToString(",\n") { "{ [${it::class.simpleName}] ${it.message} }" }
) {
    init {
        require(throwables.isNotEmpty()) { "The list of throwables cannot be empty" }
    }
}
