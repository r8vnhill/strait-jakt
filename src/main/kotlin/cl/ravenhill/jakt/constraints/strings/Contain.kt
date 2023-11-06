package cl.ravenhill.jakt.constraints.strings


data class Contain(val regex: Regex) : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        regex.containsMatchIn(value)
    }
}