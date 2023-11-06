package cl.ravenhill.jakt.constraints.ints

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeInt
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll

class BeNegativeTest : FreeSpec({

    "A [BeNegative] constraint" - {
        "should have a [validator] that" - {
            "returns true if the value is negative" {
                checkAll(Arb.negativeInt()) { value ->
                    BeNegative.validator(value).shouldBeTrue()
                }
            }

            "returns false if the value is non-negative" {
                checkAll(Arb.positiveInt()) { value ->
                    BeNegative.validator(value).shouldBeFalse()
                }

                // Checking zero as it's a boundary condition
                BeNegative.validator(0).shouldBeFalse()
            }
        }
    }
})
