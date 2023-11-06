package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll


class BeAtLeastTest : FreeSpec({

    "A [BeAtLeast] constraint" - {
        "should have a validator that" - {
            "returns true if the value is greater than or equal to the minimum" {
                checkAll(Arb.orderedPair(Arb.int())) { (min, value) ->
                    BeAtLeast(min).validator(value).shouldBeTrue()
                    BeAtLeast(min).validator(min).shouldBeTrue()
                }
            }

            "returns false if the value is less than the minimum" {
                checkAll(Arb.orderedPair(Arb.int(), strict = true)) { (value, min) ->
                    BeAtLeast(min).validator(value).shouldBeFalse()
                }
            }
        }
    }
})