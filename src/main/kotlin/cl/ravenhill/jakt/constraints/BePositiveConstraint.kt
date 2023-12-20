package cl.ravenhill.jakt.constraints


/**
 * Defines a contract for implementing constraints that check if a given value is positive.
 *
 * This interface extends the [Constraint] interface and is specialized for [Comparable] types.
 * It provides the functionality to validate that a value is greater than a specified 'zero' value.
 * In the context of complex numbers, a complex number could be considered positive if its real part is
 * greater than zero and its imaginary part is zero.
 *
 * ## Usage
 * ### Example: Implementing a custom `BePositiveConstraint` for complex numbers
 * ```kotlin
 * data class Complex(val real: Double, val imaginary: Double) : Comparable<Complex> {
 *     override fun compareTo(other: Complex): Int {
 *         // Comparison logic (e.g., based on magnitude)
 *     }
 * }
 *
 * class ComplexPositiveConstraint : BePositiveConstraint<Complex> {
 *     override val zero: Complex = Complex(0.0, 0.0)
 *     override val validator: (Complex) -> Boolean
 *         get() = { it.real > zero.real && it.imaginary == zero.imaginary }
 * }
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @property zero The value representing 'zero' for the type [T]. For complex numbers, this could be
 *   `Complex(0.0, 0.0)`.
 * @property validator A lambda function that takes a value of type [T] and returns a [Boolean] indicating
 *   whether the value is positive. For complex numbers, this could mean the real part is greater than zero and
 *   the imaginary part is zero.
 */
interface BePositiveConstraint<T> : Constraint<T> where T : Comparable<T> {
    val zero: T
    override val validator: (T) -> Boolean
        get() = { it > zero }
}
