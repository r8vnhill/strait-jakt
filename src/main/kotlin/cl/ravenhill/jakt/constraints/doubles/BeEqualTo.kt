package cl.ravenhill.jakt.constraints.doubles

import kotlin.math.abs

/**
 * Represents a constraint to verify if a given [Double] value approximates an expected value within a specified
 * tolerance.
 *
 * This constraint ensures the absolute difference between the provided and expected values doesn't surpass the defined
 * tolerance.
 * It's particularly useful for comparing floating-point numbers, considering the potential inaccuracies in their
 * representation.
 *
 * ## Usage
 * ### Example 1: Assessing the near-equality of two double values
 * ```kotlin
 * val constraint = BeEqualTo(3.14159, 1e-4)
 * val isCloseEnough = constraint.validator(3.14158) // Returns `true` given the tolerance of `1e-4`
 * val notCloseEnough = constraint.validator(3.15)   // Returns `false` given the tolerance of `1e-4`
 * ```
 *
 * ### Example 2: Leveraging the default tolerance
 * ```kotlin
 * val constraint = BeEqualTo(2.0)
 * val result = constraint.validator(2.00000001) // Returns `true` given the default tolerance of `1e-8`
 * ```
 *
 * @property expected The anticipated [Double] value for comparisons.
 * @property tolerance The permissible difference between the expected and observed values. Defaults to `1e-8`.
 * @throws IllegalArgumentException If the supplied tolerance is negative.
 *
 * @constructor Constructs a new constraint instance with the designated expected value and tolerance.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data class BeEqualTo(val expected: Double, val tolerance: Double = 1e-8) : DoubleConstraint {

    init {
        require(tolerance >= 0) { "The tolerance must be non-negative." }
    }

    override val validator = { value: Double -> abs(value - expected) <= tolerance }
}
