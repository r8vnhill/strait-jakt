package cl.ravenhill.jakt.constraints.strings


/**
 * Represents a constraint that checks if the length of a given string satisfies a specified predicate.
 *
 * This constraint is useful for validating the length of a string against various conditions,
 * such as being within a certain range, being above or below specific limits, or matching an exact value.
 * It implements the [StringConstraint] interface and is versatile for use in contexts where string length
 * is a crucial factor, like input validation in forms, password rules, and text field limits.
 *
 * ## Usage
 * ### Example 1: Validating string length with a specific condition
 * ```kotlin
 * val isOfLengthFive = HaveLength { it == 5 }
 * val isValidLength = isOfLengthFive.validator("Hello") // Returns `true`
 * val isInvalidLength = isOfLengthFive.validator("Hi")   // Returns `false`
 * ```
 *
 * ### Example 2: Using a range for string length validation
 * ```kotlin
 * val isWithinRange = HaveLength { it in 3..8 }
 * val isValidRange = isWithinRange.validator("Hello")   // Returns `true`
 * val isOutOfRange = isWithinRange.validator("H")       // Returns `false`
 * ```
 *
 * @property predicate A function that takes the length of the string as an Int and returns a Boolean.
 * @property validator A lambda function that takes a [String] and applies the [predicate] to its length.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.1.0
 * @version 1.2.0
 */
data class HaveLength(val predicate: (Int) -> Boolean) : StringConstraint {
    override val validator: (String) -> Boolean = { value ->
        predicate(value.length)
    }
}
