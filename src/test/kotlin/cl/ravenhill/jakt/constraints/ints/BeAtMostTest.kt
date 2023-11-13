package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.testfactories.`validate BeAtMostConstraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int

class BeAtMostTest : FreeSpec({

    include(`validate BeAtMostConstraint`(Arb.int(), ::BeAtMost))
})
