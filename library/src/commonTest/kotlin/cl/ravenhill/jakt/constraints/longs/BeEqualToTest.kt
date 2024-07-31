package cl.ravenhill.jakt.constraints.longs

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.long
import io.kotest.property.assume
import io.kotest.property.checkAll

class BeEqualToTest : FreeSpec({

    "A [BeEqualTo] constraint for long values" - {
        "when created with a specific expected long" - {
            "should have a [validator] that" - {
                "returns true when the provided long matches the expected value" {
                    checkAll(Arb.long()) { value ->
                        BeEqualTo(value).validator(value).shouldBeTrue()
                    }
                }

                "returns false when the provided long does not match the expected value" {
                    checkAll(Arb.long(), Arb.long()) { expected, nonMatchingValue ->
                        assume { expected shouldNotBe nonMatchingValue }
                        BeEqualTo(expected).validator(nonMatchingValue).shouldBeFalse()
                    }
                }
            }
        }
    }
})
