package cl.ravenhill.jakt.testfactories

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.constraints.BeAtLeastConstraint
import io.kotest.core.spec.style.freeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.checkAll

/**
 * Validates the behavior of the [BeAtLeastConstraint] using property-based testing.
 *
 * This function is a test suite designed to ensure that the [BeAtLeastConstraint] operates correctly.
 * It uses the provided [Arb] generator to create a range of test cases. The test cases check if the
 * [BeAtLeastConstraint] validator correctly identifies values that are greater than or equal to
 * a given minimum and those that are not.
 *
 * ## Usage
 * This function is used in the context of property-based testing. It requires a generator ([gen]) for
 * the type [T] and a function to create a [BeAtLeastConstraint] instance.
 *
 * ## Test Cases
 * - **returns true if the value is greater than or equal to the minimum**: Validates that the constraint
 *   returns `true` for values that meet or exceed the specified minimum.
 * - **returns false if the value is less than the minimum**: Validates that the constraint returns `false`
 *   for values that are less than the specified minimum.
 *
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraint A lambda function that takes a minimum inclusive value of type [T] and returns an
 * instance of [BeAtLeastConstraint] for that minimum value.
 * @param T The type parameter which must be [Comparable].
 */
fun <T> `validate BeAtLeastConstraint`(
    gen: Arb<T>,
    constraint: (minInclusive: T) -> BeAtLeastConstraint<T>
) where T : Comparable<T> = freeSpec {
    "A [BeAtLeast] constraint" - {
        "should have a validator that" - {
            "returns true if the value is greater than or equal to the minimum" {
                checkAll(Arb.orderedPair(gen)) { (min, value) ->
                    constraint(min).validator(value).shouldBeTrue()
                    constraint(min).validator(min).shouldBeTrue()
                }
            }

            "returns false if the value is less than the minimum" {
                checkAll(Arb.orderedPair(gen, strict = true)) { (value, min) ->
                    constraint(min).validator(value).shouldBeFalse()
                }
            }
        }
    }
}