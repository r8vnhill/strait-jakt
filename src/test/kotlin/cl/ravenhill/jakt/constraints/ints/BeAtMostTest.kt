package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.`test BeAtMost constraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int

class BeAtMostTest : FreeSpec({

    include(`test BeAtMost constraint`(Arb.int(), ::BeAtMost))
})
