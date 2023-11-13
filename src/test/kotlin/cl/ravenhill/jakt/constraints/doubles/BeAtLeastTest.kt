package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.testfactories.`validate BeAtLeast constraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb

class BeAtLeastTest : FreeSpec({

    include(`validate BeAtLeast constraint`(Arb.real(), ::BeAtLeast))
})
