package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.`validate BeInRangeConstraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int


class BeInRangeTest : FreeSpec({
    include(`validate BeInRangeConstraint`(Arb.int(), ::BeInRange))
})