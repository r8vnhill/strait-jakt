package cl.ravenhill.jakt.arbs.datatypes

import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.choice
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.set

/**
 * Provides an arbitrary generator for producing collections of primitive types.
 *
 * This function is an extension to the `Arb.Companion` and creates a generator capable of producing
 * either lists or sets containing primitive types. The types included in the collections are those
 * generated by the [anyPrimitive] function, ensuring that the collections consist of fundamental
 * Kotlin data types.
 *
 * ## Examples
 * ### Example 1: Using in property-based tests
 * ```kotlin
 * checkAll(Arb.collection()) { collection ->
 *     // Some test logic with the generated collection
 * }
 * ```
 *
 * @return An [Arb] that generates either a [List] or a [Set] containing primitive types.
 */
fun Arb.Companion.collection() = choice(
    list(Arb.anyPrimitive()),
    set(Arb.anyPrimitive())
)

/**
 * Provides an arbitrary generator of ordered pairs of comparable values.
 *
 * This function is an extension to the `Arb.Companion` and produces a generator of ordered pairs of type [T].
 * The pairs are created from two arbitrary sources `a` and `b` of the same type [T]. The ordering can be controlled
 * by the `strict` and `reverted` parameters.
 *
 * ## Usage
 * ### Example 1: Using to create another arbitrary
 * ```kotlin
 * fun <T> Arb.Companion.tree(gen: Arb<T>, maxDepth: IntRange = 1..5) = arbitrary {
 *     val (lo, hi) = orderedPair(int(maxDepth), int(maxDepth), strict = true).bind()
 *     ...
 * }
 * ```
 *
 * ### Example 2: Using in property-based tests
 * ```kotlin
 * checkAll(Arb.orderedPair(int(), int(), strict = true)) { (lo, hi) ->
 *    // Some test logic with the generated pair
 * }
 * ```
 *
 * @param a An [Arb] source for the first value in the pair.
 * @param b An [Arb] source for the second value in the pair.
 * @param strict If set to `true`, ensures that the generated values are distinct.
 * @param reverted If set to `true`, produces pairs where the first value is greater than the second value.
 *
 * @return An [Arb] that generates ordered pairs of type [T].
 */
fun <T: Comparable<T>> Arb.Companion.orderedPair(
    a: Arb<T>,
    b: Arb<T>,
    strict: Boolean = false,
    reverted: Boolean = false
) = arbitrary {
    val i = a.bind()
    var j = b.bind()

    while (strict && i == j) {
        j = b.bind() // Re-bind `j` until it is different from `i` if `strict` is `true`
    }

    val isOrdered = i < j
    val shouldRevert = isOrdered && reverted || !isOrdered && !reverted

    if (shouldRevert) j to i else i to j
}
