package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.exceptions.IntConstraintException
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.checkAll


class IntConstraintTest : FreeSpec({

    "An [IntConstraint]" - {
        "should be able to generate an [IntConstraintException]" {
            checkAll<String> { description ->
                shouldThrowWithMessage<IntConstraintException>(description) {
                    throw object : IntConstraint {
                        override val validator = { _: Int -> false }
                    }.generateException(description)
                }
            }
        }
    }
})
