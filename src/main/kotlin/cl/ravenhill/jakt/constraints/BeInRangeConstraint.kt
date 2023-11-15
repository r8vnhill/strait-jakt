package cl.ravenhill.jakt.constraints


/**
 * Defines a contract for implementing constraints that check if a given value is within a specified range.
 *
 * This interface extends the [Constraint] interface, focusing on constraints for [Comparable] types.
 * It provides a means to validate that a value falls within a specified closed range (`range`). This type of
 * constraint is widely applicable in various scenarios, such as validating numerical ranges, ensuring values
 * fall within acceptable limits, or checking ranges in data filtering processes.
 *
 * Implementations of this interface should specify the range to be checked and provide the logic to determine
 * if a given value falls within this range.
 *
 * ## Usage
 * ### Example: Implementing a custom `BeInRangeConstraint` for integers
 * ```kotlin
 * class IntInRangeConstraint(override val range: ClosedRange<Int>) : BeInRangeConstraint<Int>
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @property range The closed range of type [T] that the value should fall within.
 * @property validator A lambda function that takes a value of type [T] and returns a [Boolean] indicating
 * whether the value falls within the specified `range`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.2.0
 * @version 1.2.0
 */
interface BeInRangeConstraint<T> : Constraint<T> where T : Comparable<T> {
    val range: ClosedRange<T>
    override val validator: (T) -> Boolean
        get() = { it in range }
}
