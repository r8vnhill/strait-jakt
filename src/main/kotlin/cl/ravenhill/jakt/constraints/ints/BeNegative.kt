package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.constraints.BeNegativeConstraint

/**
 * Represents a constraint ensuring a provided integer value is negative.
 *
 * This constraint validates that the integer value being tested is strictly less than zero. It is an implementation
 * of the [IntConstraint] interface.
 *
 * ## Usage
 * ### Example 1: Validating if a number is negative
 * ```kotlin
 * val isNegative = BeNegative.validator(-5)  // Returns `true`
 * val isNotNegative = BeNegative.validator(5) // Returns `false`
 * ```
 *
 * @property zero The integer value representing zero.
 */
data object BeNegative : IntConstraint, BeNegativeConstraint<Int> {
    override val zero: Int = 0
}
