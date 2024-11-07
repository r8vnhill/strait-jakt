package cl.ravenhill.jakt.rules.ints

import cl.ravenhill.jakt.assertions.constraints.testBeAtMostConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int

class BeAtMostTest : FreeSpec({

    include(testBeAtMostConstraint(Arb.int(), ::BeAtMost))
})
