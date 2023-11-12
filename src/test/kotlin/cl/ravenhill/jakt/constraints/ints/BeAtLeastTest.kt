package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.testfactories.`validate BeAtLeastConstraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll


class BeAtLeastTest : FreeSpec({
    include(`validate BeAtLeastConstraint`(Arb.int(), ::BeAtLeast))
})

