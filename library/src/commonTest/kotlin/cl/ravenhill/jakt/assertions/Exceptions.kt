package cl.ravenhill.jakt.assertions

import cl.ravenhill.jakt.exceptions.ConstraintException
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

/**
 * Checks the consistency of the message in the generated exception using property-based testing.
 * The function tests with different string values to ensure the exception message always matches the input string.
 *
 * @param exceptionBuilder A lambda function that takes a string and returns an exception of type `ConstraintException`
 *                         with that string as its message.
 * @throws AssertionError if the exception message does not match the input string.
 */
suspend inline fun `check exception message consistency`(
    crossinline exceptionBuilder: (String) -> ConstraintException
) {
    checkAll(Arb.string()) { message ->
        val exception = exceptionBuilder(message)
        exception.message shouldBe message
    }
}

