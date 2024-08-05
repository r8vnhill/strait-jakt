package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.assertions.constraints.validateBeInRangeConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb

class BeInRangeTest : FreeSpec({
    include(validateBeInRangeConstraint(Arb.real(), ::BeInRange))
})
