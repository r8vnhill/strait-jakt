package cl.ravenhill.jakt.constraints

import cl.ravenhill.jakt.exceptions.ConstraintException
import java.time.LocalDate


/**
 * Defines a contract for implementing constraints that check if a given value is negative.
 *
 * This interface extends the [Constraint] interface, targeting [Comparable] types. It provides the functionality
 * to validate that a value is less than a specified 'zero' value, typically representing the numeric zero in various
 * data types. This constraint is useful in scenarios where you need to ensure that values are negative, such as in
 * financial calculations, physical measurements, or other contexts where negative values have specific implications.
 *
 * Implementations of this interface should specify the 'zero' value, which acts as the threshold to determine
 * negativity.
 *
 * ## Usage
 * ### Example: Implementing a custom `BeNegativeConstraint` for dates
 * ```kotlin
 * class DateConstraintException(
 *     description: () -> String
 * ) : ConstraintException(description)
 *
 * data class BeBefore(
 *     override val zero: LocalDate
 * ) : BeNegativeConstraint<LocalDate> {
 *     override fun generateException(description: String) =
 *         DateConstraintException { description }
 * }
 * ```
 * In this example, `DateBeforeConstraint` checks if a [LocalDate] is before a specified "zero" date. For instance, if
 * zero is set to January 1, 2020, then any date before January 1, 2020, would be considered "negative" (i.e., in the
 * past relative to this reference date).
 *
 * @param T The type parameter which must be [Comparable].
 * @property zero The value representing 'zero' for the type [T]. Values less than this are considered negative.
 * @property validator A lambda function that takes a value of type [T] and returns a [Boolean] indicating
 * whether the value is negative (less than `zero`).
 */
interface BeNegativeConstraint<T> : Constraint<T> where T : Comparable<T> {
    val zero: T
    override val validator: (T) -> Boolean
        get() = { it < zero }
}
