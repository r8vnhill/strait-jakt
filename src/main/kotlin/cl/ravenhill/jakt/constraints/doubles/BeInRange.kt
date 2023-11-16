package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeInRangeConstraint

/**
 * Represents a constraint that checks if a given double value falls within a specified closed range.
 *
 * This class implements both [DoubleConstraint] and [BeInRangeConstraint] for double values,
 * providing a specific application of range checking for double precision floating-point numbers.
 * It validates that a double is within the defined `range`, making it ideal for scenarios where
 * double values must be constrained within specific numerical limits. This can include settings
 * configurations, validation of numerical inputs, or enforcing boundaries in calculations.
 *
 * ## Usage
 * ### Example: Validating if a double value falls within a specific range
 * ```kotlin
 * val rangeConstraint = BeInRange(1.0..10.0)
 * val isValid = rangeConstraint.validator(5.0)  // Returns `true` as 5.0 is within the range of 1.0 to 10.0
 * val isInvalid = rangeConstraint.validator(10.5) // Returns `false` as 10.5 is outside the range of 1.0 to 10.0
 * ```
 *
 * @param range The closed range of doubles (inclusive) within which the value should fall.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.2.0
 */
data class BeInRange(override val range: ClosedRange<Double>) : DoubleConstraint, BeInRangeConstraint<Double>

