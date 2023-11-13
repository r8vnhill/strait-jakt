package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.constraints.BeAtMostConstraint

/**
 * Represents a constraint that checks if a given integer value is at most a specified maximum value.
 *
 * This class implements both [BeAtMostConstraint] and [IntConstraint] for integers. It is used to validate
 * that an integer value is less than or equal to the specified maximum value (`maxInclusive`). This constraint
 * is particularly useful in scenarios where you need to enforce upper limits on numerical values, such as
 * ensuring values do not exceed a predefined maximum in settings, measurements, or calculations.
 *
 * ## Usage
 * ### Example: Validating a value against a maximum limit
 * ```kotlin
 * val maxConstraint = BeAtMost(100)
 * val isValid = maxConstraint.validator(50)  // Returns `true` as 50 is less than 100
 * val isInvalid = maxConstraint.validator(150) // Returns `false` as 150 exceeds 100
 * ```
 *
 * @param maxInclusive The maximum inclusive value that the integer value should not exceed.
 * @property validator A lambda function that takes an [Int] and returns a [Boolean] indicating whether the value
 * is less than or equal to `maxInclusive`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.2.0
 */
data class BeAtMost(override val maxInclusive: Int) : BeAtMostConstraint<Int>, IntConstraint

