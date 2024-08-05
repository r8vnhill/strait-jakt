/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeInRangeConstraint

/**
 * Represents a constraint that ensures a `Double` value falls within a specified range.
 *
 * This class implements both `DoubleConstraint` and `BeInRangeConstraint<Double>`.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they fall within a specified range.
 *
 * ### Example 1: Using BeInRange Constraint
 *
 * ```kotlin
 * val result = 5.5.constrainedTo {
 *     "'$it' Must be in the range 1.0 to 10.0" { it must BeInRange(1.0..10.0) }
 * }
 * println(result) // Prints: 5.5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateRange(value: Double) {
 *     constraints {
 *         "'$value' Must be in the range 5.0 to 15.0" { value must BeInRange(5.0..15.0) }
 *     }
 * }
 *
 * try {
 *     validateRange(10.0) // Success
 *     validateRange(20.0) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param range The range that the `Double` value must fall within.
 */
data class BeInRange(override val range: ClosedRange<Double>) : DoubleConstraint, BeInRangeConstraint<Double>
