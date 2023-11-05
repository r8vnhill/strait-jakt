/*
 * Copyright (c) 2023, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.exceptions

/***************************************************************************************************
 * This file defines a hierarchy of custom exceptions for unfulfilled requirements.
 * The hierarchy is based on the open class `UnfulfilledRequirementException`, which itself extends
 * `Exception`.
 * Specific exceptions for various data types (integer, long, pair, double, and collection) are
 * derived from `UnfulfilledRequirementException`, enabling the system to raise specific exceptions
 * based on the type of unfulfilled requirement.
 * Furthermore, `EnforcementException` is used to handle situations where a contract as a whole is
 * unfulfilled, and it aggregates a list of violations for clearer error reporting.
 **************************************************************************************************/

/**
 * Exception thrown when a collection constraint is not fulfilled.
 *
 * @param lazyMessage The message to be used in the exception.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @since 2.0.0
 * @version 2.0.0
 */
class CollectionRequirementException(lazyMessage: () -> String) :
        ConstraintException(lazyMessage)

