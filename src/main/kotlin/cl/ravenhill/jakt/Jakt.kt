/*
 * Copyright (c) 2023, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

import cl.ravenhill.jakt.Jakt.shortCircuit
import cl.ravenhill.jakt.Jakt.skipChecks
import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.CompositeException
import cl.ravenhill.jakt.exceptions.ConstraintException

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
         * Defines a clause of a contract.
         *
         * @receiver The message key for the clause.
         * @param predicate A lambda expression that defines the predicate for the clause.
         *
         * @return A [StringScope] instance that can be used to define a [Constraint] for the clause.
         */
        inline operator fun String.invoke(predicate: StringScope.() -> Unit) = StringScope(this).apply { predicate() }

        @ExperimentalJakt
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
