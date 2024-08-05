/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections


/**
 * Represents a constraint that ensures a collection has a specific size, or meets a size condition.
 *
 * ## Usage:
 * Define constraints for collections to ensure they meet specified size criteria.
 *
 * ### Example 1: Constraint Check for Exact Size
 *
 * ```kotlin
 * val result = listOf(1, 2, 3, 4).constrainedTo {
 *     "'$it' Must have a size of 4" { it must HaveSize(4) }
 * }
 * println(result) // Prints: [1, 2, 3, 4]
 * ```
 *
 * ### Example 2: Constraint Check for Custom Size Condition
 *
 * ```kotlin
 * val result = listOf(1, 2, 3, 4, 5).constrainedTo {
 *     "'$it' Must have a size greater than 3" { it must HaveSize { it > 3 } }
 * }
 * println(result) // Prints: [1, 2, 3, 4, 5]
 * ```
 *
 * ### Example 3: Failed Constraint Check
 *
 * ```kotlin
 * try {
 *     val result = listOf(1, 2, 3).constrainedTo {
 *         "'$it' Must have a size of 4" { it must HaveSize(4) }
 *     }
 * } catch (e: CompositeException) {
 *     println(e) // Prints the composite exception containing the constraint failures
 * }
 * ```
 *
 * @param predicate A lambda that takes an integer size and returns a boolean indicating if the size meets the
 *   condition.
 * @property validator The validation function that checks if the collection meets the size constraint.
 * @constructor Creates a constraint that ensures a collection meets a size condition.
 */
data class HaveSize(val predicate: (Int) -> Boolean) : CollectionConstraint<Any?> {

    /**
     * Constructor to create a HaveSize constraint for an exact size.
     *
     * @param size The exact size that the collection must have.
     */
    constructor(size: Int) : this({ it == size })

    override val validator = { value: Collection<Any?> -> predicate(value.size) }
}
