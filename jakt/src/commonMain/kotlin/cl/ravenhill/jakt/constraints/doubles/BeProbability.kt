/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */
package cl.ravenhill.jakt.constraints.doubles

/**
 * Represents a constraint that ensures a `Double` value is a valid probability.
 *
 * This constraint checks that the value is within the range `[0.0, 1.0]`, inclusive, which is the standard range for
 * probabilities.
 *
 * ## Usage:
 * Use this constraint to validate that a `Double` value represents a valid probability between 0 and 1.
 *
 * ### Example 1: Using BeProbability Constraint
 * ```kotlin
 * val probability = 0.75
 * val result = probability.constrainedTo {
 *     "'$probability' Must be a valid probability" { it must BeProbability }
 * }
 * println(result) // Prints: 0.75
 * ```
 *
 * ### Example 2: Validation Failure
 * ```kotlin
 * val invalidProbability = 1.5
 * constrained {
 *     "'$invalidProbability' Must be a valid probability" { invalidProbability must BeProbability }
 * }.onLeft { exception ->
 *    println(exception.message)
 * }
 * ```
 *
 * @property validator The validation function that checks if the `Double` value is within the probability range `[0.0, 1.0]`.
 */
data object BeProbability : DoubleConstraint {
    override val validator: (Double) -> Boolean = { it in 0.0..1.0 }
}
