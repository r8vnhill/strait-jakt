/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

/**
 * Represents the types of checks that can be applied during constraint validation.
 *
 * The `Checks` enum is used to indicate whether certain checks should be skipped or enforced during the validation
 * process.
 *
 * @see SKIP
 * @see MUST
 */
enum class Checks {
    /**
     * Indicates that the check should be skipped.
     */
    SKIP,

    /**
     * Indicates that the check must be enforced.
     */
    MUST
}
