/*
 * Copyright (c) 2023, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.CollectionConstraintException

/**
 * Represents a constraint specifically tailored for collections.
 *
 * This interface extends the general [Constraint] interface but specializes in handling collections of any type.
 * When the constraint for a collection is violated, it generates a [CollectionConstraintException]
 * with the provided description.
 *
 * ## Usage
 * ### Example: Implementing a custom collection constraint to ensure non-emptiness
 * ```kotlin
 * class NonEmptyCollectionConstraint : CollectionConstraint {
 *     override val validator: (Collection<*>) -> Boolean = { it.isNotEmpty() }
 * }
 * ```
 *
 * @see Constraint
 * @see CollectionConstraintException
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
interface CollectionConstraint : Constraint<Collection<*>> {

    /**
     * Generates a constraint violation exception specific to collections.
     *
     * @param description A description of the constraint violation.
     *
     * @return A [CollectionConstraintException] with the provided description.
     */
    override fun generateException(description: String) = CollectionConstraintException { description }
}
