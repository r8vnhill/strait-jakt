/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeAtLeastConstraint


/**
 * Represents a constraint that ensures a `Double` value is at least a specified minimum value.
 *
 * This class implements both `DoubleConstraint` and `BeAtLeastConstraint<Double>`.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are at least a specified minimum value.
 *
 * ### Example 1: Using BeAtLeast Constraint
 *
 * ```kotlin
 * val result = 5.5.constrainedTo {
 *     "'$it' Must be at least 3.0" { it must BeAtLeast(3.0) }
 * }
 * println(result) // Prints: 5.5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateMinimum(value: Double) {
 *     constraints {
 *         "'$value' Must be at least 10.0" { value must BeAtLeast(10.0) }
 *     }
 * }
 *
 * try {
 *     validateMinimum(15.0) // Success
 *     validateMinimum(5.0) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param minInclusive The minimum value (inclusive) that the `Double` value must be greater than or equal to.
 */
data class BeAtLeast(override val minInclusive: Double) : DoubleConstraint, BeAtLeastConstraint<Double>
