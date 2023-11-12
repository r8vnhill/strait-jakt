package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.testfactories.`validate BeAtLeastConstraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb

class BeAtLeastTest : FreeSpec({

    include(`validate BeAtLeastConstraint`(Arb.real(), ::BeAtLeast))
})
