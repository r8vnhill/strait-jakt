package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.constraints.BeInRangeConstraint


/**
 * Represents a constraint that checks if a given integer value falls within a specified closed range.
 *
 * This class implements both the [IntConstraint] and [BeInRangeConstraint] interfaces for integers,
 * providing a specific application of range checking for integer values. It validates that an integer
 * is within the defined `range`, making it ideal for scenarios where integer values must be constrained
 * within specific limits, such as in settings configurations, validation of numerical inputs, or enforcing
 * boundaries in calculations.
 *
 * ## Usage
 * ### Example: Validating if a number falls within a specified range
 * ```kotlin
 * val rangeConstraint = BeInRange(1..10)
 * val isValid = rangeConstraint.validator(5)  // Returns `true` as 5 is within the range of 1 to 10
 * val isInvalid = rangeConstraint.validator(11) // Returns `false` as 11 is outside the range of 1 to 10
 * ```
 *
 * @param range The closed range of integers (inclusive) within which the value should fall.
 * @property validator A lambda function that evaluates an [Int] and returns a [Boolean] indicating whether
 * the value lies within the specified `range`.
 *
 * @constructor Constructs a new constraint instance with the designated range.
 *
 */
data class BeInRange(override val range: ClosedRange<Int>) : IntConstraint, BeInRangeConstraint<Int>

