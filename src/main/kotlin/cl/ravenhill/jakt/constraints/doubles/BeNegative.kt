package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeNegativeConstraint

/**
 * Represents a constraint to validate that a [Double] value is negative.
 *
 * `BeNegative` is an object that implements both [DoubleConstraint] and [BeNegativeConstraint]`<Double>`, tailored for
 * validating `Double` values. It asserts that a given `Double` value is less than zero. Being a singleton data object,
 * it maintains a single, shared instance that can be reused across various validations where a negative `Double` value
 * is expected.
 *
 * ## Usage:
 * `BeNegative` is utilized in scenarios where it's essential to confirm that a `Double` value is negative, such as in
 * financial calculations, physical measurements, or other contexts where negative numbers hold specific significance.
 *
 * ### Example: Validating if a number is negative
 * ```
 * val isNegative = BeNegative.validator(-5.0)  // Returns `true`
 * val isNotNegative = BeNegative.validator(5.0) // Returns `false`
 * ```
 *
 * @property zero The baseline value (0.0) used for comparison in the validation, indicating
 *   that any `Double` value must be less than this to satisfy the constraint.
 */
data object BeNegative : DoubleConstraint, BeNegativeConstraint<Double> {
    override val zero: Double = 0.0
}
