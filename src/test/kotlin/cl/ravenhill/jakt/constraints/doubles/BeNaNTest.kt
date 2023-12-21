package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.ExperimentalJakt
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.filterNot
import io.kotest.property.checkAll
@OptIn(ExperimentalJakt::class)
class BeNaNTest : FreeSpec({

    "A Be NaN constraint" - {
        "should have a validator that" - {
            "returns true if the value is NaN" {
                BeNaN.validator(Double.NaN).shouldBeTrue()
            }

            "returns false if the value is not NaN" {
                checkAll(Arb.double().filterNot { it.isNaN() }) { value ->
                    BeNaN.validator(value).shouldBeFalse()
                }
            }
        }
    }
})
