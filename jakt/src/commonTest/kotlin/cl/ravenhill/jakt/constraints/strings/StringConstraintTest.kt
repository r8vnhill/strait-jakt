package cl.ravenhill.jakt.constraints.strings

import cl.ravenhill.jakt.exceptions.StringConstraintException
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.checkAll

class StringConstraintTest : FreeSpec({

    "A [StringConstraint]" - {
        "should be able to generate a [StringConstraintException]" {
            checkAll<String> { description ->
                shouldThrowWithMessage<StringConstraintException>(description) {
                    throw object : StringConstraint {
                        override val validator = { _: String -> false }
                    }.generateException(description)
                }
            }
        }
    }
})
