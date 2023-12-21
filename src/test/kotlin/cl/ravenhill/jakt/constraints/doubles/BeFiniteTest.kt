package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.ExperimentalJakt
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.collection

@OptIn(ExperimentalJakt::class)
class BeFiniteTest : FreeSpec({

    "A Be Finite constraint" - {
        "should have a validator that" - {
            "returns true if the value is finite" {
                checkAll(Arb.double().filter { it.isFinite() }) { value ->
                    BeFinite.validator(value).shouldBe(true)
                }
            }

            "returns false if the value is not finite" {
                checkAll(
                    Exhaustive.collection(
                        listOf(Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)
                    )
                ) { value ->
                    BeFinite.validator(value).shouldBe(false)
                }
            }
        }
    }
})
