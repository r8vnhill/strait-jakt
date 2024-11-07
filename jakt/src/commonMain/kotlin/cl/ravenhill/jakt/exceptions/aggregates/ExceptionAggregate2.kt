/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions.aggregates


/**
 * Represents an aggregate exception containing two generic causes.
 *
 * The `ExceptionAggregate2` class encapsulates two exceptions of generic types `T1` and `T2` within the
 * `ExceptionAggregate` structure, providing a single message that includes both exception types and messages. This
 * class is useful when two independent exceptions of potentially different types occur and need to be reported
 * together.
 *
 * The aggregated message is created using the `createMessage` function from `ExceptionAggregate`, which formats the
 * message to list both exceptions in a consistent format.
 *
 * ## Usage:
 * Use this class to aggregate two exceptions, maintaining detailed information about each cause in the generated
 * message.
 *
 * ### Example 1: Using ExceptionAggregate2 with Different Exception Types
 * ```kotlin
 * val cause1: IllegalArgumentException = IllegalArgumentException("Invalid argument")
 * val cause2: IllegalStateException = IllegalStateException("Invalid state")
 * val exception = ExceptionAggregate2(cause1, cause2)
 * println(exception.message)
 * // Prints: Multiple exceptions occurred -- { [IllegalArgumentException] Invalid argument },
 * //         { [IllegalStateException] Invalid state }
 * ```
 *
 * @param T1 The type of the first exception, constrained to `Throwable`.
 * @param T2 The type of the second exception, constrained to `Throwable`.
 * @param cause1 The first exception to be included in the aggregate.
 * @param cause2 The second exception to be included in the aggregate.
 */
data class ExceptionAggregate2<out T1, out T2>(
    val cause1: T1,
    val cause2: T2
) : ExceptionAggregate(createMessage(listOf(cause1, cause2)))
        where T1 : Throwable,
              T2 : Throwable
