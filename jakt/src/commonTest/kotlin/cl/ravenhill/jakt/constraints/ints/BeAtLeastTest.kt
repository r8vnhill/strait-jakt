package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.testBeAtLeastConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int


class BeAtLeastTest : FreeSpec({
    include(testBeAtLeastConstraint(Arb.int(), ::BeAtLeast))
})
