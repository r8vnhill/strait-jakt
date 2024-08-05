/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

import cl.ravenhill.jakt.Jakt.Scope
import cl.ravenhill.jakt.Jakt.skipChecks
import cl.ravenhill.jakt.exceptions.CompositeException

/**
 * Applies constraints defined in the builder on the receiver object and returns the receiver if constraints are
 * satisfied.
 *
 * ## Usage:
 * Ensure that the receiver object meets the specified constraints.
 *
 * ### Example 1: Successful Constraint Check
 *
 * ```kotlin
 * val result = "Hello, World!".constrainedTo {
 *     "'$it' Must contain 'Hello'" { it must Contain("Hello".toRegex()) }
 *     "'$it' Must contain 'World'" { it must Contain("World".toRegex()) }
 * }
 * println(result) // Prints: Hello, World!
 * ```
 *
 * ### Example 2: Failed Constraint Check as Infix Function
 *
 * ```kotlin
 * try {
 *     val result = "Hello, World!" constrainedTo {
 *         "'$it' Must contain 'Hello'" { it must Contain("Hello".toRegex()) }
 *         "'$it' Must be empty" { it must BeEmpty }
 *     }
 * } catch (e: CompositeException) {
 *     println(e) // Prints the composite exception containing the constraint failures
 * }
 * ```
 *
 * @param builder A lambda defining the constraints to be applied on the receiver object.
 * The receiver object is passed as a parameter to the builder.
 * @return The receiver object if constraints are satisfied.
 * @throws CompositeException if any constraints are violated.
 */
infix fun <T> T.constrainedTo(builder: Scope.(T) -> Unit): T =
    if (skipChecks) {
        this
    } else {
        Scope().apply { builder(this@constrainedTo) }.failures
            .takeIf { it.isNotEmpty() }
            ?.let { throw CompositeException(it) }
            ?: this
    }
