package cl.ravenhill.jakt.constraints.strings

data object BeEmpty : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        value.isEmpty()
    }
}