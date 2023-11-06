package cl.ravenhill.jakt.constraints.pairs

import cl.ravenhill.jakt.exceptions.PairConstraintException
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.pair
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class PairConstraintTest : FreeSpec({

    "A [PairConstraint]" - {
        "should be able to generate a PairConstraintException" - {
            checkAll(Arb.string()) { description ->
                shouldThrowWithMessage<PairConstraintException>(description) {
                    throw object : PairConstraint<Int, Int> {
                        override val validator = { _: Pair<Int, Int> -> false }
                    }.generateException(description)
                }
            }
        }
    }
})

