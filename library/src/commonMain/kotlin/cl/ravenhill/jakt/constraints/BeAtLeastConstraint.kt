package cl.ravenhill.jakt.constraints


/**
 * Defines a contract for implementing constraints that check if a given value is at least a specified minimum.
 *
 * This interface extends the [Constraint] interface with a focus on constraints for [Comparable] types,
 * specifically for validating that a value is greater than or equal to a specified minimum value (`minInclusive`).
 * It can be used in various contexts where numerical or comparable values must meet a minimum threshold,
 * such as lower bounds in ranges, minimum requirements in scoring systems, or boundary conditions in algorithms.
 *
 * Implementations of this interface should define the minimum inclusive value and provide the logic to validate
 * if a given value meets this minimum requirement.
 *
 * ## Usage
 * ### Example: Implementing a custom `BeAtLeastConstraint` for integers
 * ```kotlin
 * class IntAtLeastConstraint(override val minInclusive: Int) : BeAtLeastConstraint<Int>
 * ```
 *
 * @param T The type of value this constraint applies to, which must be [Comparable].
 * @property minInclusive The minimum inclusive value that a value of type [T] should be equal to or exceed.
 * @property validator A lambda function that takes a value of type [T] and returns a [Boolean] indicating
 * whether the value is greater than or equal to `minInclusive`.
 */
interface BeAtLeastConstraint<T> : Constraint<T> where T : Comparable<T> {
    val minInclusive: T
    override val validator: (T) -> Boolean
        get() = { value: T -> value >= minInclusive }
}
