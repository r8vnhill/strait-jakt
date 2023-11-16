package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.`test BeAtLeast constraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int


class BeAtLeastTest : FreeSpec({
    include(`test BeAtLeast constraint`(Arb.int(), ::BeAtLeast))
})
