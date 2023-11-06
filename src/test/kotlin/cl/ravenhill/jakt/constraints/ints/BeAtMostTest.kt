package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class BeAtMostTest : FreeSpec({

    "A [BeAtMost] constraint" - {
        "should have a [validator] that" - {
            "returns true if the value is at most the maximum allowed value" {
                checkAll(Arb.orderedPair(Arb.int())) { (value, max) ->
                    with(BeAtMost(max)) {
                        validator(value).shouldBeTrue()
                        validator(max).shouldBeTrue()
                    }
                }
            }

            "returns false if the value is greater than the maximum allowed value" {
                checkAll(Arb.orderedPair(Arb.int(), strict = true)) { (max, value) ->
                    BeAtMost(max).validator(value).shouldBeFalse()
                }
            }
        }
    }
})
