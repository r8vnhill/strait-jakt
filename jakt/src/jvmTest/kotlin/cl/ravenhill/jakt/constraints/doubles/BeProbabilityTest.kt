/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.checkAll

class BeProbabilityTest : FreeSpec({

    "A BeProbability constraint" - {
        "when checking a valid probability" - {
            "should return true" {
                checkAll(arbProbability()) { probability ->
                    BeProbability.validator(probability).shouldBeTrue()
                }
            }
        }

        "when checking an invalid probability" - {
            "should return false" {
                checkAll(arbInvalidProbability()) { probability ->
                    BeProbability.validator(probability).shouldBeFalse()
                }
            }
        }
    }
})

private fun arbProbability(): Arb<Double> = Arb.double(0.0, 1.0, includeNonFiniteEdgeCases = false)

private fun arbInvalidProbability(): Arb<Double> = Arb.double().filter { it !in 0.0..1.0 }
