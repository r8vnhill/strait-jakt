package cl.ravenhill.jakt.constraints.strings

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class HaveLengthTest : FreeSpec({

    "HaveLength constraint" - {
        "should validate strings based on their length" - {
            "when checking for a specific length" {
                val lengthFive = HaveLength { it == 5 }
                lengthFive.validator("Hello").shouldBeTrue()
                lengthFive.validator("Hi").shouldBeFalse()
            }

            "when using a range for length validation" {
                val lengthRange = HaveLength { it in 3..8 }
                lengthRange.validator("Hello").shouldBeTrue()
                lengthRange.validator("Hi").shouldBeFalse()
            }

            "when testing with random strings" {
                checkAll(Arb.string(1..10)) { value ->
                    val lengthConstraint = HaveLength { it == value.length }
                    lengthConstraint.validator(value).shouldBeTrue()
                }
            }
        }
    }
})
