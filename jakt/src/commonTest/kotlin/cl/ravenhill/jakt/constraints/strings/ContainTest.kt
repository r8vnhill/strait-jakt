package cl.ravenhill.jakt.constraints.strings

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class ContainTest : FreeSpec({

    "Contain constraint" - {
        "should validate strings that match the regex" - {
            val containsDigit = Contain(Regex("[0-9]"))

            "when the string contains a digit" {
                containsDigit.validator("abc123").shouldBeTrue()
            }

            "when the string does not contain a digit" {
                containsDigit.validator("abcdef").shouldBeFalse()
            }
        }

        "should validate strings against different regex patterns" {
            checkAll(Arb.string()) { value ->
                val containsLowerCase = Contain(Regex("[a-z]"))
                val containsResult = containsLowerCase.validator(value)

                val expectedResult = value.any { it in 'a'..'z' }
                containsResult shouldBe expectedResult
            }
        }
    }
})
