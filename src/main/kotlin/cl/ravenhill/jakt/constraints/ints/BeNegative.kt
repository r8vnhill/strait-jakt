package cl.ravenhill.jakt.constraints.ints

data object BeNegative : IntConstraint {
    override val validator = { value: Int -> value < 0 }
}