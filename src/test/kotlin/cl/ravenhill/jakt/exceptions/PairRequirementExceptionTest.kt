package cl.ravenhill.jakt.exceptions

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PairRequirementExceptionTest : FreeSpec({

    "A [PairRequirementException]" - {
        "should be able to be created with a lazy message" {
            val exception = PairRequirementException { "message" }
            exception.message shouldBe "message"
        }
    }
})
