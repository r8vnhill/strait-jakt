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
data class HaveSize(val predicate: (Int) -> Boolean) : CollectionConstraint {

    constructor(size: Int) : this({ it == size })

    override val validator = { value: Collection<*> -> predicate(value.size) }
}