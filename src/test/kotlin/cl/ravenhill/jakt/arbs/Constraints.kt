package cl.ravenhill.jakt.arbs

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.constraints.DummyConstraint
import cl.ravenhill.jakt.exceptions.ConstraintException
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.element
import io.kotest.property.arbitrary.next

/**
 * Provides an arbitrary generator of integer validator functions.
 *
 * This function is an extension to the `Arb.Companion` and produces a generator of common integer validation functions.
 * These validator functions can be employed in property-based tests or any other context requiring random validator
 * selection.
 *
 * The validators generated are as follows:
 * 1. A function that always returns `true`.
 * 2. A function that always returns `false`.
 * 3. A function that checks if the integer is even.
 * 4. A function that checks if the integer is odd.
 * 5. A function that checks if the integer is non-negative.
 *
 * ## Examples
 * ### Example 1: Using to create another [Arb]
 * ```kotlin
 * fun Arb.Companion.myArb() = arbitrary {
 *     val validator = validator()
 *     val value = int()
 *     validator to value
 * }
 * ```
 *
 * ### Example 2: Using in property-based tests
 * ```kotlin
 * checkAll(Arb.validator(), Arb.int()) { validator, value ->
 *    ...
 * }
 * ```
 *
 * @return An [Arb] that generates validator functions taking an [Int] and returning a [Boolean].
 */
fun Arb.Companion.validator() = element(
    { _: Int -> true },
    { _: Int -> false },
    { i: Int -> i % 2 == 0 },
    { i: Int -> i % 2 != 0 },
    { i: Int -> i >= 0 }
)

/**
 * Provides an arbitrary generator of [DummyConstraint] instances for integers.
 *
 * This function is an extension to the `Arb.Companion` and produces a generator of [DummyConstraint] instances
 * using randomly selected validator functions.
 *
 * ## Examples
 * ### Example 1: Using to create another [Arb]
 * ```kotlin
 * fun Arb.Companion.myArb() = arbitrary {
 *    val constraint = constraint()
 *    val value = int()
 *    constraint to value
 * }
 * ```
 *
 * ### Example 2: Using the generated constraint in tests
 * ```kotlin
 * checkAll(Arb.constraint(), Arb.int()) { constraint, value ->
 *     ...
 * }
 * ```
 *
 * @return An [Arb] that generates instances of [DummyConstraint] using random validator functions.
 */
fun Arb.Companion.constraint() = arbitrary {
    DummyConstraint(validator().bind())
}
