package cl.ravenhill.jakt.constraints

import cl.ravenhill.jakt.exceptions.ConstraintException


/**
 * Defines a contract for implementing constraints on values of type [T].
 *
 * A constraint represents a condition or set of conditions that values of type [T] must satisfy.
 * It provides utility functions for validation and for generating constraint violation exceptions.
 *
 * ## Usage
 * ### Example: Implementing a custom constraint for integers
 * ```kotlin
 * class IntPositiveConstraint : Constraint<Int> {
 *     override val validator: (Int) -> Boolean = { it > 0 }
 *
 *     override fun generateException(description: String) = ConstraintException { description }
 * }
 * ```
 *
 * @property validator A lambda function that defines the constraint's validation logic.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
interface Constraint<T> {
    val validator: (T) -> Boolean

    /**
     * Generates an exception to indicate a constraint violation.
     *
     * @param description A description of the constraint violation.
     *
     * @return A [ConstraintException] with the provided description.
     */
    fun generateException(description: String): ConstraintException
}
