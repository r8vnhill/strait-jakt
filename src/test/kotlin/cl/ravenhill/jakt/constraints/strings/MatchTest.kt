package cl.ravenhill.jakt.constraints.strings

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class MatchTest : FreeSpec({

    "Match constraint" - {
        "should validate strings that fully match the regex" - {
            "with a simple numeric pattern" {
                val isNumeric = Match(Regex("^\\d+$"))

                isNumeric.validator("12345").shouldBeTrue()
                isNumeric.validator("12345abc").shouldBeFalse()
            }

            "with a complex pattern such as hex color code" {
                val isHexColor = Match(Regex("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$"))

                isHexColor.validator("#AABBCC").shouldBeTrue()
                isHexColor.validator("AABBCC").shouldBeFalse()
                isHexColor.validator("#GGHHII").shouldBeFalse()
            }
        }

        "should validate against various patterns using random strings" {
            checkAll(Arb.string(1..50)) { value ->
                val isLowercase = Match(Regex("^[a-z]+$"))
                val containsResult = isLowercase.validator(value)

                val expectedResult = value.all { it.isLowerCase() }
                containsResult shouldBe expectedResult
            }
        }
    }
})
