/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints


/**
 * A constraint that checks if a value is equal to the expected value.
 *
 * ## Usage:
 * Ensure that a value meets the equality constraint.
 *
 * ### Example 1: Equality Constraint Check
 *
 * ```kotlin
 * val constraint = object : BeEqualToConstraint<Int> {
 *     override val expected: Int = 42
 * }
 * val isValid = constraint.validator(42)
 * println(isValid) // Prints: true
 * ```
 *
 * ### Example 2: Inequality Constraint Check
 *
 * ```kotlin
 * val constraint = object : BeEqualToConstraint<String> {
 *     override val expected: String = "Hello"
 * }
 * val isValid = constraint.validator("World")
 * println(isValid) // Prints: false
 * ```
 *
 * @property expected The value that the actual value is expected to be equal to.
 * @property validator The validation function that checks if the actual value is equal to the expected value.
 */
interface BeEqualToConstraint<T> : Constraint<T> {
    val expected: T
    override val validator: (T) -> Boolean
        get() = { it == expected }
}
