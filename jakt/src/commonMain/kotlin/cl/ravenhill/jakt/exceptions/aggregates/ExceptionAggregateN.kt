/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions.aggregates

/**
 * Represents an aggregate exception containing multiple causes.
 *
 * The `ExceptionAggregateN` class encapsulates multiple exceptions of a generic type `T` within the
 * `ExceptionAggregate` structure, providing a single message that includes the type and message of each exception. This
 * class is useful for cases where multiple independent exceptions of the same type occur and need to be reported
 * collectively.
 *
 * The aggregated message is created using the `createMessage` function from `ExceptionAggregate`, which formats the
 * message to list each exception in a consistent format.
 *
 * ## Usage:
 * Use this class to aggregate multiple exceptions, maintaining detailed information about each cause in the generated
 * message.
 *
 * ### Example 1: Using ExceptionAggregateN with Multiple Exceptions
 * ```kotlin
 * val cause1 = IllegalArgumentException("Invalid argument")
 * val cause2 = IllegalStateException("Invalid state")
 * val cause3 = RuntimeException("General error")
 * val exception = ExceptionAggregateN(cause1, cause2, cause3)
 * println(exception.message)
 * // Prints: Multiple exceptions occurred -- { [IllegalArgumentException] Invalid argument },
 * //         { [IllegalStateException] Invalid state }, { [RuntimeException] General error }
 * ```
 *
 * @param T The type of the exceptions to be aggregated, constrained to `Throwable`.
 * @param cause1 The first exception to be included in the aggregate.
 * @param causes Additional exceptions to be included in the aggregate.
 */
class ExceptionAggregateN<T>(cause1: T, vararg causes: T) : ExceptionAggregate(createMessage(listOf(cause1, *causes)))
        where T : Throwable
