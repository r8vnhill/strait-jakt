package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.utils.IntToInt
import cl.ravenhill.utils.contains

/**
 * Represents a requirement that an integer value must be within a specified [range].
 *
 * @constructor Creates a [BeInRange] requirement with a range of integer values specified as an
 * [IntToInt].
 * @property range The range of values that are allowed.
 */
open class BeInRange(val range: IntToInt) : IntConstraint {

    init {
        require(range.first <= range.second) {
            "The first value in the range [${range.first}] must be less than or equal to the second value [${range.second}]."
        }
    }

    /**
     * Creates a [BeInRange] requirement with a range of integer values specified as an
     * [IntRange].
     *
     * @param range The [IntRange] of allowed values.
     */
    constructor(range: IntRange) : this(range.first to range.last)

    /// Documentation inherited from [Requirement].
    override val validator = { value: Int -> value in range }

    /// Documentation inherited from [Any].
    override fun toString() = "BeInRange { range: $range }"
}