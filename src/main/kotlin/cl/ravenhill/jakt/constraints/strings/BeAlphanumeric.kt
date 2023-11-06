package cl.ravenhill.jakt.constraints.strings

data object BeAlphanumeric : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        value.all { it.isLetterOrDigit() }
    }
}
