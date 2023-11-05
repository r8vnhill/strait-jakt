package cl.ravenhill.jakt.exceptions

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class CompositeExceptionTest : FreeSpec({
    "A [CompositeException]" - {
        "should be able to be created with a list of exceptions" {
            checkAll(Arb.list(Arb.string())) { messages ->
                val exceptions = messages.map { Exception(it) }
                val exception = CompositeException(exceptions)
                exception.message shouldBe
                      "Multiple exceptions occurred: " +
                      messages.joinToString(", ") { "{ $it }" }
                exception.failures shouldBe exceptions
            }
        }
    }
})