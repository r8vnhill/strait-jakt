/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions.aggregates

data class ExceptionAggregate4<T1, T2, T3, T4>(
    val cause1: T1,
    val cause2: T2,
    val cause3: T3,
    val cause4: T4
) : ExceptionAggregate(createMessage(listOf(cause1, cause2, cause3, cause4)))
        where T1 : Throwable,
              T2 : Throwable,
              T3 : Throwable,
              T4 : Throwable
