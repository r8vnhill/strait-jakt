package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.assertions.constraints.testBeAtMostConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb

class BeAtMostTest : FreeSpec({

    include(testBeAtMostConstraint(Arb.real(), ::BeAtMost))
})
