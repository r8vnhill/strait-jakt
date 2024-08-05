/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeNegativeConstraint

/**
 * Represents a constraint that ensures a `Double` value is negative, i.e., less than zero.
 *
 * This class implements both `DoubleConstraint` and `BeNegativeConstraint<Double>`.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are negative.
 *
 * ### Example 1: Using BeNegative Constraint
 *
 * ```kotlin
 * val result = (-5.5).constrainedTo {
 *     "'$it' Must be negative" { it must BeNegative }
 * }
 * println(result) // Prints: -5.5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateNegative(value: Double) {
 *     constraints {
 *         "'$value' Must be negative" { value must BeNegative }
 *     }
 * }
 *
 * try {
 *     validateNegative(-10.0) // Success
 *     validateNegative(5.0) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @property zero The value that the actual value must be less than to be considered negative.
 */
data object BeNegative : DoubleConstraint, BeNegativeConstraint<Double> {
    override val zero: Double = 0.0
}
