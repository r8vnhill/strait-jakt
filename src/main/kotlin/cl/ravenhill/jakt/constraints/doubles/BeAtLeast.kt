package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeAtLeastConstraint


/**
 * Represents a constraint that checks if a given double value is at least a specified minimum value.
 *
 * This constraint is used to validate that a double value is greater than or equal to the specified minimum
 * value (`minInclusive`). It is particularly useful in scenarios where numerical values must meet a certain
 * threshold, such as minimum requirements, lower bounds in ranges, or in validating measurements and scores.
 *
 * ## Usage
 * ### Example: Validating a value against a minimum threshold
 * ```kotlin
 * val minConstraint = BeAtLeast(10.0)
 * val isValid = minConstraint.validator(10.5) // Returns `true` as 10.5 is greater than 10.0
 * val isInvalid = minConstraint.validator(9.99) // Returns `false` as 9.99 is less than 10.0
 * ```
 *
 * @param minInclusive The minimum inclusive value that the double value should be equal to or exceed.
 * @property validator A lambda function that takes a [Double] and returns a [Boolean] indicating whether the
 * given value is greater than or equal to `minInclusive`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.2.0
 * @version 1.2.0
 */
data class BeAtLeast(override val minInclusive: Double) : DoubleConstraint, BeAtLeastConstraint<Double>
