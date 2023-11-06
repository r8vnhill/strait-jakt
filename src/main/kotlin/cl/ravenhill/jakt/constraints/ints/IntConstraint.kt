/**
 * Copyright (c) 2023, R8V.
 * BSD Zero Clause License.
 */

package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.IntConstraintException

/**
 * Represents a specialized constraint designed for integer values.
 *
 * This interface defines a contract for implementing constraints on values of type [Int]. The constraints are
 * conditions or sets of conditions that integer values must satisfy. It extends from the [Constraint] interface with a
 * specific implementation for integer values.
 *
 * The [generateException] function provides a default way to generate a custom exception, [IntConstraintException],
 * tailored for violations related to integer constraints.
 *
 * ## Usage
 * ### Example: Implementing a custom constraint for even integers
 * ```kotlin
 * class EvenIntConstraint : IntConstraint {
 *     override val validator: (Int) -> Boolean = { it % 2 == 0 }
 * }
 * ```
 * With this constraint, any integer value checked against it will be validated to be even.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
interface IntConstraint : Constraint<Int> {

    /**
     * Generates an exception specific to integer constraint violations.
     *
     * @param description A description of the constraint violation.
     * @return An [IntConstraintException] with the provided description.
     */
    override fun generateException(description: String) = IntConstraintException { description }
}
