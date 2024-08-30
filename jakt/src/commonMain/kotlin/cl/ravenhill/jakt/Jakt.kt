/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import cl.ravenhill.jakt.Jakt.shortCircuit
import cl.ravenhill.jakt.Jakt.skipChecks
import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.CompositeException
import cl.ravenhill.jakt.exceptions.ConstraintException

/**
 * The `Jakt` object provides utilities for defining and validating constraints on values within a DSL. It offers both
 * the deprecated `constraints` method, which throws exceptions, and a more functional approach with the `constrained`
 * method (recommended for new code).
 *
 * The [skipChecks] and [shortCircuit] flags control the behavior of constraint validation.
 * - `skipChecks`: If `true`, skips all validations.
 * - `shortCircuit`: If `true`, stops further validations as soon as a constraint fails and throws the corresponding
 *   exception.
 *
 * ## Usage:
 * Use `Jakt` to define constraints on values within a declarative scope, allowing for flexible validation logic.
 *
 * ### Example 1: Using the `constrained` function:
 * ```kotlin
 * constrained {
 *     "Value must be positive" {
 *         5 must BePositive
 *     }
 * }.fold(
 *    ifLeft = { println("Errors: $it") },
 *    ifRight = { println("No errors") }
 * )
 * ```
 *
 * ### Example 2: Using the `constrainedTo` function:
 * ```kotlin
 * 5.constrainedTo {
 *     "Value must be positive" {
 *         it must BePositive
 *     }
 * }.getOrElse { throw it }
 * ```
 */
object Jakt {
    /**
     * If `true`, skips all constraint checks.
     */
    var skipChecks = Checks.MUST

    /**
     * If `true`, stops further validations as soon as a constraint fails and throws the corresponding exception.
     */
    var shortCircuit = false

    /**
     * Defines constraints on values within the provided `builder` scope.
     *
     * This method is deprecated in favor of the `constrained` method, which adopts a more functional programming (FP)
     * approach by returning results instead of throwing exceptions.
     *
     * @param builder The block in which constraints are defined.
     *
     * @throws CompositeException if any constraint fails and `shortCircuit` is set to `false`.
     */
    @Deprecated(
        "Use the constrained function instead. Note that the new implementation does not throw an exception in" +
                "favor of a more FP approach.",
        ReplaceWith("constrained(builder)")
    )
    inline fun constraints(builder: Scope.() -> Unit) {
        if (skipChecks == Checks.MUST) {
            Scope().apply(builder).failures.let { errors ->
                if (errors.isNotEmpty()) {
                    throw CompositeException(errors)
                }
            }
        }
    }

    /**
     * The `Scope` class provides a context for defining constraints on values.
     * It collects the results of validations and handles the logic for enforcing constraints.
     */
    class Scope {
        /**
         * A mutable list that stores the results of each validation
         */
        private val _results: MutableList<Either<ConstraintException, *>> = mutableListOf()

        /**
         * A list of results from the validations performed within the scope.
         */
        val results: List<Either<ConstraintException, *>>
            get() = _results

        /**
         * A list of exceptions corresponding to failed validations.
         */
        val failures: List<Throwable>
            get() = _results
                .filter { it.isLeft() }
                .map { it.leftOrNull()!! } // This is safe because we are filtering the lefts

        /**
         * Defines a constraint within the `Scope` using a string message as the identifier.
         * This operator function allows for concise constraint definitions.
         *
         * @param predicate The block where the constraint is defined.
         */
        inline operator fun String.invoke(predicate: StringScope.() -> Unit) = StringScope(this).apply { predicate() }

        /**
         * Defines a constraint within the `Scope` using a string message and a custom exception generator.
         * This operator function allows for concise constraint definitions with custom exception handling.
         *
         * @param exceptionGenerator A function to generate a custom exception if the constraint fails.
         * @param predicate The block where the constraint is defined.
         */
        inline operator fun String.invoke(
            noinline exceptionGenerator: (String) -> ConstraintException,
            predicate: StringScope.() -> Unit,
        ) = StringScope(this).apply {
            this.exceptionGenerator = exceptionGenerator
            predicate()
        }

        /**
         * The `StringScope` class provides a nested context within the `Scope` for defining constraints on values.
         * It manages the validation logic and allows for custom exception handling.
         *
         * @param message The message associated with the constraint.
         */
        inner class StringScope(val message: String) {
            val outerScope = this@Scope

            var exceptionGenerator: ((String) -> ConstraintException)? = null

            /**
             * Validates a value against a constraint, enforcing that the value must satisfy the constraint.
             *
             * @param constraint The constraint to validate the value against.
             * @return An `Either` representing the success or failure of the validation.
             * @throws ConstraintException if the constraint fails and `shortCircuit` is `true`.
             */
            infix fun <T, C : Constraint<T>> T.must(constraint: C) {
                _results += validate(constraint, shouldPass = true).also {
                    if (shortCircuit && it.isLeft()) {
                        throw it.swap().getOrNull()!! // This is safe because we are checking if it is left
                    }
                }
            }

            /**
             * Validates a value against a constraint, enforcing that the value must not satisfy the constraint.
             *
             * @param constraint The constraint to validate the value against.
             * @throws ConstraintException if the constraint fails and `shortCircuit` is `true`.
             */
            infix fun <T, C : Constraint<T>> T.mustNot(constraint: C) {
                _results += validate(constraint, shouldPass = false).also {
                    if (shortCircuit && it.isLeft()) {
                        throw it.swap().getOrNull()!! // This is safe because we are checking if it is left
                    }
                }
            }

            /**
             * Validates a value against a constraint.
             *
             * @param constraint The constraint to validate the value against.
             * @param shouldPass Indicates whether the value should pass or fail the constraint.
             * @return An `Either` representing the success or failure of the validation.
             */
            private fun <T, C : Constraint<T>> T.validate(
                constraint: C,
                shouldPass: Boolean
            ): Either<ConstraintException, T> {
                val validationResult = if (constraint.validator(this) == shouldPass) {
                    this.right()
                } else {
                    val exception = exceptionGenerator?.invoke(message) ?: constraint.generateException(message)
                    exception.left()
                }
                return validationResult
            }

            /**
             * Adds a custom constraint by evaluating a predicate.
             * The constraint is considered valid if the predicate returns `true`.
             *
             * @param predicate The custom validation logic.
             */
            fun constraint(predicate: () -> Boolean) {
                _results += if (predicate()) {
                    Unit.right()
                } else {
                    ConstraintException { message }.left()
                }
            }

            override fun toString() = "StringScope(message='$message')"
        }
    }
}
