package cl.ravenhill.jakt.assertions.constraints

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.arbs.datatypes.orderedTriple
import cl.ravenhill.jakt.constraints.BeInRangeConstraint
import io.kotest.core.spec.style.freeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

/**
 * Validates the behavior of the [BeInRangeConstraint] using property-based testing.
 *
 * This function is a test suite designed to ensure that the [BeInRangeConstraint] operates correctly.
 * It uses the provided [Arb] generator to create a range of test cases. The test cases check if the
 * [BeInRangeConstraint] validator correctly identifies values that are within a specified range and those
 * that are not.
 *
 * ## Test Cases
 * - **Range Property**: Validates that the `range` property of the constraint returns the correct range provided in the
 *   constructor.
 * - **Validator Functionality**:
 *   - Checks if the constraint returns `true` for values within the specified range.
 *   - Checks if the constraint returns `false` for values outside the specified range.
 *
 * ## Usage
 * ### Example: Testing a custom `BeInRangeConstraint`
 * ```kotlin
 * include(`validate BeInRangeConstraint`(Arb.int()) { range ->
 *    IntInRangeConstraint(range)
 * })
 * ```
 *
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraint A lambda function that takes a closed range of type [T] and returns an instance of
 *   [BeInRangeConstraint].
 * @param T The type parameter which must be [Comparable].
 */
fun <T> validateBeInRangeConstraint(
    gen: Arb<T>,
    constraint: (range: ClosedRange<T>) -> BeInRangeConstraint<T>
) where T : Comparable<T> = freeSpec {
    "A [BeInRange] constraint" - {

        "should have a range property that" - {
            "returns the range provided in the constructor" {
                checkAll(Arb.orderedPair(gen)) { (first, second) ->
                    constraint(first..second).range shouldBe first..second
                }
            }
        }

        "should have a validator that" - {
            "returns true when the constraint condition is met" {
                checkAll(Arb.orderedTriple(gen, strict = true)) { (lo, mid, hi) ->
                    constraint(lo..hi).validator(mid).shouldBeTrue()
                }
            }

            "returns false when the constraint condition is not met" {
                checkAll(Arb.orderedTriple(gen, strict = true)) { (lo, mid, hi) ->
                    constraint(lo..mid).validator(hi).shouldBeFalse()
                    constraint(mid..hi).validator(lo).shouldBeFalse()
                }
            }
        }
    }
}
