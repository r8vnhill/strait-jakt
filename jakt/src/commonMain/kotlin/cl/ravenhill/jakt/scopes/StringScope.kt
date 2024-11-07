/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.scopes

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import cl.ravenhill.jakt.rules.Rule

class StringScope<E>(val message: String) where E : Throwable {
    var exceptionGenerator: ((String) -> E)? = null

    infix fun <T, R> T.must(constraint: R): Either<E, T>
            where R : Rule<T, E> =
        validate(constraint, shouldPass = true)

    infix fun <T, R> T.mustNot(constraint: R): Either<E, T>
            where R : Rule<T, E> =
        validate(constraint, shouldPass = false)

    private fun <T, C> T.validate(constraint: C, shouldPass: Boolean): Either<E, T>
            where C : Rule<T, E> =
        if (constraint.validator(this) == shouldPass) {
            this.right()
        } else {
            val exception = exceptionGenerator?.invoke(message) ?: constraint.generateException(message)
            exception.left()
        }
}
