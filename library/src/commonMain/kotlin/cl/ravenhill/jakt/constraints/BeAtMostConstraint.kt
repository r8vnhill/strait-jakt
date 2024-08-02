/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints


/**
 * Represents a constraint that ensures a value is at most a specified maximum value.
 *
 * ## Usage:
 * Define constraints for values to ensure they are at most a specified maximum value.
 *
 * ### Example 1: Implementing a BeAtMostConstraint
 *
 * ```kotlin
 * data class AtMostTen : BeAtMostConstraint<Int> {
 *     override val maxInclusive: Int = 10
 * }
 *
 * val result = 8.constrainedTo {
 *     "'$it' Must be at most 10" { it must AtMostTen() }
 * }
 * println(result) // Prints: 8
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateMaximumValue(value: Int) {
 *     constraints {
 *         "'$value' Must be at most 20" { value must object : BeAtMostConstraint<Int> {
 *             override val maxInclusive: Int = 20
 *         }}
 *     }
 * }
 *
 * try {
 *     validateMaximumValue(18) // Success
 *     validateMaximumValue(25) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param T The type of value to which the constraint is applied. Must be comparable.
 * @property maxInclusive The maximum value (inclusive) that the actual value must be less than or equal to.
 * @property validator The validation function that checks if the value meets the constraint criteria.
 */
interface BeAtMostConstraint<T> : Constraint<T> where T : Comparable<T> {
    val maxInclusive: T
    override val validator: (T) -> Boolean
        get() = { it <= maxInclusive }
}
