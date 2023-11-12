package cl.ravenhill.jakt.constraints.ints

import cl.ravenhill.jakt.constraints.BeAtLeastConstraint

/**
 * Represents a constraint that checks if a given [Int] value is greater than or equal to a specified minimum value.
 *
 * This class is a specialization of [BeInRange], where the range is defined from the provided minimum value (inclusive)
 * up to the maximum integer value.
 * In other words, it checks if a given integer value lies in the range `[minInclusive, Int.MAX_VALUE]`.
 *
 * ## Usage
 * ### Example: Checking if an integer is at least a certain value
 * ```kotlin
 * val constraint = BeAtLeast(10)
 * val success = constraint.validator(15) // This will return `true` since 15 is greater than 10
 * val failure = constraint.validator(5)  // This will return `false` since 5 is less than 10
 * ```
 *
 * @param minInclusive The minimum value (inclusive) that an integer should have to satisfy the constraint.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data class BeAtLeast(override val minInclusive: Int) : IntConstraint, BeAtLeastConstraint<Int>
