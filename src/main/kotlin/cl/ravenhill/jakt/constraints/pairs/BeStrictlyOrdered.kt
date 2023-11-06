package cl.ravenhill.jakt.constraints.pairs

/**
 * Represents a constraint ensuring a pair of comparable elements is in strict ascending order.
 *
 * This constraint checks if the first element of the pair is strictly less than the second element,
 * thus ensuring that they are in a strict ascending order. It is useful for validating sequences,
 * ranges, or any ordered pair where the first should be less than the second.
 *
 * @param A the type of the elements in the pair, which must be [Comparable].
 * @property validator A lambda function that checks if the first element is strictly less than the second.
 *
 * ## Usage
 * ### Example 1: Validating that a pair is strictly ordered
 * ```kotlin
 * val orderedPair = Pair(1, 2)
 * val isStrictlyOrdered = BeStrictlyOrdered<Int>().validator(orderedPair) // Returns `true`
 *
 * val unorderedPair = Pair(2, 1)
 * val isNotStrictlyOrdered = BeStrictlyOrdered<Int>().validator(unorderedPair) // Returns `false`
 * ```
 *
 * @author <a href="www.github.com/username">Your Name</a>
 * @since 1.0.0
 * @version 1.0.0
 */
class BeStrictlyOrdered<A : Comparable<A>> : PairConstraint<A, A> {
    override val validator = { value: Pair<A, A> -> value.first < value.second }
}
