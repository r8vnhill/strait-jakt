package cl.ravenhill.jakt.exceptions

import cl.ravenhill.jakt.arbs.validator
import cl.ravenhill.jakt.assertions.`check exception message consistency`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb

class CollectionRequirementExceptionTest : FreeSpec({

    "A [CollectionRequirementException]" - {
        "should be able to be created with a lazy message" {
            `check exception message consistency` { CollectionRequirementException { it } }
        }
    }
})
