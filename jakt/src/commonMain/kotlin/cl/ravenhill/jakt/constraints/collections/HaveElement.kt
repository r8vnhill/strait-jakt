/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections


/**
 * Represents a constraint that ensures a collection contains a specific element.
 *
 * ## Usage:
 * Define constraints for collections to ensure they meet specified criteria.
 *
 * ### Example 1: Successful Constraint Check
 *
 * ```kotlin
 * val result = listOf(1, 2, 3, 4).constrainedTo {
 *     "'$it' Must contain the element 3" { it must HaveElement(3) }
 * }
 * println(result) // Prints: [1, 2, 3, 4]
 * ```
 *
 * ### Example 2: Failed Constraint Check
 *
 * ```kotlin
 * try {
 *     val result = listOf(1, 2, 3, 4).constrainedTo {
 *         "'$it' Must contain the element 5" { it must HaveElement(5) }
 *     }
 * } catch (e: CompositeException) {
 *     println(e) // Prints the composite exception containing the constraint failures
 * }
 * ```
 *
 * ### Example 3: Using Object Expressions for Custom Constraints
 *
 * ```kotlin
 * val customConstraint = object : CollectionConstraint<Int> {
 *     override val validator = { value: Collection<Int> ->
 *         value.contains(2)
 *     }
 * }
 *
 * val result = listOf(1, 2, 3, 4).constrainedTo {
 *     "'$it' Must contain the element 2" { it must customConstraint }
 * }
 * println(result) // Prints: [1, 2, 3, 4]
 *
 * try {
 *     val failedResult = listOf(1, 3, 4).constrainedTo {
 *         "'$it' Must contain the element 2" { it must customConstraint }
 *     }
 * } catch (e: CompositeException) {
 *     println(e) // Prints the composite exception containing the constraint failures
 * }
 * ```
 *
 * @param T The type of elements in the collection.
 * @property element The element that must be present in the collection.
 * @property validator The validation function that checks if the collection meets the constraint criteria.
 */
data class HaveElement<T>(private val element: T) : CollectionConstraint<T> {
    override val validator = { collection: Collection<T> -> element in collection }
}
