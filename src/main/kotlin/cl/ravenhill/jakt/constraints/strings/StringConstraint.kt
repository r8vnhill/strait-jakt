package cl.ravenhill.jakt.constraints.strings

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.StringConstraintException


interface StringConstraint : Constraint<String> {
    override fun generateException(description: String) = StringConstraintException { description }
}