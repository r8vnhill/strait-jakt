package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.ExperimentalJakt

/**
 * Represents a constraint to validate that a [Double] value is NaN (Not-a-Number).
 *
 * Annotated with `@ExperimentalJakt`, this indicates that `BeNaN` is an experimental feature within the library and
 * may be subject to changes in future versions. `BeNaN` implements the [DoubleConstraint] interface and is
 * specifically designed to check if a `Double` value is NaN.
 *
 * The implementation leverages the [isNaN] extension function on `Double` to perform the validation.
 *
 * ## Usage:
 * The `BeNaN` constraint is particularly useful in numerical computations and scenarios where NaN values can occur,
 * such as in calculations that result in undefined or unrepresentable values. This constraint provides a
 * straightforward way to detect these cases.
 *
 * ### Example: Validating if a number is NaN
 * ```
 * val isNan = BeNaN.validator(Double.NaN)  // Returns `true`
 * val isNotNan = BeNaN.validator(5.0)      // Returns `false`
 * ```
 *
 * @property validator A lambda function that takes a `Double` value and returns `true` if it is NaN,
 *   or `false` otherwise. This property offers an efficient way to validate against the NaN condition.
 */
@ExperimentalJakt
data object BeNaN : DoubleConstraint {
    override val validator: (Double) -> Boolean = Double::isNaN
}
