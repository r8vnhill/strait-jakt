/*
 * Copyright (c) 2023, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

import cl.ravenhill.jakt.Jakt.shortCircuit
import cl.ravenhill.jakt.Jakt.skipChecks
import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.constraints.strings.BeEmpty
import cl.ravenhill.jakt.exceptions.CompositeException
import cl.ravenhill.jakt.exceptions.ConstraintException
import cl.ravenhill.jakt.exceptions.StringConstraintException

/**
 * Provides a mechanism for enforcing contracts and constraints on data.
 *
 * `Jakt` allows the definition and enforcement of constraints within a specified scope.
 * It is useful for validating object states, ensuring that certain conditions or rules are met.
 *
 * ## Features
 * - Allows defining custom constraints and contracts using a DSL-like syntax.
 * - Supports skipping constraint checks globally via the `skipChecks` flag.
 * - Throws a `CompositeException` if one or more constraints are not fulfilled.
 *
 * ## Example: Enforcing a contract
 * ```kotlin
 * data class Person(val name: String, val age: Int) {
 *     init {
 *         Jakt.constraints {
 *             "Name must not be empty" { name mustNot BeEmpty }
 *             "Age must be greater than 0" { age must BePositive }
 *         }
 *     }
 * }
 * ```
 *
 * @property skipChecks A global flag to skip constraint checks if set to `true`.
 * @property shortCircuit A global flag to stop checking constraints after the first failure.
 */
object Jakt {
    var skipChecks = false
    var shortCircuit = false

    /**
     * Enforces the contract of the given builder.
     *
     * ## Example: Enforcing a contract
     *
     * ```kotlin
     *  data class Person(val name: String, val age: Int) {
     *      init {
     *          enforce {
     *              "Name must not be empty" { name mustNot BeEmpty }
     *              "Age must be greater than 0" { age must BePositive }
     *          }
     *      }
     *  }
     * ```
     *
     * @param builder The builder that contains the contract.
     * @throws CompositeException If the contract is not fulfilled.
     */
    inline fun constraints(builder: Scope.() -> Unit) {
        if (skipChecks) return
        Scope().apply(builder).failures.let { errors ->
            if (errors.isNotEmpty()) {
                throw CompositeException(errors)
            }
        }
    }

    /**
     * A utility class for enforcing contracts.
     *
     * An instance of this class can be used to enforce a contract by defining clauses using string
     * literals as message keys and lambda expressions that define the predicate.
     * Each clause defines a requirement, which can be validated by calling the `validate()` method
     * of a [Constraint] instance.
     *
     * @property results The list of results of evaluating the contract.
     * @property failures The list of exceptions thrown by the contract.
     */
    class Scope {
        private val _results: MutableList<Result<*>> = mutableListOf()
        val results: List<Result<*>>
            get() = _results

        val failures: List<Throwable> get() = _results.filter { it.isFailure }.map { it.exceptionOrNull()!! }

        /**
         * Defines a clause of a contract for string validation.
         *
         * This inline operator function simplifies the creation of a validation clause for a string value. It is
         * designed to be used within a DSL context, providing a declarative approach to define validation rules for
         * strings. The function creates a [StringScope] instance, allowing the caller to specify validation logic in a
         * concise and readable manner.
         *
         * ## Usage:
         * The function is typically utilized to establish validation rules within a custom DSL. It allows for
         * defining various constraints and rules that a string should comply with.
         *
         * ### Example: Setting up a simple validation rule
         * ```
         * "Username must not be empty" {
         *     username mustNot BeEmpty
         * }
         * ```
         *
         * In this example, a validation rule is defined for the username. The rule specifies that the username must
         * not be empty. If the validation fails, a [ConstraintException] is thrown. The actual kind of exception
         * thrown is determined by the [Constraint] used in the validation rule. For example, if the validation rule
         * uses the [BeEmpty] constraint on a string, a [StringConstraintException] is thrown.
         *
         * @receiver The message key or label for the validation clause.
         * @param predicate A lambda expression with a receiver of type [StringScope], where the validation logic is
         *   defined.
         * @return A [StringScope] instance, providing the context and functionalities to define constraints and
         *   validation logic.
         */
        inline operator fun String.invoke(predicate: StringScope.() -> Unit) = StringScope(this).apply { predicate() }

        /**
         * Defines a clause of a contract with a custom exception generator for string validation.
         *
         * This variant of the inline operator function not only creates a contract clause for string validation but
         * also allows specifying a custom exception to be thrown when validation fails. It combines Kotlin's DSL
         * features with exception handling, enabling a more tailored approach to validation logic.
         *
         * ## Usage:
         * Ideal for use cases where custom exception handling is necessary along with validation rules. Each rule can
         * specify its own logic and the type of exception to be thrown upon failure.
         *
         * ### Example: Defining a validation rule with a custom exception
         * ```
         * class InvalidUsernameException(message: String) : ConstraintException({ message })
         *
         * constraints {
         *     "Username must not be empty"(::InvalidUsernameException) {
         *         username mustNot BeEmpty
         *     }
         * }
         * ```
         *
         * In this example, the contract specifies that the username must not be empty. If the validation fails,
         * an `InvalidUsernameException` is thrown.
         *
         * @param exceptionGenerator A lambda function that takes a [String] message and returns a
         *   [ConstraintException]. This function is invoked to generate a custom exception when the validation rule is
         *   violated.
         * @param predicate A lambda with receiver [StringScope]. This is where the validation logic for the string is
         *   defined. The receiver [StringScope] provides the context and utilities for string validation.
         * @return A [StringScope] instance that can be used to define a [Constraint] for the clause.
         */
        inline operator fun String.invoke(
            noinline exceptionGenerator: (String) -> ConstraintException,
            predicate: StringScope.() -> Unit,
        ) = StringScope(this).apply {
            this.exceptionGenerator = exceptionGenerator
            predicate()
        }

        /**
         * A scope for defining a [Constraint] for a contract clause.
         *
         * @property message The message key associated with the clause.
         * @property outerScope The outer scope of the clause.
         * @property exceptionGenerator A lambda function that takes a [String] message and returns a
         *   [ConstraintException].
         */
        inner class StringScope(val message: String) {
            val outerScope = this@Scope

            var exceptionGenerator: ((String) -> ConstraintException)? = null

            /**
             * Infix function that validates that the current value satisfies the specified
             * requirement.
             *
             * @param constraint the requirement to validate against.
             * @receiver the current value to be validated.
             */

            infix fun <T, C : Constraint<T>> T.must(constraint: C) {
                _results += if (!constraint.validator(this)) {
                    if (shortCircuit) throw exceptionGenerator?.invoke(message) ?: constraint.generateException(message)
                    Result.failure(exceptionGenerator?.invoke(message) ?: constraint.generateException(message))
                } else {
                    Result.success(this)
                }
            }

            /**
             * Infix function that validates that the current value does not satisfy the specified
             * requirement.
             *
             * @param constraint the requirement to validate against.
             * @receiver the current value to be validated.
             */
            infix fun <T, C : Constraint<T>> T.mustNot(constraint: C) {
                _results += if (constraint.validator(this)) {
                    if (shortCircuit) throw exceptionGenerator?.invoke(message) ?: constraint.generateException(message)
                    Result.failure(exceptionGenerator?.invoke(message) ?: constraint.generateException(message))
                } else {
                    Result.success(this)
                }
            }

            /**
             * Defines a [Constraint] based on a predicate.
             *
             * @param predicate The predicate that defines the clause.
             */
            fun constraint(predicate: () -> Boolean) {
                _results += if (predicate()) {
                    Result.success(Unit)
                } else {
                    Result.failure(ConstraintException { message })
                }
            }

            /**
             * Represents the clause as a string using its message key.
             */
            override fun toString() = "StringScope(message='$message')"
        }
    }
}
