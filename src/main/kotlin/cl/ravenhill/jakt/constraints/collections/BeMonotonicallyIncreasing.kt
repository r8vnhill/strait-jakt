package cl.ravenhill.jakt.constraints.collections


/**
 * Represents a constraint that checks if a collection of [Comparable] elements is monotonically increasing.
 *
 * This constraint validates that each element in a collection is less than or equal to the next element, ensuring
 * the collection is monotonically increasing. It is useful in scenarios where the order and progression of elements
 * are critical, such as in sorted lists or sequences where each element must be equal to or greater than the previous.
 *
 * ## Usage
 * ### Example: Validating a monotonically increasing collection
 * ```kotlin
 * val isMonotonicallyIncreasing = BeMonotonicallyIncreasing.validator(listOf(1, 2, 2, 3)) // Returns `true`
 * val isNotMonotonicallyIncreasing = BeMonotonicallyIncreasing.validator(listOf(1, 3, 2))   // Returns `false`
 * ```
 *
 * @property validator A lambda function that takes a [Collection] of [Comparable] elements and returns a [Boolean]
 * indicating whether the collection is monotonically increasing.
 */
data class BeMonotonicallyIncreasing<T>(val strict: Boolean = false) : CollectionConstraint<T> where T : Comparable<T> {
    override val validator = { value: Collection<T> ->
        value.zipWithNext().all { (a, b) ->
            if (strict) a < b else a <= b
        }
    }
}
