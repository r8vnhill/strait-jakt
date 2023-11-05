/*
 * Copyright (c) 2023, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections

/**
 * Represents a constraint requiring that a collection has a specific size.
 *
 * @property size The desired size that the collection should have.
 */
data class HaveSize<T>(val size: Int) : CollectionConstraint<T> {
    override val validator = { value: Collection<T> -> value.size == size }
}