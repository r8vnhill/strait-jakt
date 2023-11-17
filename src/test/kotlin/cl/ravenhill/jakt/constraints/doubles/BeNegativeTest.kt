package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.assertions.constraints.`test BeNegative constraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeDouble
import io.kotest.property.arbitrary.positiveDouble

class BeNegativeTest : FreeSpec({

    include(`test BeNegative constraint`(Arb.negativeDouble(), Arb.positiveDouble()) { BeNegative })
})
