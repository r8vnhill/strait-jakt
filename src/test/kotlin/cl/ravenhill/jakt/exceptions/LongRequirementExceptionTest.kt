package cl.ravenhill.jakt.exceptions

import cl.ravenhill.jakt.assertions.`check exception message consistency`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class LongRequirementExceptionTest : FreeSpec({

    "A [LongRequirementException]" - {
        "should be able to be created with a lazy message" {
            `check exception message consistency` { LongRequirementException { it } }
        }
    }
})
