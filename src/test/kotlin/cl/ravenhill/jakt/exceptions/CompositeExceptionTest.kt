package cl.ravenhill.jakt.exceptions

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import java.lang.IllegalArgumentException

class CompositeExceptionTest : FreeSpec({

    "A [CompositeException]" - {
        "should be able to be created with a list of exceptions" {
            checkAll(Arb.list(Arb.string(), 2..50)) { messages ->
                val exceptions = messages.map { Exception(it) }
                val exception = CompositeException(exceptions)
                exception.message shouldBe
                      "Multiple exceptions occurred: " +
                      messages.joinToString(", ") { "{ $it }" }
                exception.throwables shouldBe exceptions
            }
        }

        "should be able to be created with a single exception" {
            checkAll(Arb.string()) { message ->
                val exception = Exception(message)
                val composite = CompositeException(listOf(exception))
                composite.message shouldBe "An exception occurred: $message"
                composite.throwables shouldBe listOf(exception)
            }
        }

        "should throw an exception if the list of exceptions is empty" {
            shouldThrowWithMessage<IllegalArgumentException>(
                "The list of throwables cannot be empty"
            ) {
                CompositeException(emptyList())
            }
        }
    }
})