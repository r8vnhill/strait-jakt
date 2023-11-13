package cl.ravenhill.jakt.constraints


/**
 * Defines a contract for implementing constraints that check if a given value is at most a specified maximum.
 *
 * This interface extends the [Constraint] interface, focusing on constraints for [Comparable] types.
 * It is specifically designed for validating that a value is less than or equal to a specified maximum value (`value`).
 * This type of constraint is widely applicable in scenarios where there are upper limits or maximum thresholds,
 * such as in setting maximum allowable values, defining upper bounds in ranges, or capping measurements and scores.
 *
 * Implementations of this interface should specify the maximum value and provide the logic to determine
 * if a given value does not exceed this maximum.
 *
 * ## Usage
 * ### Example: Implementing a custom `BeAtMostConstraint` for integers
 * ```kotlin
 * class IntAtMostConstraint(override val value: Int) : BeAtMostConstraint<Int>
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @property maxInclusive The maximum value that a value of type [T] should not exceed.
 * @property validator A lambda function that takes a value of type [T] and returns a [Boolean] indicating
 * whether the value is less than or equal to `value`.
 */
interface BeAtMostConstraint<T> : Constraint<T> where T : Comparable<T> {
    val maxInclusive: T
    override val validator: (T) -> Boolean
        get() = { it <= maxInclusive }
}
