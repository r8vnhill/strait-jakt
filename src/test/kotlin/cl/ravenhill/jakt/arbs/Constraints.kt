package cl.ravenhill.jakt.arbs

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.choice
import io.kotest.property.arbitrary.element
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.nonNegativeInt

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

fun Arb.Companion.constraint() = choice(
    collectionBeEmpty(),
    collectionHaveElement(),
    collectionHaveSize(),
)

/**
 * Provides an arbitrary generator for the `BeEmpty` collection constraint.
 * This generator can be used in property-based testing to provide collection constraints
 * that validate if a collection is empty.
 *
 * ## Usage
 * ### Example 1: Using to create another [Arb]
 * ```kotlin
 * fun Arb.Companion.myArb() = arbitrary {
 *    val constraint = collectionBeEmpty()
 *    val value = listOf(1, 2, 3)
 *    constraint to value
 * }
 * ```
 *
 * ### Example 2: Using the generated constraint in tests
 * ```kotlin
 * checkAll(Arb.collectionBeEmpty(), Arb.list(Arb.int())) { constraint, value ->
 *    ...
 * }
 * ```
 *
 * @return An arbitrary of `BeEmpty` representing the collection constraint.
 */
fun Arb.Companion.collectionBeEmpty() = arbitrary { cl.ravenhill.jakt.constraints.collections.BeEmpty }

/**
 * Provides an arbitrary generator for the `HaveElement` collection constraint.
 * This generator can be used in property-based testing to provide collection constraints
 * that validate if a collection contains a specific element generated by an integer arbitrary.
 *
 * ## Usage
 * ### Example 1: Using to create another [Arb]
 * ```kotlin
 * fun Arb.Companion.myArb() = arbitrary {
 *   val constraint = collectionHaveElement()
 *   val value = listOf(1, 2, 3)
 *   constraint to value
 * }
 * ```
 *
 * ### Example 2: Using the generated constraint in tests
 * ```kotlin
 * checkAll(Arb.collectionHaveElement(), Arb.list(Arb.int())) { constraint, value ->
 *   ...
 * }
 * ```
 *
 * @return An arbitrary of `HaveElement` with a generated integer element.
 */
fun Arb.Companion.collectionHaveElement() =
    arbitrary { cl.ravenhill.jakt.constraints.collections.HaveElement(int().bind()) }

/**
 * Provides an arbitrary generator for the `HaveSize` collection constraint.
 * This generator can be used in property-based testing to provide collection constraints
 * that validate if a collection has a specific size generated by a non-negative integer arbitrary.
 *
 * ## Usage
 * ### Example 1: Using to create another [Arb]
 * ```kotlin
 * fun Arb.Companion.myArb() = arbitrary {
 *   val constraint = collectionHaveSize()
 *   val value = listOf(1, 2, 3)
 *   constraint to value
 * }
 * ```
 *
 * ### Example 2: Using the generated constraint in tests
 * ```kotlin
 * checkAll(Arb.collectionHaveSize(), Arb.list(Arb.int())) { constraint, value ->
 *   ...
 * }
 * ```
 *
 * @return An arbitrary of `HaveSize` with a generated size value.
 */
fun Arb.Companion.collectionHaveSize(size: Arb<Int> = nonNegativeInt()) =
    arbitrary { cl.ravenhill.jakt.constraints.collections.HaveSize(size.bind()) }
