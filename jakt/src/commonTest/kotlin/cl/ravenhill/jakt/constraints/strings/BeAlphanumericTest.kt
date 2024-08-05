/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.strings

import cl.ravenhill.jakt.arbs.datatypes.nonAlphanumeric
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldNotBeIn
import io.kotest.property.Arb
import io.kotest.property.arbitrary.Codepoint
import io.kotest.property.arbitrary.alphanumeric
import io.kotest.property.arbitrary.asString
import io.kotest.property.arbitrary.ascii
import io.kotest.property.arbitrary.string
import io.kotest.property.assume
import io.kotest.property.checkAll

class BeAlphanumericTest : FreeSpec({

    "BeAlphanumeric constraint" - {
        "should validate alphanumeric strings correctly" {
            checkAll(Arb.string(0..25, Codepoint.alphanumeric())) { value ->
                BeAlphanumeric.validator(value).shouldBeTrue()
            }
        }

        "should invalidate non-alphanumeric strings" {
            checkAll(Arb.string(1..25, Codepoint.nonAlphanumeric())) { value ->
                BeAlphanumeric.validator(value).shouldBeFalse()
            }
        }
    }
})

