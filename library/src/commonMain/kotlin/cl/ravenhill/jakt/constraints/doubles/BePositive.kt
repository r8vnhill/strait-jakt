/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BePositiveConstraint
import cl.ravenhill.jakt.constraints.doubles.BePositive.zero

/**
 * Represents a constraint that ensures a `Double` value is positive, i.e., greater than zero.
 *
 * This class implements both `DoubleConstraint` and `BePositiveConstraint<Double>`.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are positive.
 *
 * ### Example 1: Using BePositive Constraint
 *
 * ```kotlin
 * val result = 5.5.constrainedTo {
 *     "'$it' Must be positive" { it must BePositive }
 * }
 * println(result) // Prints: 5.5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validatePositive(value: Double) {
 *     constraints {
 *         "'$value' Must be positive" { value must BePositive }
 *     }
 * }
 *
 * try {
 *     validatePositive(10.0) // Success
 *     validatePositive(-5.0) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @property zero The value that the actual value must be greater than to be considered positive.
 */
data object BePositive : DoubleConstraint, BePositiveConstraint<Double> {
    override val zero = 0.0
}
