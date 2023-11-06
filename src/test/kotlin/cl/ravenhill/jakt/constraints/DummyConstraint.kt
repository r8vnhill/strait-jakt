package cl.ravenhill.jakt.constraints

import cl.ravenhill.jakt.exceptions.ConstraintException


/**
 * Represents a dummy constraint for integers.
 *
 * This class is primarily used for testing or demonstration purposes and implements the [Constraint] interface
 * for integer values. Upon violation of the constraint, it can generate a [ConstraintException] using the provided
 * description.
 *
 * ## Usage
 *
 * ### Example 1: Creating a dummy constraint that validates even integers
 * ```kotlin
 * val evenConstraint = DummyConstraint { it % 2 == 0 }
 * ```
 *
 * ### Example 2: Using the constraint and generating an exception
 * ```kotlin
 * if (!evenConstraint.validator(3)) {
 *     throw evenConstraint.generateException("Value should be even.")
 * }
 * ```
 *
 * @property validator A lambda function that defines the constraint's validation logic.
 *
 * @constructor Creates a new `DummyConstraint` with the given validator function.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
class DummyConstraint(override val validator: (Int) -> Boolean) : Constraint<Int> {
    override fun generateException(description: String) = ConstraintException { description }
}
