/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections


/**
 * Represents a constraint that ensures a collection is monotonically increasing. It checks if each element in the
 * collection is less than or equal to the subsequent element.
 *
 * ## Usage:
 * Ensure that a collection is monotonically increasing.
 *
 * ### Example 1: Successful Constraint Check (Non-Strict)
 *
 * ```kotlin
 * val result = listOf(1, 2, 2, 3).constrainedTo {
 *     "'$it' Must be monotonically increasing" { it must BeMonotonicallyIncreasing() }
 * }
 * println(result) // Prints: [1, 2, 2, 3]
 * ```
 *
 * ### Example 2: Successful Constraint Check (Strict)
 *
 * ```kotlin
 * val result = listOf(1, 2, 3, 4).constrainedTo {
 *     "'$it' Must be strictly monotonically increasing" { it must BeMonotonicallyIncreasing(strict = true) }
 * }
 * println(result) // Prints: [1, 2, 3, 4]
 * ```
 *
 * ### Example 3: Failed Constraint Check
 *
 * ```kotlin
 * try {
 *     val result = listOf(1, 2, 2, 1).constrainedTo {
 *         "'$it' Must be monotonically increasing" { it must BeMonotonicallyIncreasing() }
 *     }
 * } catch (e: CompositeException) {
 *     println(e) // Prints the composite exception containing the constraint failures
 * }
 * ```
 *
 * ### Example 4: Applying Constraints using `constraints { }`
 *
 * ```kotlin
 * fun validateList(list: List<Int>) {
 *     constraints {
 *         "'$list' Must be monotonically increasing" { list must BeMonotonicallyIncreasing() }
 *     }
 *     // Continue with function logic...
 * }
 *
 * try {
 *     validateList(listOf(1, 2, 2, 3)) // Success
 *     validateList(listOf(1, 3, 2, 4)) // Throws CompositeException
 * } catch (e: CompositeException) {
 *     println(e) // Prints the composite exception containing the constraint failures
 * }
 * ```
 *
 * @property strict A flag indicating whether the comparison should be strict. If true, each element must be strictly
 *   less than the subsequent element.
 * @property validator The validation function that checks if the collection is monotonically increasing.
 */
data class BeMonotonicallyIncreasing<T>(val strict: Boolean = false) : CollectionConstraint<T> where T : Comparable<T> {
    override val validator = { value: Collection<T> ->
        value.zipWithNext().all { (a, b) ->
            if (strict) a < b else a <= b
        }
    }
}
