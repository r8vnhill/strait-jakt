package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.testfactories.`validate BeInRangeConstraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb

class BeInRangeTest : FreeSpec({
    include(`validate BeInRangeConstraint`(Arb.real(), ::BeInRange))
})
