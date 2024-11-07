/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.rules


interface Rule<T, E> where E : Throwable {

    val validator: (T) -> Boolean

    fun generateException(description: String): E
}
