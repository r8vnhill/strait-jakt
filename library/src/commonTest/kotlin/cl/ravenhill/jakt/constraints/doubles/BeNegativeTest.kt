package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.assertions.constraints.testBeNegativeConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeDouble
import io.kotest.property.arbitrary.positiveDouble

class BeNegativeTest : FreeSpec({

    include(testBeNegativeConstraint(Arb.negativeDouble(), Arb.positiveDouble()) { BeNegative })
})
