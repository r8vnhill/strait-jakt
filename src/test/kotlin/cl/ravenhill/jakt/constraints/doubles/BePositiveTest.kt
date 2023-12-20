package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.assertions.constraints.`test BePositive constraint`
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.positiveDouble


class BePositiveTest : FreeSpec({
    include(`test BePositive constraint`(Arb.positiveDouble(), Arb.double().filter { it <= 0 }) { BePositive })
})
