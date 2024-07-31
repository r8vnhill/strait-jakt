package cl.ravenhill.jakt.constraints.collections

import cl.ravenhill.jakt.arbs.datatypes.anyPrimitive
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.long
import io.kotest.property.assume
import io.kotest.property.checkAll
import kotlin.random.Random

@OptIn(ExperimentalKotest::class)
class HaveElementTest : FreeSpec({

    "A [HaveElement] constraint" - {
        "have a validator that" - {
            "returns true if the collection contains the element" {
                checkAll(Arb.list(Arb.anyPrimitive(), 1..100), Arb.long()) { list, seed ->
                    val element = list.random(Random(seed))
                    HaveElement(element).validator(list).shouldBeTrue()
                }
            }

            "returns false if the collection does not contain the element" {
                checkAll(PropTestConfig(iterations = 50),
                    Arb.list(Arb.anyPrimitive(), 0..50), Arb.anyPrimitive()) { list, element ->
                    assume { list shouldNotContain element }
                    HaveElement(element).validator(list).shouldBeFalse()
                }
            }

            "return false if the collection is empty" {
                checkAll(Arb.anyPrimitive()) { element ->
                    HaveElement(element).validator(emptyList<Nothing>()).shouldBeFalse()
                }
            }
        }
    }
})
