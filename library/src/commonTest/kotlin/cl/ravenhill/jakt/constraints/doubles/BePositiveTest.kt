/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.assertions.constraints.testBePositiveConstraint
import io.kotest.core.spec.style.FreeSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.arbitrary.positiveDouble


class BePositiveTest : FreeSpec({
    include(testBePositiveConstraint(Arb.positiveDouble(), Arb.double().filter { it <= 0 }) { BePositive })
})
