package cl.ravenhill.jakt.constraints.ints

/**
 * Represents a constraint that checks if a given [Int] value is less than or equal to a specified maximum value.
 *
 * This class is a specialization of [BeInRange], where the range is defined from the minimum integer value up to the
 * provided maximum value (inclusive).
 * In essence, it checks if a given integer value lies in the range `[Int.MIN_VALUE, maxInclusive]`.
 *
 * ## Usage
 * ### Example: Checking if an integer is at most a certain value
 * ```kotlin
 * val constraint = BeAtMost(10)
 * val success = constraint.validator(5) // This will return `true` since 5 is less than or equal to 10
 * val failure = constraint.validator(15) // This will return `false` since 15 is greater than 10
 * ```
 *
 * @param maxInclusive The maximum value (inclusive) that an integer should not exceed to satisfy the constraint.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
data class BeAtMost(val maxInclusive: Int) : BeInRange(Int.MIN_VALUE..maxInclusive)
