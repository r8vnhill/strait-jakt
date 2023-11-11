package cl.ravenhill.jakt.constraints.strings

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.StringConstraintException


/**
 * Defines a contract for implementing constraints specifically for `String` values.
 *
 * This interface extends the [Constraint] interface with a specialization for strings.
 * Implementing this interface involves defining string-specific validation logic and
 * providing a mechanism to generate string-related constraint violation exceptions.
 *
 * Implementations of this interface can be used to apply a wide range of validations
 * to string data, such as format checking, length validation, content inspection, etc.
 *
 * ## Usage
 * ### Example: Implementing a custom `StringConstraint`
 * ```kotlin
 * object MyStringConstraint : StringConstraint {
 *     override val validator: (String) -> Boolean = { it.isNotEmpty() }
 * }
 * ```
 *
 * @see Constraint
 *
 * @property validator A lambda function that defines the constraint's validation logic for strings.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.1.0
 * @version 1.1.1
 */
interface StringConstraint : Constraint<String> {

    /**
     * Generates a [StringConstraintException] to indicate a string constraint violation.
     *
     * @param description A description of the constraint violation.
     * @return A [StringConstraintException] with the provided description.
     */
    override fun generateException(description: String) = StringConstraintException { description }
}
