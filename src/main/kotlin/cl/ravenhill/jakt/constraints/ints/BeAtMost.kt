package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a requirement that an integer value must be at most a specified value.
 *
 * @param maxInclusive The maximum allowed value.
 * @property most The maximum allowed value.
 */
class BeAtMost(maxInclusive: Int) : BeInRange(Int.MIN_VALUE..maxInclusive)