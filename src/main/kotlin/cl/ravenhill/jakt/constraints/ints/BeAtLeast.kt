package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a requirement that an integer value must be at least a specified value.
 *
 * @param minInclusive The minimum allowed value.
 * @property least The minimum allowed value.
 */
class BeAtLeast(minInclusive: Int) : BeInRange(minInclusive..Int.MAX_VALUE)