package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.assertions.constraints.testBeAtLeastConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb

class BeAtLeastTest : FreeSpec({
    include(testBeAtLeastConstraint(Arb.real(), ::BeAtLeast))
})
