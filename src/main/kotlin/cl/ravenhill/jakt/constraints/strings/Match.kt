package cl.ravenhill.jakt.constraints.strings


/**
 * Represents a constraint that checks if a given string fully matches a specified regular expression.
 *
 * This constraint is utilized to validate whether the entire content of a string adheres to a pattern
 * defined by the provided [Regex]. It implements the [StringConstraint] interface and is highly useful in
 * scenarios where strict pattern matching is required, such as format validation, strict parsing requirements,
 * or validation of structured data like phone numbers, email addresses, etc.
 *
 * ## Usage
 * ### Example 1: Validating a string against a specific pattern
 * ```kotlin
 * val isNumeric = Match(Regex("^\\d+$"))
 * val isValid = isNumeric.validator("12345") // Returns `true`
 * val isInvalid = isNumeric.validator("12345abc") // Returns `false`
 * ```
 *
 * ### Example 2: Using a complex regex for validation
 * ```kotlin
 * val isHexColor = Match(Regex("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$"))
 * val isValidColor = isHexColor.validator("#AABBCC")   // Returns `true`
 * val isInvalidColor = isHexColor.validator("AABBCC")  // Returns `false`
 * ```
 *
 * @property regex The regular expression used for full string matching.
 * @property validator A lambda function that takes a [String] and returns a [Boolean] indicating whether the string
 * fully matches the [regex].
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.2.0
 */
data class Match(val regex: Regex) : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        regex.matches(value)
    }
}
