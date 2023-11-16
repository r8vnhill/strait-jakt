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
 * @property validator A lambda function that checks if the provided integer value is negative.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data object BeNegative : IntConstraint, BeNegativeConstraint<Int> {
    override val zero: Int = 0
}
