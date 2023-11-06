package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.arbs.datatypes.orderedTriple
import cl.ravenhill.jakt.arbs.datatypes.real
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class BeInRangeTest : FreeSpec({

    "A [BeInRange] constraint" - {
        "when created" - {
            "with a double range should set the range correctly" {
                checkAll(Arb.orderedPair(Arb.real(), Arb.real(), strict = true)) { (start, end) ->
                    with(BeInRange(start..end)) {
                        range.start shouldBe start
                        range.endInclusive shouldBe end
                    }
                }
            }
        }

        "have a validator that" - {
            "returns true if the value is within the range" {
                checkAll(Arb.orderedTriple(Arb.real(), strict = true)) { (lo, mid, hi) ->
                    BeInRange(lo..hi).validator(mid).shouldBeTrue()
                }
            }

            "returns false if the value is outside the range" {
                checkAll(Arb.orderedTriple(Arb.real(), strict = true)) { (lo, mid, hi) ->
                    BeInRange(lo..mid).validator(hi).shouldBeFalse()
                    BeInRange(mid..hi).validator(lo).shouldBeFalse()
                }
            }
        }
    }
})
