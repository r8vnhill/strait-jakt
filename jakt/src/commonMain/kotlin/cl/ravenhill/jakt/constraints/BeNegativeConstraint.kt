/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints


/**
 * Represents a constraint that ensures a value is negative, i.e., less than a specified zero value.
 *
 * ## Usage:
 * Define constraints for values to ensure they are negative.
 *
 * ### Example 1: Implementing a BeNegativeConstraint
 *
 * ```kotlin
 * data object BeNegativeInt : BeNegativeConstraint<Int> {
 *     override val zero: Int = 0
 * }
 *
 * val result = (-5).constrainedTo {
 *     "'$it' Must be negative" { it must BeNegativeInt }
 * }
 * println(result) // Prints: -5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateNegative(value: Int) {
 *     constraints {
 *         "'$value' Must be negative" { value must object : BeNegativeConstraint<Int> {
 *             override val zero: Int = 0
 *         }}
 *     }
 * }
 *
 * try {
 *     validateNegative(-10) // Success
 *     validateNegative(5) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param T The type of value to which the constraint is applied. Must be comparable.
 * @property zero The value that the actual value must be less than to be considered negative.
 * @property validator The validation function that checks if the value meets the constraint criteria.
 */
interface BeNegativeConstraint<T> : Constraint<T> where T : Comparable<T> {
    val zero: T
    override val validator: (T) -> Boolean
        get() = { it < zero }
}
