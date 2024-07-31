package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.ExperimentalJakt
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.checkAll

@OptIn(ExperimentalJakt::class)
class BeInfiniteTest : FreeSpec({

    "A Be Infinite constraint" - {
        "should have a validator that" - {
            "returns true if the value is infinite" {
                BeInfinite.validator(Double.NEGATIVE_INFINITY).shouldBeTrue()
                BeInfinite.validator(Double.POSITIVE_INFINITY).shouldBeTrue()
            }

            "returns false if the value is not infinite" {
                checkAll(Arb.double().filterNot { it.isInfinite() }) { value ->
                    BeInfinite.validator(value).shouldBeFalse()
                }
            }
        }
    }
})
