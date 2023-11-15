package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.constraints.BeEqualToConstraint

/**
 * Represents a constraint ensuring a provided integer value is equal to a specified expected value.
 *
 * This constraint is designed to validate whether a given integer matches the expected value. As an
 * implementation of both [IntConstraint] and [BeEqualToConstraint]<Int>, it simplifies the process
 * of checking integer values against this exactness condition, ensuring they are exactly equal to the
 * specified `expected` value.
 *
 * ## Usage
 * ### Example: Validating if a number is equal to the expected value
 * In this example, `BeEqualTo` is used to check if a number is exactly equal to 5.
 * ```kotlin
 * val isEqual = BeEqualTo(5).validator(5)  // Returns `true` as 5 is equal to 5
 * val isNotEqual = BeEqualTo(5).validator(3) // Returns `false` as 3 is not equal to 5
 * ```
 *
 * @param expected The integer value to which other values should be compared.
 * @property validator A lambda function that verifies if the provided integer value matches the expected value.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.2.0
 */
data class BeEqualTo(override val expected: Int) : IntConstraint, BeEqualToConstraint<Int>

