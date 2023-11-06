package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a constraint ensuring a provided integer value is equal to a specified expected value.
 *
 * This constraint is designed to validate whether a given integer matches the expected value. As an
 * implementation of the [IntConstraint] interface, it simplifies the process of checking integer values
 * against this exactness condition.
 *
 * ## Usage
 * ### Example: Validating if a number is equal to the expected value
 * ```kotlin
 * val isEqual = BeEqualTo(5).validator(5)  // Returns `true`
 * val isNotEqual = BeEqualTo(5).validator(3) // Returns `false`
 * ```
 *
 * @param expected The integer value to which other values should be compared.
 * @property validator A lambda function that verifies if the provided integer value matches the expected value.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data class BeEqualTo(val expected: Int) : IntConstraint {
    override val validator = { value: Int -> value == expected }
}
