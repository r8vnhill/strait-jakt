package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.arbs.datatypes.orderedTriple
import cl.ravenhill.jakt.testfactories.`validate BeInRangeConstraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll


class BeInRangeTest : FreeSpec({
    include(`validate BeInRangeConstraint`(Arb.int(), ::BeInRange))
})