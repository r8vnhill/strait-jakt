package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a requirement that an integer value must be at least a specified value.
 *
 * @param min The minimum allowed value.
 * @property least The minimum allowed value.
 */
class BeAtLeast(min: Int) : BeInRange(min to Int.MAX_VALUE) {
    val least: Int get() = range.first

    /// Documentation inherited from [Any].
    override fun toString() = "BeAtLeast { min: ${range.first} }"
}