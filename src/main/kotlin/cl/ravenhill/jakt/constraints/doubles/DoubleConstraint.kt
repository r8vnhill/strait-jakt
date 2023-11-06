/**
 * Copyright (c) 2023, R8V.
 * BSD Zero Clause License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.DoubleRequirementException
import cl.ravenhill.utils.DoubleRange
import cl.ravenhill.utils.toRange
import kotlin.math.abs

/**
 * Represents a constraint that can be applied to a double.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @version 2.0.0
 * @since 2.0.0
 */
interface DoubleConstraint : Constraint<Double> {

    override fun generateException(description: String) = DoubleRequirementException { description }

    /**
     * A [DoubleConstraint] constraint that checks if a given [Double] is within a specified range.
     *
     * @property range The range of acceptable [Double] values, as a `Pair<Double, Double>` pair.
     */
    class BeInRange(val range: DoubleRange) : DoubleConstraint {

        /**
         * A secondary constructor that allows for the range to be specified as a
         * [ClosedFloatingPointRange].
         *
         * @param range The range of acceptable [Double] values, as a [ClosedFloatingPointRange].
         */
        constructor(range: Pair<Double, Double>) : this(range.toRange()) {
            require(range.first <= range.second) {
                "The first value in the range must be less than or equal to the second value."
            }
        }

        /// Documentation inherited from [Requirement]
        override val validator = { value: Double -> value in range }

        override fun toString() = "BeInRange { range: $range }"
    }

    /**
     * A [DoubleConstraint] constraint that checks if a given [Double] is equal to within a certain
     * tolerance of an expected value.
     *
     * @property expected The expected [Double] value.
     * @property tolerance The maximum allowable difference between the given value and the expected
     * value. Defaults to `1e-8`.
     */
    class BeEqualTo(val expected: Double, val tolerance: Double = 1e-8) : DoubleConstraint {

        init {
            require(tolerance >= 0) { "The tolerance must be non-negative." }
        }

        /// Documentation inherited from [Requirement]
        override val validator = { value: Double -> abs(value - expected) <= tolerance }

        /// Documentation inherited from [Any]
        override fun toString() = "BeEqualTo { expected: $expected, tolerance: $tolerance }"
    }
}