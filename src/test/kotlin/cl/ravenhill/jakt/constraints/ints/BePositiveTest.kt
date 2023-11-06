package cl.ravenhill.jakt.constraints.ints

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.negativeInt
import io.kotest.property.arbitrary.nonPositiveInt
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll

class BePositiveTest : FreeSpec({

    "A [BePositive] constraint" - {
        "should have a [validator] that" - {
            "returns true if the value is positive" {
                checkAll(Arb.positiveInt()) { value ->
                    BePositive.validator(value).shouldBeTrue()
                }
            }

            "returns false if the value is non-positive" {
                checkAll(Arb.nonPositiveInt()) { value ->
                    BePositive.validator(value).shouldBeFalse()
                }
            }
        }
    }
})
