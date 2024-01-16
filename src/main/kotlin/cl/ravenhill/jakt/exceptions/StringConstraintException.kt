package cl.ravenhill.jakt.exceptions

/**
 * A specialized exception for handling violations of string constraints.
 *
 * `StringConstraintException` extends [ConstraintException] and is specifically designed to represent
 * exceptions that occur during the validation of string constraints. This exception is thrown when a
 * string value does not meet a defined constraint in the validation process.
 *
 * The exception uses a lazy-initialized message, which is only computed when the message is needed.
 * This approach is efficient, especially in scenarios where constructing the exception message might
 * be resource-intensive or unnecessary unless the exception is actually thrown and handled.
 *
 * ## Usage:
 * Typically, `StringConstraintException` is thrown by string constraint validators within the library
 * when a validation fails. It can also be used in custom string validation logic where specific
 * exception handling is required.
 *
 * ### Example: Throwing StringConstraintException
 * ```
 * if (!isValidString) {
 *     throw StringConstraintException { "String does not meet the required constraint" }
 * }
 * ```
 *
 * In this example, if `isValidString` is `false`, the `StringConstraintException` will be thrown with
 * the provided message.
 *
 * @param lazyMessage A lambda function that provides the exception message. The message is evaluated
 *   lazily, meaning it's only computed when the exception's message is accessed.
 */
class StringConstraintException(lazyMessage: () -> String) : ConstraintException(lazyMessage)
