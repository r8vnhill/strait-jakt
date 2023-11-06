package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.arbs.datatypes.orderedTriple
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll


class BeInRangeTest : FreeSpec({

    "A [BeInRange] constraint" - {
        "when created" - {
            "with an int range should set the range correctly" {
                checkAll(Arb.orderedPair(Arb.int(), strict = true)) { (lo, hi) ->
                    with(BeInRange(lo..hi)) {
                        this.range shouldBe range
                    }
                }
            }
        }

        "should have a [validator] that" - {
            "returns true if the value is within the range" {
                checkAll(Arb.orderedTriple(Arb.int(), strict = true)) { (lo, mid, hi) ->
                    with(BeInRange(lo..hi)) {
                        validator(mid).shouldBeTrue()
                    }
                }
            }

            "returns false if the value is outside the range" {
                checkAll(Arb.orderedTriple(Arb.int(), strict = true)) { (lo, mid, hi) ->
                    BeInRange(lo..mid).validator(hi).shouldBeFalse()
                    BeInRange(mid..hi).validator(lo).shouldBeFalse()
                }
            }
        }
    }
})