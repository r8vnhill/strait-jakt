/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.DoubleConstraintException

/**
 * Represents a constraint specifically for `Double` values. It ensures that the value meets specific criteria defined
 * by the validator function.
 *
 * ## Usage:
 * Define constraints for `Double` values to ensure they meet specified criteria.
 *
 * ### Example 1: Implementing a DoubleConstraint
 *
 * ```kotlin
 * data object BePositive : DoubleConstraint {
 *     override val validator = { it > 0 }
 * }
 *
 * val result = 5.5.constrainedTo {
 *     "'$it' Must be positive" { it must BePositive }
 * }
 * println(result) // Prints: 5.5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateDouble(value: Double) {
 *     constraints {
 *         "'$value' Must be positive" { value must object : DoubleConstraint {
 *             override val validator = { it > 0 }
 *         }}
 *     }
 * }
 *
 * try {
 *     validateDouble(10.0) // Success
 *     validateDouble(-5.5) // Throws DoubleConstraintException
 * } catch (e: DoubleConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 */
interface DoubleConstraint : Constraint<Double> {

    override fun generateException(description: String) = DoubleConstraintException { description }
}
