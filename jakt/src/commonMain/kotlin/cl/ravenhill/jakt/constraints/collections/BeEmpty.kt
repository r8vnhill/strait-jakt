/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections

/**
 * Represents a constraint that ensures a collection is empty. It checks if the collection does not contain any
 * elements.
 *
 * ## Usage:
 * Ensure that a collection is empty.
 *
 * ### Example 1: Successful Constraint Check
 *
 * ```kotlin
 * val result = emptyList<String>().constrainedTo {
 *     "'$it' Must be empty" { it must BeEmpty }
 * }
 * println(result) // Prints: []
 * ```
 *
 * ### Example 2: Applying Constraints on a Function Parameter
 *
 * ```kotlin
 * fun foo(list: List<String>) {
 *     constraints {
 *         "'$list' Must not be empty" { list mustNot BeEmpty }
 *     }
 *     // Continue with function logic...
 * }
 * ```
 *
 * @property validator The validation function that checks if the collection is empty.
 */
data object BeEmpty : CollectionConstraint<Any?> {
    override val validator = { value: Collection<Any?> -> value.isEmpty() }
}
