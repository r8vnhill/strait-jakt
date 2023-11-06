package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a requirement that an integer value must be at most a specified value.
 *
 * @param max The maximum allowed value.
 * @property most The maximum allowed value.
 */
class BeAtMost(max: Int) : BeInRange(Int.MIN_VALUE to max) {
    val most: Int get() = range.second

    /// Documentation inherited from [Any].
    override fun toString() = "BeAtMost { max: ${range.second} }"
}