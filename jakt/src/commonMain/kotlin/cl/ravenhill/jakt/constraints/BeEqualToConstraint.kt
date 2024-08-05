/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints


/**
 * Represents a constraint that ensures a value is equal to a specified expected value.
 *
 * ## Usage:
 * Define constraints for values to ensure they are equal to a specified expected value.
 *
 * ### Example 1: Implementing a BeEqualToConstraint
 *
 * ```kotlin
 * data class EqualToFive : BeEqualToConstraint<Int> {
 *     override val expected: Int = 5
 * }
 *
 * val result = 5.constrainedTo {
 *     "'$it' Must be equal to 5" { it must EqualToFive() }
 * }
 * println(result) // Prints: 5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateEquality(value: Int) {
 *     constraints {
 *         "'$value' Must be equal to 10" { value must object : BeEqualToConstraint<Int> {
 *             override val expected: Int = 10
 *         }}
 *     }
 * }
 *
 * try {
 *     validateEquality(10) // Success
 *     validateEquality(5) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param T The type of value to which the constraint is applied.
 * @property expected The value that the actual value is expected to be equal to.
 * @property validator The validation function that checks if the value meets the constraint criteria.
 */
interface BeEqualToConstraint<T> : Constraint<T> {
    val expected: T
    override val validator: (T) -> Boolean
        get() = { it == expected }
}
