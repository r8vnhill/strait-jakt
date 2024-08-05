/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints



/**
 * Represents a constraint that ensures a value is within a specified range.
 *
 * ## Usage:
 * Define constraints for values to ensure they fall within a specified range.
 *
 * ### Example 1: Implementing a BeInRangeConstraint
 *
 * ```kotlin
 * data class InRangeOneToTen : BeInRangeConstraint<Int> {
 *     override val range: ClosedRange<Int> = 1..10
 * }
 *
 * val result = 5.constrainedTo {
 *     "'$it' Must be within the range 1 to 10" { it must InRangeOneToTen() }
 * }
 * println(result) // Prints: 5
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateRange(value: Int) {
 *     constraints {
 *         "'$value' Must be within the range 5 to 15" { value must object : BeInRangeConstraint<Int> {
 *             override val range: ClosedRange<Int> = 5..15
 *         }}
 *     }
 * }
 *
 * try {
 *     validateRange(10) // Success
 *     validateRange(3) // Throws ConstraintException
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @param T The type of value to which the constraint is applied. Must be comparable.
 * @property range The range that the actual value must fall within.
 * @property validator The validation function that checks if the value meets the constraint criteria.
 */
interface BeInRangeConstraint<T> : Constraint<T> where T : Comparable<T> {
    val range: ClosedRange<T>
    override val validator: (T) -> Boolean
        get() = range::contains
}
