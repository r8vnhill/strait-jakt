/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeAtMostConstraint


/**
 * Represents a constraint that ensures a `Double` value is at most a specified maximum value.
 *
 * This class implements both `DoubleConstraint` and `BeAtMostConstraint<Double>`.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they are at most a specified maximum value.
 *
 * ### Example 1: Using BeAtMost Constraint
 *
 * ```kotlin
 * val result = 5.5.constrainedTo {
 *     "'$it' Must be at most 10.0" { it must BeAtMost(10.0) }
 * }
 * println(result) // Prints: 5.5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateMaximum(value: Double) {
 *     constraints {
 *         "'$value' Must be at most 20.0" { value must BeAtMost(20.0) }
 *     }
 * }
 *
 * try {
 *     validateMaximum(15.0) // Success
 *     validateMaximum(25.0) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param maxInclusive The maximum value (inclusive) that the `Double` value must be less than or equal to.
 */
data class BeAtMost(override val maxInclusive: Double) : DoubleConstraint, BeAtMostConstraint<Double>
