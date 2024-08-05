/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.CollectionConstraintException

/**
 * Represents a constraint applied to collections. It ensures that a collection meets specific criteria.
 *
 * ## Usage:
 * Define constraints for collections to ensure they meet specified criteria.
 *
 * ### Example 1: Implementing a Collection Constraint
 *
 * ```kotlin
 * data class BeMonotonicallyIncreasing<T>(
 *     val strict: Boolean = false
 * ) : CollectionConstraint<T> where T : Comparable<T> {
 *     override val validator = { value: Collection<T> ->
 *         value.zipWithNext().all { (a, b) ->
 *             if (strict) a < b else a <= b
 *         }
 *     }
 * }
 * ```
 *
 * ### Example 2: Applying a Collection Constraint
 *
 * ```kotlin
 * val result = listOf(1, 2, 3, 4).constrainedTo {
 *     "'$it' Must be monotonically increasing" { it must BeMonotonicallyIncreasing() }
 * }
 * println(result) // Prints: [1, 2, 3, 4]
 * ```
 *
 * ### Example 3: Using Object Expressions for Custom Constraints
 *
 * ```kotlin
 * val customConstraint = object : CollectionConstraint<Int> {
 *     override val validator = { value: Collection<Int> ->
 *         value.all { it > 0 }
 *     }
 * }
 *
 * val result = listOf(1, 2, 3, 4).constrainedTo {
 *     "'$it' Must contain only positive numbers" { it must customConstraint }
 * }
 * println(result) // Prints: [1, 2, 3, 4]
 *
 * try {
 *     val failedResult = listOf(-1, 2, 3, 4).constrainedTo {
 *         "'$it' Must contain only positive numbers" { it must customConstraint }
 *     }
 * } catch (e: CollectionConstraintException) {
 *     println(e) // Prints the collection constraint exception details
 * }
 * ```
 *
 * @param T The type of elements in the collection.
 *
 * @property validator The validation function that checks if the collection meets the constraint criteria.
 */
interface CollectionConstraint<T> : Constraint<Collection<T>> {
    override fun generateException(description: String) = CollectionConstraintException { description }
}
