package cl.ravenhill.jakt.constraints.strings

import cl.ravenhill.jakt.constraints.Constraint


interface StringConstraint : Constraint<String> {
    override fun generateException(description: String) = TODO()
}