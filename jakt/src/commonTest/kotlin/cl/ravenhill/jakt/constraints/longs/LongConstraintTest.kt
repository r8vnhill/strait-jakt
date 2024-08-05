package cl.ravenhill.jakt.constraints.longs

import cl.ravenhill.jakt.exceptions.LongConstraintException
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.checkAll

class LongConstraintTest : FreeSpec({

    "A [LongConstraint]" - {
        "should be able to generate a [LongConstraintException]" {
            checkAll<String> { description ->
                shouldThrowWithMessage<LongConstraintException>(description) {
                    throw object : LongConstraint {
                        override val validator = { _: Long -> false }
                    }.generateException(description)
                }
            }
        }
    }
})
