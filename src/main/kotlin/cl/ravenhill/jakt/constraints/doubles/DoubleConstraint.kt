/**
 * Copyright (c) 2023, R8V.
 * BSD Zero Clause License.
 */

package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.DoubleConstraintException

/**
 * Defines a contract for implementing constraints on values of type [Double].
 *
 * This interface extends [Constraint] specialized for `Double` type values. It overrides the
 * `generateException` method to produce a [DoubleConstraintException] which is designed specifically
 * for handling exceptions related to double constraints.
 *
 * ## Usage
 * ### Example: Implementing a custom constraint for doubles
 * ```kotlin
 * class DoublePositiveConstraint : DoubleConstraint {
 *     override val validator: (Double) -> Boolean = { it > 0.0 }
 * }
 * ```
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
interface DoubleConstraint : Constraint<Double> {

    override fun generateException(description: String) = DoubleConstraintException { description }
}
