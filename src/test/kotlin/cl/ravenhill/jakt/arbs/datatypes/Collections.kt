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

/**
 * Provides an arbitrary generator of ordered pairs of values of type [T] using a single arbitrary generator.
 *
 * This function produces pairs `(i, j)` such that:
 * - If `reverted` is `false` (default), `i <= j`.
 * - If `reverted` is `true`, `i >= j`.
 *
 * If the `strict` parameter is set to `true`, the values of `i` and `j` will be distinct.
 *
 * The convenience of this function lies in its ability to take a single arbitrary generator and produce ordered pairs
 * from it. It essentially delegates the task to the more general `orderedPair` function by passing the same generator
 * for both values.
 *
 * ## Usage
 * ### Example 1: Creating another arbitrary
 * ```kotlin
 * fun <T> Arb.Companion.tree(gen: Arb<T>, maxDepth: IntRange = 1..5) = arbitrary {
 *    val (lo, hi) = orderedPair(int(maxDepth), strict = true).bind()
 *    ...
 * }
 * ```
 *
 * ### Example 2: Using in property-based tests
 * ```kotlin
 * checkAll(Arb.orderedPair(Arb.int(), strict = true)) { (lo, hi) ->
 *   // Some test logic with the generated pair
 * }
 * ```
 *
 * @param gen Arbitrary generator used for both values of the pair.
 * @param strict If set to `true`, ensures both values in the pair are distinct.
 * @param reverted If set to `true`, the pair will be in decreasing order. Otherwise, it will be in increasing order.
 *
 * @return An arbitrary generator producing ordered pairs of values of type [T].
 */
fun <T : Comparable<T>> Arb.Companion.orderedPair(
    gen: Arb<T>,
    strict: Boolean = false,
    reverted: Boolean = false
) = orderedPair(gen, gen, strict, reverted)

/**
 * Provides an arbitrary generator of ordered triples of values of type [T].
 *
 * This function produces triples `(i, j, k)` such that:
 * - If `reverted` is `false` (default), `i <= j <= k`.
 * - If `reverted` is `true`, `i >= j >= k`.
 *
 * If the `strict` parameter is set to `true`, the values of `i`, `j`, and `k` will be distinct.
 *
 * ## Usage
 * ### Example 1: Using to create another arbitrary
 * ```kotlin
 * fun Arb.Companion.rangePair(gen: Arb<Int>) = arbitrary {
 *   val (lo, mid, hi) = orderedTriple(gen, gen, gen, strict = true).bind()
 *   lo..mid to mid..hi
 * }
 * ```
 *
 * ### Example 2: Using in property-based tests
 * ```kotlin
 * checkAll(Arb.orderedTriple(int(), int(), int(), strict = true)) { (lo, mid, hi) ->
 *   // Some test logic with the generated triple
 * }
 * ```
 *
 * @param a Arbitrary generator for the first value of the triple.
 * @param b Arbitrary generator for the second value of the triple.
 * @param c Arbitrary generator for the third value of the triple.
 * @param strict If set to `true`, ensures all three values in the triple are distinct.
 * @param reverted If set to `true`, the triple will be in decreasing order. Otherwise, it will be in increasing order.
 *
 * @return An arbitrary generator producing ordered triples of values of type [T].
 */
fun <T : Comparable<T>> Arb.Companion.orderedTriple(
    a: Arb<T>,
    b: Arb<T>,
    c: Arb<T>,
    strict: Boolean = false,
    reverted: Boolean = false
) = arbitrary {
    val i = a.bind()
    var j = b.bind()
    var k = c.bind()

    while (strict && i == j) {
        j = b.bind() // Re-bind `j` until it is different from `i` if `strict` is `true`
    }

    while (strict && (i == k || j == k)) {
        k = c.bind() // Re-bind `k` until it is different from `i` and `j` if `strict` is `true`
    }

    val sortedTriple = if (!reverted) {
        listOf(i, j, k).sorted()
    } else {
        listOf(i, j, k).sortedDescending()
    }
    Triple(sortedTriple[0], sortedTriple[1], sortedTriple[2])
}

/**
 * Provides an arbitrary generator of ordered triples of values of type [T], where all three values are generated using
 * the same arbitrary generator.
 *
 * This function is a convenience overload of the [orderedTriple] function where the same arbitrary generator is used
 * for all three values.
 * It produces triples `(i, j, k)` such that:
 * - If `reverted` is `false` (default), `i <= j <= k`.
 * - If `reverted` is `true`, `i >= j >= k`.
 *
 * If the `strict` parameter is set to `true`, the values of `i`, `j`, and `k` will be distinct.
 *
 * ## Usage
 * ### Example 1: Using to create another arbitrary
 * ```kotlin
 * fun Arb.Companion.rangePair(gen: Arb<Int>) = arbitrary {
 *   val (lo, mid, hi) = orderedTriple(gen, strict = true).bind()
 *   lo..mid to mid..hi
 * }
 * ```
 *
 * ### Example 2: Using in property-based tests
 * ```kotlin
 * checkAll(Arb.orderedTriple(int(), strict = true)) { (lo, mid, hi) ->
 *   // Some test logic with the generated triple
 * }
 * ```
 *
 * @param gen Arbitrary generator used for all three values of the triple.
 * @param strict If set to `true`, ensures all three values in the triple are distinct.
 * @param reverted If set to `true`, the triple will be in decreasing order. Otherwise, it will be in increasing order.
 *
 * @return An arbitrary generator producing ordered triples of values of type [T].
 */
fun <T : Comparable<T>> Arb.Companion.orderedTriple(
    gen: Arb<T>,
    strict: Boolean = false,
    reverted: Boolean = false
) = orderedTriple(gen, gen, gen, strict, reverted)
