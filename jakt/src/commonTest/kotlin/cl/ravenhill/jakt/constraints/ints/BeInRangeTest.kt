package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.validateBeInRangeConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int


class BeInRangeTest : FreeSpec({
    include(validateBeInRangeConstraint(Arb.int(), ::BeInRange))
})