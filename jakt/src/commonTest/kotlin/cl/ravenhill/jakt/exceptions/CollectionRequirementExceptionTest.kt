package cl.ravenhill.jakt.exceptions

import cl.ravenhill.jakt.assertions.checkExceptionMessageConsistency
import io.kotest.core.spec.style.FreeSpec

class CollectionRequirementExceptionTest : FreeSpec({

    "A [CollectionRequirementException]" - {
        "should be able to be created with a lazy message" {
            checkExceptionMessageConsistency { CollectionConstraintException { it } }
        }
    }
})
