package cl.ravenhill.jakt.exceptions

/**
 * Custom exception class for representing integer constraint-related exceptions.
 *
 * This class extends [ConstraintException] and is designed specifically for handling exceptions related
 * to integer constraints. It allows you to provide a lazy message that will be computed only if and when
 * the exception is thrown, providing flexibility in error message generation.
 *
 * ## Usage
 *
 * You can use this class to create custom integer constraint-related exceptions by providing a
 * `lazyMessage` lambda that computes the error message. For example:
 *
 * ```kotlin
 * throw IntConstraintException { "Custom error message." }
 * ```
 *
 * @param lazyMessage A lambda that computes the error message when the exception is thrown.
 *
 * @constructor Creates a new `IntConstraintException` with the specified `lazyMessage`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
class IntConstraintException(lazyMessage: () -> String) : ConstraintException(lazyMessage)
