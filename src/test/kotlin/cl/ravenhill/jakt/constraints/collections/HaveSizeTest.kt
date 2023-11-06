package cl.ravenhill.jakt.constraints.collections

import cl.ravenhill.jakt.arbs.datatypes.collection
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldNotHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.assume
import io.kotest.property.checkAll

class HaveSizeTest : FreeSpec({

    "A [HaveSize] constraint" - {
        "have a validator that" - {
            "returns true if the collection has the specified size" {
                checkAll(Arb.collection()) { collection ->
                    HaveSize(collection.size).validator(collection).shouldBeTrue()
                }
            }

            "returns false if the collection does not have the specified size" {
                checkAll(Arb.collection(), Arb.int()) { collection, size ->
                    assume { collection shouldNotHaveSize size }
                    HaveSize(size).validator(collection).shouldBeFalse()
                }
            }
        }
    }
})
