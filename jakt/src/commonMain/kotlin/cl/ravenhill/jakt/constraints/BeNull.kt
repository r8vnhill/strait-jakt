/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints

import cl.ravenhill.jakt.exceptions.ConstraintException

/**
 * Represents a constraint that ensures a value is `null`.
 *
 * This class implements the `Constraint<Any?>` interface.
 *
 * ## Usage:
 * Define constraints for values to ensure they are `null`.
 *
 * ### Example 1: Using BeNull Constraint
 *
 * ```kotlin
 * val result = null.constrainedTo {
 *     "'$it' Must be null" { it must BeNull }
 * }
 * println(result) // Prints: null
 * ```
 *
 * ### Example 2: Using a Constraints Block for Custom Validation
 *
 * ```kotlin
 * fun validateNull(value: Any?) {
 *     constraints {
 *         "'$value' Must not be null" { value mustNot BeNull }
 *     }
 * }
 *
 * try {
 *     validateNull(null) // Throws ConstraintException
 *     validateNull("Not Null")
 * } catch (e: ConstraintException) {
 *     println(e) // Prints the constraint exception details
 * }
 * ```
 *
 * @property validator The validation function that checks if the value is `null`.
 * @throws ConstraintException if the value does not meet the constraint criteria.
 */
data object BeNull : Constraint<Any?> {
    override val validator: (Any?) -> Boolean = { it == null }

    override fun generateException(description: String) = ConstraintException { description }
}
