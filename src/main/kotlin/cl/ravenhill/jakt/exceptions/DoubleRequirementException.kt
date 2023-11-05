package cl.ravenhill.jakt.exceptions

/**
 * Custom exception class for representing double constraint-related exceptions.
 *
 * This class extends [ConstraintException] and is designed specifically for handling exceptions related
 * to double constraints. It allows you to provide a lazy message that will be computed only if and when
 * the exception is thrown, ensuring efficiency and flexibility in error message generation.
 *
 * ## Usage
 *
 * You can utilize this class to create custom double constraint-related exceptions by supplying a
 * `lazyMessage` lambda that determines the error message. For instance:
 *
 * ```kotlin
 * throw DoubleRequirementException { "The provided double value does not meet the requirement." }
 * ```
 *
 * @param lazyMessage A lambda that computes the error message when the exception is thrown.
 *
 * @constructor Creates a new `DoubleRequirementException` with the given `lazyMessage`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
class DoubleRequirementException(lazyMessage: () -> String) : ConstraintException(lazyMessage)
