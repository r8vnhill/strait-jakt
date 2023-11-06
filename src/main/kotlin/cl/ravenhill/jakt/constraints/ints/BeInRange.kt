package cl.ravenhill.jakt.constraints.ints


/**
 * Represents a requirement that an integer value must be within a specified [range].
 *
 * @constructor Creates a [BeInRange] requirement with a range of integer values specified as an
 * [IntToInt].
 * @property range The range of values that are allowed.
 */
open class BeInRange(val range: ClosedRange<Int>) : IntConstraint {

    /// Documentation inherited from [Requirement].
    override val validator = { value: Int -> value in range }
}