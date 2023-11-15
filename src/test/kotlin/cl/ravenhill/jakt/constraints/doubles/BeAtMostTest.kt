package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.testfactories.`validate BeAtMostConstraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb

class BeAtMostTest : FreeSpec({

    include(`validate BeAtMostConstraint`(Arb.real(), ::BeAtMost))
})
