/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints


/**
 * Represents a constraint that ensures a value is positive, i.e., greater than a specified zero value.
 *
 * ## Usage:
 * Define constraints for values to ensure they are positive.
 *
 * ### Example 1: Implementing a BePositiveConstraint
 *
 * ```kotlin
 * data object BePositiveInt : BePositiveConstraint<Int> {
 *     override val zero: Int = 0
 * }
 *
 * val result = 5.constrainedTo {
 *     "'$it' Must be positive" { it must BePositiveInt }
 * }
 * println(result) // Prints: 5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validatePositive(value: Int) {
 *     constraints {
 *         "'$value' Must be positive" { value must object : BePositiveConstraint<Int> {
 *             override val zero: Int = 0
 *         }}
 *     }
 * }
 *
 * try {
 *     validatePositive(10) // Success
 *     validatePositive(-5) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param T The type of value to which the constraint is applied. Must be comparable.
 * @property zero The value that the actual value must be greater than to be considered positive.
 * @property validator The validation function that checks if the value meets the constraint criteria.
 */
interface BePositiveConstraint<T> : Constraint<T> where T : Comparable<T> {
    val zero: T
    override val validator: (T) -> Boolean
        get() = { it > zero }
}
