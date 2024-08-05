package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.testBePositiveConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.nonPositiveInt
import io.kotest.property.arbitrary.positiveInt

class BePositiveTest : FreeSpec({
    include(testBePositiveConstraint(Arb.positiveInt(), Arb.nonPositiveInt()) { BePositive })
})
