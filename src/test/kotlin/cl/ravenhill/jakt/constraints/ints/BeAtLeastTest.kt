package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.testfactories.`test BeAtLeast validator`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int


class BeAtLeastTest : FreeSpec({
    "A Be At Least constraint" - {
        `test BeAtLeast validator`(Arb.int(), ::BeAtLeast)
    }
})

