/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.longs

import cl.ravenhill.jakt.constraints.BeEqualToConstraint

/**
 * Represents a constraint ensuring a provided long value matches a specific expected value.
 *
 * This constraint validates that the long value being tested is equal to the specified expected value.
 * It is an implementation of the [LongConstraint] interface.
 *
 * ## Examples
 * ### Example 1: Validating if a number is equal to a specific value
 * ```kotlin
 * val isEqualToTen = BeEqualTo(10L).validator(10L)  // Returns `true`
 * val isNotEqualToTen = BeEqualTo(10L).validator(5L) // Returns `false`
 * ```
 *
 * @property expected The expected long value against which the given value will be compared.
 */
data class BeEqualTo(override val expected: Long) : LongConstraint, BeEqualToConstraint<Long>
