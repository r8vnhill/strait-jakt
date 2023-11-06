package cl.ravenhill.jakt.exceptions

/**
 * Custom exception class for representing long requirement-related exceptions.
 *
 * This class extends [ConstraintException] and is specifically designed for handling exceptions related
 * to long (64-bit integer) requirements. It allows you to provide a lazy message that will be computed only if
 * and when the exception is thrown, providing flexibility in error message generation.
 *
 * ## Usage
 *
 * You can use this class to create custom long requirement-related exceptions by providing a
 * `lazyMessage` lambda that computes the error message. For example:
 *
 * ```kotlin
 * throw LongRequirementException { "Custom error message." }
 * ```
 *
 * @param lazyMessage A lambda that computes the error message when the exception is thrown.
 *
 * @constructor Creates a new `LongRequirementException` with the specified `lazyMessage`.
 *
 * @param lazyMessage A lambda that computes the error message when the exception is thrown.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
open class LongRequirementException(lazyMessage: () -> String) : ConstraintException(lazyMessage)
