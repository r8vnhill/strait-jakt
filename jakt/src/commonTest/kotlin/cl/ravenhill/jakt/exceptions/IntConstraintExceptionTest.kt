package cl.ravenhill.jakt.exceptions

import cl.ravenhill.jakt.assertions.checkExceptionMessageConsistency
import io.kotest.core.spec.style.FreeSpec

class IntConstraintExceptionTest : FreeSpec({

    "An [IntConstraintException]" - {
        "should be able to be created with a lazy message" {
            checkExceptionMessageConsistency { IntConstraintException { it } }
        }
    }
})
