/**
 * Copyright (c) 2023, R8V.
 * BSD Zero Clause License.
 */

/**
 * Copyright (c) 2023, R8V.
 * BSD Zero Clause License.
 */


package cl.ravenhill.jakt.constraints.longs

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.LongConstraintException

/**
 * Represents a constraint specific to `Long` data type values.
 *
 * This interface extends the [Constraint] interface specialized for `Long` type. Implementations of this
 * interface are expected to define constraints on `Long` values and provide an exception generator for constraint
 * violations.
 *
 * ## Examples
 * ### Example 1: Implementing a custom `LongConstraint`
 * ```kotlin
 * data object BePositiveLong : LongConstraint {
 *     override val validator = { value: Long -> value > 0L }
 * }
 * ```
 *
 * @see Constraint
 *
 * @property validator A lambda function that defines the constraint for a given `Long` value.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
interface LongConstraint : Constraint<Long> {

    /**
     * Generates an exception with the provided description in case of constraint violations.
     *
     * @param description A description of the violated constraint.
     * @return A [LongConstraintException] with the provided description.
     */
    override fun generateException(description: String) = LongConstraintException { description }
}
