package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a requirement that an integer value must be equal to a specified value.
 *
 * @property expected The expected value.
 */
class BeEqualTo(val expected: Int) : IntConstraint {

    override val validator = { value: Int -> value == expected }
}