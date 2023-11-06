package cl.ravenhill.jakt.constraints.strings


data class Match(val regex: Regex) : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        regex.matches(value)
    }
}