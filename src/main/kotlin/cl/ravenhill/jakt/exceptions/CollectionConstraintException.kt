/*
 * Copyright (c) 2023, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions

/**
 * Custom exception class for representing collection constraint-related exceptions.
 *
 * This class extends [ConstraintException] and is crafted specifically for managing exceptions tied
 * to collection constraints. By offering the ability to provide a lazy message, this exception ensures
 * that the error message will only be computed when the exception is actually thrown. This approach
 * offers both efficiency and flexibility in generating error messages.
 *
 * ## Usage
 *
 * This class can be used to instantiate custom collection constraint-related exceptions by providing a
 * `lazyMessage` lambda that defines the error message. For example:
 *
 * ```kotlin
 * throw CollectionConstraintException { "The provided collection does not satisfy the required conditions." }
 * ```
 *
 * @param lazyMessage A lambda responsible for generating the error message when the exception is thrown.
 *
 * @constructor Initializes a new `CollectionConstraintException` using the specified `lazyMessage`.
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
open class CollectionConstraintException(lazyMessage: () -> String) : ConstraintException(lazyMessage)
