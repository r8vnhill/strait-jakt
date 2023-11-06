package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a requirement that an integer value must be positive.
 */
data object BePositive : IntConstraint {

    override val validator = { value: Int -> value > 0 }
}