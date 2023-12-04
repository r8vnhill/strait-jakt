package cl.ravenhill.jakt.constraints


/**
 * Defines a contract for implementing constraints that check if a given value is positive.
 *
 * This interface extends the [Constraint] interface and is specialized for [Comparable] types.
 * It provides the functionality to validate that a value is greater than a specified 'zero' value,
 * typically representing the numeric zero in various data types. This constraint is essential in
 * scenarios where positive values are required, such as in financial calculations, physical measurements,
 * or other contexts where positive values have specific significance.
 *
 * Implementations of this interface should specify the 'zero' value, which acts as the threshold to determine
 * positivity.
 *
 * ## Usage
 * ### Example: Implementing a custom `BePositiveConstraint` for integers
 * ```kotlin
 * class IntPositiveConstraint : BePositiveConstraint<Int> {
 *     override val zero: Int = 0
 * }
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @property zero The value representing 'zero' for the type [T]. Values greater than this are considered positive.
 * @property validator A lambda function that takes a value of type [T] and returns a [Boolean] indicating
 *   whether the value is positive (greater than `zero`).
 */
interface BePositiveConstraint<T> : Constraint<T> where T : Comparable<T> {
    val zero: T
    override val validator: (T) -> Boolean
        get() = { it > zero }
}
