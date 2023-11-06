package cl.ravenhill.jakt.constraints.collections

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.checkAll

class BeEmptyTest : FreeSpec({

    "A [BeEmpty] constraint" - {
        "have a validator that" - {
            "returns true if the collection is empty" {
                BeEmpty.validator(emptyList<Nothing>()) shouldBe true
            }

            "returns false if the collection is not empty" {
                checkAll(Arb.list(Arb.int(), 1..100)) {
                    BeEmpty.validator(it) shouldBe false
                }
            }
        }
    }
})
