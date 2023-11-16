package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.testfactories.`test BeAtLeast validator`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb

class BeAtLeastTest : FreeSpec({
    "A Be At Least constraint" - {
        `test BeAtLeast validator`(Arb.real(), ::BeAtLeast)
    }
})
