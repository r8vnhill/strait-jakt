package cl.ravenhill.jakt.constraints.strings

/**
 * A constraint object for validating if a given string is empty.
 *
 * This constraint checks whether a string is empty, meaning it has no characters. It implements
 * the [StringConstraint] interface and is used in scenarios where an empty string signifies a specific
 * condition or requirement, such as ensuring that certain fields are not left blank.
 *
 * ## Usage
 * ### Example: Validating an empty string
 * ```kotlin
 * val isEmpty = BeEmpty.validator("") // Returns `true`
 * val isNotEmpty = BeEmpty.validator("NotEmpty") // Returns `false`
 * ```
 *
 * @property validator A lambda function that takes a [String] and returns a [Boolean] indicating whether the string is
 * empty.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.2.0
 */
data object BeEmpty : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        value.isEmpty()
    }
}
