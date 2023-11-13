package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.testfactories.`validate BeAtLeast constraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int


class BeAtLeastTest : FreeSpec({
    include(`validate BeAtLeast constraint`(Arb.int(), ::BeAtLeast))
})

