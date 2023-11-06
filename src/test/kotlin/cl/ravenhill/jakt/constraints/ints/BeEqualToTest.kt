package cl.ravenhill.jakt.constraints.ints

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.assume
import io.kotest.property.checkAll

class BeEqualToTest : FreeSpec({

    "A [BeEqualTo] constraint" - {
        "when created with a specific expected integer" - {
            "should have a [validator] that" - {
                "returns true when the provided integer matches the expected value" {
                    checkAll(Arb.int()) { value ->
                        BeEqualTo(value).validator(value).shouldBeTrue()
                    }
                }

                "returns false when the provided integer does not match the expected value" {
                    checkAll(Arb.int(), Arb.int()) { expected, nonMatchingValue ->
                        assume { expected shouldNotBe nonMatchingValue }
                        BeEqualTo(expected).validator(nonMatchingValue).shouldBeFalse()
                    }
                }
            }
        }
    }
})
