package cl.ravenhill.jakt.constraints.collections


/**
 * Represents a constraint that checks if a collection of [Comparable] elements is monotonically decreasing.
 *
 * This constraint validates that each element in a collection is greater than (or equal to, if not strict)
 * the next element. It's particularly useful for validating collections where the order of elements is expected
 * to be in a descending sequence, such as in sorted arrays or certain algorithmic outputs.
 *
 * The `strict` parameter determines whether the comparison between consecutive elements should be strictly
 * greater than (`strict = true`) or greater than or equal to (`strict = false`).
 *
 * ## Usage
 * ### Example: Validating a monotonically decreasing collection
 * ```kotlin
 * val isStrictlyDecreasing = BeMonotonicallyDecreasing<Int>(true)
 * val isValidStrict = isStrictlyDecreasing.validator(listOf(5, 4, 3, 2, 1)) // Returns `true`
 * val isInvalidStrict = isStrictlyDecreasing.validator(listOf(5, 4, 4, 3))   // Returns `false`
 *
 * val isNonStrictlyDecreasing = BeMonotonicallyDecreasing<Int>(false)
 * val isValidNonStrict = isNonStrictlyDecreasing.validator(listOf(5, 5, 4, 4, 3)) // Returns `true`
 * val isInvalidNonStrict = isNonStrictlyDecreasing.validator(listOf(5, 6, 5, 4))  // Returns `false`
 * ```
 *
 * @param T The type of elements in the collection, which must be [Comparable].
 * @param strict A boolean indicating whether the comparison is strict (true for strictly greater than,
 * false for greater than or equal to).
 * @property validator A lambda function that takes a [Collection] of [T] and returns a [Boolean] indicating
 * whether the collection is monotonically decreasing according to the specified strictness.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.2.0
 * @version 1.2.0
 */
data class BeMonotonicallyDecreasing<T>(val strict: Boolean = false) : CollectionConstraint<T> where T : Comparable<T> {
    override val validator = { value: Collection<T> ->
        value.zipWithNext().all { (a, b) ->
            if (strict) {
                a > b
            } else {
                a >= b
            }
        }
    }
}
