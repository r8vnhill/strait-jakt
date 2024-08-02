/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints


/**
 * Represents a constraint that ensures a value is at least a specified minimum value.
 *
 * ## Usage:
 * Define constraints for values to ensure they are at least a specified minimum value.
 *
 * ### Example 1: Implementing a BeAtLeastConstraint
 *
 * ```kotlin
 * data class AtLeastFive : BeAtLeastConstraint<Int> {
 *     override val minInclusive: Int = 5
 * }
 *
 * val result = 6.constrainedTo {
 *     "'$it' Must be at least 5" { it must AtLeastFive() }
 * }
 * println(result) // Prints: 6
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateMinimumValue(value: Int) {
 *     constraints {
 *         "'$value' Must be at least 10" { value must object : BeAtLeastConstraint<Int> {
 *             override val minInclusive: Int = 10
 *         }}
 *     }
 * }
 *
 * try {
 *     validateMinimumValue(12) // Success
 *     validateMinimumValue(8) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param T The type of value to which the constraint is applied. Must be comparable.
 * @property minInclusive The minimum value (inclusive) that the actual value must be greater than or equal to.
 * @property validator The validation function that checks if the value meets the constraint criteria.
 */
interface BeAtLeastConstraint<T> : Constraint<T> where T : Comparable<T> {
    val minInclusive: T
    override val validator: (T) -> Boolean
        get() = { it >= minInclusive }
}
