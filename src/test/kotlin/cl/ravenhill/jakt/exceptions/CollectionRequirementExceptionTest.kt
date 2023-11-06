package cl.ravenhill.jakt.exceptions

import cl.ravenhill.jakt.assertions.`check exception message consistency`
import io.kotest.core.spec.style.FreeSpec

class CollectionRequirementExceptionTest : FreeSpec({

    "A [CollectionRequirementException]" - {
        "should be able to be created with a lazy message" {
            `check exception message consistency` { CollectionConstraintException { it } }
        }
    }
})
