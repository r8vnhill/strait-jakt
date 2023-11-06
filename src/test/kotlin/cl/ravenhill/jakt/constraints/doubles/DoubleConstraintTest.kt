package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.exceptions.DoubleConstraintException
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.checkAll


class DoubleConstraintTest : FreeSpec({

    "A [DoubleConstraint]" - {
        "should be able to generate a [DoubleConstraintException]" {
            checkAll<String> { description ->
                shouldThrowWithMessage<DoubleConstraintException>(description) {
                    throw object : DoubleConstraint {
                        override val validator = { _: Double -> false }
                    }.generateException(description)
                }
            }
        }
    }
})