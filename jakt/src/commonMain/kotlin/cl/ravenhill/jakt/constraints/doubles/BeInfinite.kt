/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles


/**
 * Represents a constraint that ensures a `Double` value is infinite.
 *
 * This class implements the `DoubleConstraint` interface.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are infinite.
 *
 * ### Example 1: Using BeInfinite Constraint
 *
 * ```kotlin
 * val result = Double.POSITIVE_INFINITY.constrainedTo {
 *     "'$it' Must be infinite" { it must BeInfinite }
 * }
 * println(result) // Prints: Infinity
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateInfinite(value: Double) {
 *     constraints {
 *         "'$value' Must be infinite" { value must BeInfinite }
 *     }
 * }
 *
 * try {
 *     validateInfinite(Double.POSITIVE_INFINITY) // Success
 *     validateInfinite(10.0) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 */
data object BeInfinite : DoubleConstraint {
    override val validator = Double::isInfinite
}
