package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a constraint ensuring a provided integer value is positive.
 *
 * This constraint validates that the integer value under test is strictly greater than zero. As an implementation
 * of the [IntConstraint] interface, it facilitates the checking of integer values against this specific condition.
 *
 * ## Usage
 * ### Example: Validating if a number is positive
 * ```kotlin
 * val isPositive = BePositive.validator(5)  // Returns `true`
 * val isNotPositive = BePositive.validator(-5) // Returns `false`
 * ```
 *
 * @property validator A lambda function that evaluates whether the provided integer value is positive.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data object BePositive : IntConstraint {
    override val validator = { value: Int -> value > 0 }
}
