package cl.ravenhill.jakt.constraints.strings

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.stringPattern
import io.kotest.property.checkAll

class BeEmptyTest : FreeSpec({

    "BeEmpty constraint" - {
        "should validate that a string is empty" {
            BeEmpty.validator("").shouldBeTrue()
        }

        "should validate that a non-empty string is not empty" {
            checkAll(Arb.stringPattern(".+")) { nonEmptyString ->
                BeEmpty.validator(nonEmptyString).shouldBeFalse()
            }
        }
    }
})