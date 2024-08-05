/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeEqualToConstraint
import kotlin.math.abs


/**
 * Represents a constraint that ensures a `Double` value is equal to a specified expected value, within a given
 * tolerance.
 *
 * This class implements both `DoubleConstraint` and `BeEqualToConstraint<Double>`.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are equal to a specified expected value within a given
 * tolerance.
 *
 * ### Example 1: Using BeEqualTo Constraint
 *
 * ```kotlin
 * val result = 5.00000001.constrainedTo {
 *     "'$it' Must be equal to 5.0 within tolerance" { it must BeEqualTo(5.0) }
 * }
 * println(result) // Prints: 5.00000001
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateEquality(value: Double) {
 *     constraints {
 *         "'$value' Must be equal to 10.0 within tolerance 1e-6" { value must BeEqualTo(10.0, 1e-6) }
 *     }
 * }
 *
 * try {
 *     validateEquality(10.000001) // Success
 *     validateEquality(10.1) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param expected The value that the actual value is expected to be equal to.
 * @param tolerance The allowable difference between the actual value and the expected value. Must be non-negative.
 * @throws IllegalArgumentException if the tolerance is negative.
 */
data class BeEqualTo(override val expected: Double, val tolerance: Double = 1e-8) :
    DoubleConstraint, BeEqualToConstraint<Double> {

    init {
        require(tolerance >= 0) { "The tolerance must be non-negative." }
    }

    override val validator = { value: Double -> abs(value - expected) <= tolerance }
}
