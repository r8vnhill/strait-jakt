package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeInRangeConstraint
import cl.ravenhill.jakt.utils.DoubleRange

/**
 * Represents a constraint that verifies if a given [Double] value falls within a specified range.
 *
 * This constraint ensures that the provided double value is contained within the given [DoubleRange]. It's useful
 * for ensuring a value falls between certain limits.
 *
 * ## Usage
 * ### Example 1: Checking if a double value is within a range
 * ```kotlin
 * val constraint = BeInRange(1.0..5.0)
 * val withinRange = constraint.validator(3.5)  // Returns `true` as 3.5 is in the range 1.0..5.0
 * val outsideRange = constraint.validator(6.0) // Returns `false` as 6.0 is outside the range 1.0..5.0
 * ```
 *
 * @property range The [DoubleRange] within which the value should lie.
 *
 * @constructor Constructs a new constraint instance with the designated range.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data class BeInRange(override val range: DoubleRange) : DoubleConstraint, BeInRangeConstraint<Double>
