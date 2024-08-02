/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.doubles.BeNaN.validator

/**
 * Represents a constraint that ensures a `Double` value is NaN (Not-a-Number).
 *
 * This class implements the `DoubleConstraint` interface.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are NaN.
 *
 * ### Example 1: Using BeNaN Constraint
 *
 * ```kotlin
 * val result = Double.NaN.constrainedTo {
 *     "'$it' Must be NaN" { it must BeNaN }
 * }
 * println(result) // Prints: NaN
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateNaN(value: Double) {
 *     constraints {
 *         "'$value' Must be NaN" { value must BeNaN }
 *     }
 * }
 *
 * try {
 *     validateNaN(Double.NaN) // Success
 *     validateNaN(10.0) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @property validator The validation function that checks if the value is NaN.
 */
data object BeNaN : DoubleConstraint {
    override val validator = Double::isNaN
}

