/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import cl.ravenhill.jakt.Jakt.Scope
import cl.ravenhill.jakt.Jakt.skipChecks
import cl.ravenhill.jakt.exceptions.CompositeException

fun <T> T.constrainedTo(builder: Scope.(T) -> Unit): Either<CompositeException, T> = if (skipChecks == Checks.SKIP) {
    this.right()
} else {
    Scope().apply { builder(this@constrainedTo) }
        .failures
        .takeIf(List<Throwable>::isNotEmpty)
        ?.let(::CompositeException)
        ?.left()
        ?: this.right()
}

inline fun constrained(builder: Scope.() -> Unit): Either<CompositeException, Unit> = if (skipChecks == Checks.SKIP) {
    Unit.right()
} else {
    Scope().apply(builder)
        .failures
        .takeIf(List<Throwable>::isNotEmpty)
        ?.let(::CompositeException)
        ?.left()
        ?: Unit.right()
}
