package cl.ravenhill.jakt.constraints.strings

/**
 * A constraint object for validating if a given string is entirely composed of alphanumeric characters.
 *
 * This constraint checks whether each character in a string is either a letter or a digit. It implements
 * the [StringConstraint] interface and is used to ensure that strings meet alphanumeric requirements, which is
 * common in various scenarios like username validation, password policies, etc.
 *
 * ## Usage
 * ### Example: Validating an alphanumeric string
 * ```kotlin
 * val isValid = BeAlphanumeric.validator("User123") // Returns `true`
 * val isInvalid = BeAlphanumeric.validator("User_123") // Returns `false`
 * ```
 *
 * @property validator A lambda function that takes a [String] and returns a [Boolean] indicating whether the string is
 *  alphanumeric.
 */
data object BeAlphanumeric : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        value.all { it.isLetterOrDigit() }
    }
}

