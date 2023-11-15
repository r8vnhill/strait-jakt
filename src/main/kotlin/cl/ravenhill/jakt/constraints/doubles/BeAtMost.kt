package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeAtMostConstraint


/**
 * Represents a constraint that checks if a given double value is at most a specified maximum value.
 *
 * This class implements both [DoubleConstraint] and [BeAtMostConstraint] for double values. It is used to validate
 * that a double value is less than or equal to the specified maximum value (`maxInclusive`). This constraint is
 * especially useful in scenarios where there are upper limits for numerical values, such as maximum thresholds
 * in various calculations, settings, or measurements.
 *
 * ## Usage
 * ### Example: Validating a value against a maximum threshold
 * ```kotlin
 * val maxConstraint = BeAtMost(100.0)
 * val isValid = maxConstraint.validator(50.0)  // Returns `true` as 50.0 is less than 100.0
 * val isInvalid = maxConstraint.validator(150.0) // Returns `false` as 150.0 exceeds 100.0
 * ```
 *
 * @param maxInclusive The maximum inclusive value that the double value should not exceed.
 * @property validator A lambda function that takes a [Double] and returns a [Boolean] indicating whether the value
 * is less than or equal to `maxInclusive`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.2.0
 */
data class BeAtMost(override val maxInclusive: Double) : DoubleConstraint, BeAtMostConstraint<Double>
