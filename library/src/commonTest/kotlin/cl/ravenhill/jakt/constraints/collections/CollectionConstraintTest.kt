package cl.ravenhill.jakt.constraints.collections

import cl.ravenhill.jakt.exceptions.CollectionConstraintException
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll


class CollectionConstraintTest : FreeSpec({
    "A [CollectionConstraint]" - {
        "should be able to generate an exception" {
            checkAll(Arb.string()) { description ->
                shouldThrowWithMessage<CollectionConstraintException>(description) {
                    throw object : CollectionConstraint<Any?> {
                        override val validator = { _: Collection<Any?> -> false }
                    }.generateException(description)
                }
            }
        }
    }
})