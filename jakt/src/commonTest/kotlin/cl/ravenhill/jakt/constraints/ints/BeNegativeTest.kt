package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.testBeNegativeConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeInt
import io.kotest.property.arbitrary.positiveInt

class BeNegativeTest : FreeSpec({

    include(testBeNegativeConstraint(Arb.negativeInt(), Arb.positiveInt()) { BeNegative })
})
