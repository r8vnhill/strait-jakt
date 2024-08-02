/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

/**
 * Represents a constraint that ensures a `Double` value is finite.
 *
 * This class implements the `DoubleConstraint` interface.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are finite.
 *
 * ### Example 1: Using BeFinite Constraint
 *
 * ```kotlin
 * val result = 5.5.constrainedTo {
 *     "'$it' Must be finite" { it must BeFinite }
 * }
 * println(result) // Prints: 5.5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateFinite(value: Double) {
 *     constraints {
 *         "'$value' Must be finite" { value must BeFinite }
 *     }
 * }
 *
 * try {
 *     validateFinite(10.0) // Success
 *     validateFinite(Double.POSITIVE_INFINITY) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 */
data object BeFinite : DoubleConstraint {
    override val validator = Double::isFinite
}
