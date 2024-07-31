package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.assertions.constraints.`test BeNegative constraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeInt
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.checkAll

class BeNegativeTest : FreeSpec({

    include(`test BeNegative constraint`(Arb.negativeInt(), Arb.positiveInt()) { BeNegative })
})
