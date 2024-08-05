package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.arbs.datatypes.real
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.checkAll


class BeEqualToTest : FreeSpec({

    "A [BeEqualTo] constraint" - {
        "have a validator that" - {
            "returns true if the value is equal to the expected value" {
                checkAll(Arb.real()) { value ->
                    BeEqualTo(value).validator(value).shouldBeTrue()
                }
            }

            "returns true if the value is within the tolerance of the expected value" {
                checkAll(
                    Arb.real(),
                    Arb.orderedPair(Arb.real(0.0..1.0), Arb.real(0.0..1.0))
                ) { value, (disturbance, tolerance) ->
                    BeEqualTo(value, tolerance).validator(value + disturbance).shouldBeTrue()
                }
            }

            "returns false if the value is not within the tolerance of the expected value" {
                checkAll(
                    Arb.real(),
                    Arb.orderedPair(Arb.real(0.0..1.0), Arb.real(0.0..1.0))
                ) { value, (tolerance, disturbance) ->
                    BeEqualTo(value, tolerance).validator(value + disturbance).shouldBeTrue()
                }
            }

            "throws an exception if the tolerance is negative" {
                checkAll(Arb.double(), Arb.double()) { value, expected ->
                    shouldThrowWithMessage<IllegalArgumentException>(
                        "The tolerance must be non-negative."
                    ) {
                        BeEqualTo(expected, -1e-8).validator(value)
                    }
                }
            }
        }
    }
})