package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.ExperimentalJakt

/**
 * Represents a constraint to validate that a [Double] value is infinite.
 *
 * Marked with the `@ExperimentalJakt` annotation, `BeInfinite` signifies that this is an experimental feature within
 * the library and may undergo changes in the future. This data object implements the [DoubleConstraint] interface,
 * specifically designed for validating whether a `Double` value is either positive infinity or negative infinity.
 *
 * The implementation uses the [isInfinite] extension function on `Double` to perform the validation.
 *
 * ## Usage:
 * `BeInfinite` is useful in scenarios where the concept of infinity is a valid or expected state, such as in
 * mathematical computations, simulations, or scenarios where overflow might be represented by infinite values.
 *
 * ### Example: Validating if a number is infinite
 * ```
 * val isInfinite = BeInfinite.validator(Double.POSITIVE_INFINITY)  // Returns `true`
 * val isFinite = BeInfinite.validator(5.0)                        // Returns `false`
 * ```
 *
 * @property validator A lambda function that takes a `Double` value and returns `true` if it is infinite (either
 *   positive or negative infinity), or `false` otherwise. This property provides a concise and efficient means of
 *   validating infinity.
 */
@ExperimentalJakt
data object BeInfinite : DoubleConstraint {
    override val validator: (Double) -> Boolean get() = Double::isInfinite
}
