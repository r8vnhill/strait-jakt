/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions.aggregates

/**
 * Represents a single exception wrapped within the `ExceptionAggregate` hierarchy.
 *
 * The `ExceptionAggregateSingle` class encapsulates a single cause within an `ExceptionAggregate`, providing a
 * formatted message that includes the type and message of the wrapped exception. This is useful for cases where only
 * one exception has occurred, but it should still conform to the aggregate exception structure.
 *
 * ## Usage:
 * Use this class to encapsulate a single exception within an `ExceptionAggregate` structure, maintaining detailed
 * information about the specific cause.
 *
 * ### Example 1: Using ExceptionAggregateSingle
 * ```kotlin
 * val cause = IllegalArgumentException("Invalid argument")
 * val exception = ExceptionAggregateSingle(cause)
 * println(exception.message)
 * // Prints: An exception occurred -- [IllegalArgumentException] Invalid argument
 * ```
 *
 * @param cause The single exception wrapped by this `ExceptionAggregateSingle`.
 */
data class ExceptionAggregate1<out T>(val cause: T) :
    ExceptionAggregate("An exception occurred -- [${cause::class.simpleName}] ${cause.message}")
        where T : Throwable
