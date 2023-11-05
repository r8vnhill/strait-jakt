package cl.ravenhill.jakt.exceptions

import cl.ravenhill.jakt.assertions.`check exception message consistency`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DoubleRequirementExceptionTest : FreeSpec({

    "A [DoubleRequirementException]" - {
        "should be able to be created with a lazy message" {
            `check exception message consistency` { DoubleRequirementException { it } }
        }
    }
})
