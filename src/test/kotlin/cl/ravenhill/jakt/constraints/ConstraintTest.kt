package cl.ravenhill.jakt.constraints

import cl.ravenhill.jakt.arbs.constraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class ConstraintTest : FreeSpec({

    "A [Constraint]" - {
        "when validating" - {
            "a condition should return [Result.Success] for true and [Result.Failure] for false." {
                checkAll(Arb.int(), Arb.constraint(), Arb.string()) { value, constraint, message ->
                    if (constraint.validator(value)) {
                        constraint.validate(value, message).shouldBeSuccess()
                    } else {
                        constraint.validate(value, message).shouldBeFailure()
                    }
                }
            }

            "the negation of a condition should return [Result.Success] for false and [Result.Failure] for true." {
                checkAll(Arb.int(), Arb.constraint(), Arb.string()) { value, constraint, message ->
                    if (!constraint.validator(value)) {
                        constraint.validateNot(value, message).shouldBeSuccess()
                    } else {
                        constraint.validateNot(value, message).shouldBeFailure()
                    }
                }
            }
        }
    }
})
