/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

import cl.ravenhill.jakt.constraints.ints.BeAtLeast
import cl.ravenhill.jakt.constraints.ints.BeAtMost
import cl.ravenhill.jakt.constraints.ints.BeNegative
import cl.ravenhill.jakt.constraints.ints.BePositive
import cl.ravenhill.jakt.exceptions.CompositeException
import cl.ravenhill.jakt.exceptions.IntConstraintException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeInt
import io.kotest.property.arbitrary.nonNegativeInt
import io.kotest.property.checkAll

class ConstrainedToTest : FreeSpec({
    "Constraining a value" - {
        "should return the value if it's within the constraints" {
            checkAll(Arb.nonNegativeInt(100)) { value ->
                val result = value.constrainedTo {
                    "'$it' Must be greater than 0" { it must BeAtLeast(0) }
                    "'$it' Must be less than 100" { it must BeAtMost(100) }
                }
                result shouldBe value
            }
        }

        "should throw an exception if the value is not within the constraints" {
            checkAll(Arb.negativeInt()) { value ->
                val exception = shouldThrow<CompositeException> {
                    value.constrainedTo {
                        "'$it' Must be negative" { it must BeNegative }
                        "'$it' Must be positive" { it must BePositive }
                        "'$it' Must not be less than 0" { it must BeAtLeast(0) }
                    }
                }
                with(exception) {
                    throwables.size shouldBe 2
                    throwables[0].shouldBeInstanceOf<IntConstraintException>()
                        .shouldHaveMessage("'$value' Must be positive")
                    throwables[1].shouldBeInstanceOf<IntConstraintException>()
                        .shouldHaveMessage("'$value' Must not be less than 0")
                }
            }
        }
    }
})
