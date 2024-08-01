/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BePositiveConstraint
import cl.ravenhill.jakt.constraints.doubles.BePositive.zero

/**
 * Represents a constraint to validate that a [Double] value is positive.
 *
 * Marked with the `@ExperimentalJakt` annotation, indicating that `BePositive` is part of the experimental API
 * and may be subject to changes in future releases. It implements both [DoubleConstraint] and
 * [BePositiveConstraint]<Double>, specifically designed for validating `Double` values to ensure they are
 * greater than zero.
 *
 * As a data object, `BePositive` is a singleton, providing a shared instance that can be used across different
 * parts of an application where positive `Double` values need to be validated.
 *
 * ## Usage:
 * This constraint is particularly useful in scenarios where only positive `Double` values are valid, such as
 * in financial calculations, measurements, or other contexts where negative numbers are not permissible.
 *
 * ### Example: Validating if a number is positive
 * ```
 * val isPositive = BePositive.validator(5.0)  // Returns `true`
 * val isNotPositive = BePositive.validator(-5.0) // Returns `false`
 * ```
 *
 * @property zero The baseline value (0.0) used for comparison in the validation. It serves as the threshold
 *   to determine if a `Double` value is positive, i.e., any `Double` value must be greater than this to
 *   satisfy the constraint.
 */
data object BePositive : DoubleConstraint, BePositiveConstraint<Double> {
    override val zero = 0.0
}
