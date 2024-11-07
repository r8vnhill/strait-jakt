/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.scopes

class JaktScope1<T> where T : Throwable {
    inline fun String.invoke(
        noinline exceptionGenerator: ((String) -> T)? = null,
        predicate: StringScope<T>.() -> Unit
    ) {
        StringScope<T>(this).apply {
            exceptionGenerator?.let {
                this.exceptionGenerator = it
            }
            predicate()
        }
    }
}
