package cl.ravenhill.jakt.constraints


/**
 * Defines a contract for implementing constraints that check if a given value is equal to a specified value.
 *
 * This interface extends the [Constraint] interface and is specifically geared towards [Comparable] types.
 * It provides a means to validate that a value is exactly equal to a specified `value`. This type of constraint
 * is widely applicable in a range of scenarios, such as ensuring equality in comparisons, validating exact matches,
 * and checking for specific conditions in data validation processes.
 *
 * Implementations of this interface should specify the value to be matched and provide the logic to determine
 * if a given value exactly equals this specified value.
 *
 * ## Usage
 * ### Example: Implementing a custom `BeEqualToConstraint` for integers
 * ```kotlin
 * class IntEqualToConstraint(override val value: Int) : BeEqualToConstraint<Int>
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @property expected The exact value that a value of type [T] is compared against for equality.
 * @property validator A lambda function that takes a value of type [T] and returns a [Boolean] indicating
 * whether the value is exactly equal to `value`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.2.0
 * @version 1.2.0
 */
interface BeEqualToConstraint<T> : Constraint<T> where T : Comparable<T> {
    val expected: T
    override val validator: (T) -> Boolean
        get() = { it == expected }
}