package cl.ravenhill.jakt


/**
 * Marks the API annotated with `ExperimentalJakt` as experimental.
 *
 * Usage of APIs marked with this annotation indicates that the API is still experimental
 * and subject to change. It may not be stable and should be used with caution.
 *
 * ## Attributes:
 * - `level`: Specifies the level of the warning given when the experimental API is used.
 *    In this case, it is set to `RequiresOptIn.Level.WARNING`.
 * - `message`: Provides a custom message that explains the experimental nature of the API.
 *    The message here is "This API is experimental and may change in the future."
 * - `Retention(AnnotationRetention.BINARY)`: Indicates that this annotation is stored in binary output
 *    and is available for reflection at runtime.
 * - `Target`: Specifies the possible kinds of elements that can be annotated with this
 *    annotation (`CLASS`, `FUNCTION`, `PROPERTY`, `TYPEALIAS`).
 *
 * ## Usage:
 * When you use an API marked with `ExperimentalJakt`, it indicates that you acknowledge
 * the experimental status of the API. This is important for API stability and backward compatibility.
 *
 * ### Example 1: Annotating a Class
 * ```
 * @ExperimentalJakt
 * class ExperimentalClass { ... }
 * ```
 *
 * ### Example 2: Annotating a Function
 * ```
 * @ExperimentalJakt
 * fun experimentalFunction() { ... }
 * ```
 */
@RequiresOptIn(level = RequiresOptIn.Level.WARNING, message = "This API is experimental and may change in the future.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.TYPEALIAS)
annotation class ExperimentalJakt
