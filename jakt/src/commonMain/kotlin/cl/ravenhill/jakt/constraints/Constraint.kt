/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints

import cl.ravenhill.jakt.exceptions.ConstraintException


/**
 * Represents a constraint that can be applied to a value of type `T`. It ensures that the value meets specific criteria
 * defined by the validator function.
 *
 * ## Usage:
 * Define constraints for values to ensure they meet specified criteria.
 *
 * ### Example 1: Implementing a Simple Constraint
 *
 * ```kotlin
 * data object BePositive : Constraint<Int> {
 *     override val validator = { value: Int -> value > 0 }
 *
 *     override fun generateException(description: String) = ConstraintException(description)
 * }
 *
 * val result = 5.constrainedTo {
 *     "'$it' Must be positive" { it must BePositive }
 * }
 * println(result) // Prints: 5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateNumber(number: Int) {
 *     constraints {
 *         "'$number' Must be positive" { number must BePositive }
 *     }
 * }
 *
 * try {
 *     validateNumber(5) // Success
 *     validateNumber(-3) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * ### Example 3: Implementing a Custom Constraint with Constraints Block
 *
 * ```kotlin
 * val customConstraint = object : Constraint<String> {
 *     override val validator = { value: String -> value.isNotEmpty() }
 *
 *     override fun generateException(description: String) = ConstraintException(description)
 * }
 *
 * fun validateString(str: String) {
 *     constraints {
 *         "'$str' Must not be empty" { str must customConstraint }
 *     }
 * }
 *
 * try {
 *     validateString("Hello") // Success
 *     validateString("") // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * ### Example 4: Using mustNot for Inverse Constraint Check
 *
 * ```kotlin
 * data class BeNegative : Constraint<Int> {
 *     override val validator = { value: Int -> value < 0 }
 *
 *     override fun generateException(description: String) = ConstraintException(description)
 * }
 *
 * fun validateNonNegativeNumber(number: Int) {
 *     constraints {
 *         "'$number' Must not be negative" { number mustNot BeNegative() }
 *     }
 * }
 *
 * try {
 *     validateNonNegativeNumber(5) // Success
 *     validateNonNegativeNumber(-3) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param T The type of value to which the constraint is applied.
 * @property validator The validation function that checks if the value meets the constraint criteria.
 * @throws ConstraintException if the value does not meet the constraint criteria.
 */
interface Constraint<T> {
    val validator: (T) -> Boolean

    /**
     * Generates a `ConstraintException` with the provided description.
     *
     * ## Usage:
     * Use this function to generate a `ConstraintException` when a constraint is violated. This helps StraitJakt handle
     * the exception and provide detailed information about the constraint violation.
     *
     * @param description A string describing the reason for the exception.
     * @return A `ConstraintException` containing the provided description.
     */
    fun generateException(description: String): ConstraintException
}
