package cl.ravenhill.jakt.constraints.longs

/**
 * Represents a constraint ensuring a provided long value matches a specific expected value.
 *
 * This constraint validates that the long value being tested is equal to the specified expected value.
 * It is an implementation of the [LongConstraint] interface.
 *
 * ## Examples
 * ### Example 1: Validating if a number is equal to a specific value
 * ```kotlin
 * val isEqualToTen = BeEqualTo(10L).validator(10L)  // Returns `true`
 * val isNotEqualToTen = BeEqualTo(10L).validator(5L) // Returns `false`
 * ```
 *
 * @property expected The expected long value against which the given value will be compared.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data class BeEqualTo(val expected: Long) : LongConstraint {
    override val validator = { value: Long -> value == expected }
}
