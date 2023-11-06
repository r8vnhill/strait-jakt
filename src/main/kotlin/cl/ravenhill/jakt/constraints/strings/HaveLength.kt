package cl.ravenhill.jakt.constraints.strings


data class HaveLength(val predicate: (Int) -> Boolean) : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        predicate(value.length)
    }
}