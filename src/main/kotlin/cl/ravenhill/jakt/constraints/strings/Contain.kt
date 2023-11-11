package cl.ravenhill.jakt.constraints.strings


/**
 * Represents a constraint that checks if a given string contains a match for a specified regular expression.
 *
 * This constraint is useful for verifying that a string contains certain patterns, characters, or sequences defined
 * by the provided [Regex]. It implements the [StringConstraint] interface and can be used in various contexts
 * where string pattern validation is required, such as validating formats, checking for the presence of special
 * characters, or ensuring certain text structures.
 *
 * ## Usage
 * ### Example: Validating if a string contains a specific pattern
 * ```kotlin
 * val containsDigit = Contain(Regex("[0-9]"))
 * val hasDigit = containsDigit.validator("abc123") // Returns `true`
 * val noDigit = containsDigit.validator("abcdef")  // Returns `false`
 * ```
 *
 * @property regex The regular expression used for matching within the string.
 * @property validator A lambda function that takes a [String] and returns a [Boolean] indicating whether the string
 * contains a match for the [regex].
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.2.0
 */
data class Contain(val regex: Regex) : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        regex.containsMatchIn(value)
    }
}
