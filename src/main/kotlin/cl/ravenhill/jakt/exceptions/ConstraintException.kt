package cl.ravenhill.jakt.exceptions

/**
 * Custom exception class for representing constraint-related exceptions.
 *
 * This open class is designed to be extended for creating specific constraint-related exceptions
 * in your application. It allows you to provide a lazy message that will be computed only if
 * and when the exception is thrown, providing flexibility in error message generation.
 *
 * ## Usage
 *
 * You can create specific constraint-related exceptions by extending this class and providing a
 * `lazyMessage` lambda that computes the error message. For example:
 *
 * ```kotlin
 * class MyCustomConstraintException(val field: String) : ConstraintException({
 *     "Constraint violation in field '$field': Custom error message."
 * })
 * ```
 *
 * @param lazyMessage A lambda that computes the error message when the exception is thrown.
 *
 * @constructor Creates a new `ConstraintException` with the specified `lazyMessage`.
 *
 * @throws Exception Thrown with the computed error message when the exception is raised.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
open class ConstraintException(lazyMessage: () -> String) : Exception(lazyMessage())
