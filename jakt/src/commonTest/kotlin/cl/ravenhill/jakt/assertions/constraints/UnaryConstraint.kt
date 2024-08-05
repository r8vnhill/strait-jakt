/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.assertions.constraints

import cl.ravenhill.jakt.constraints.BeNegativeConstraint
import cl.ravenhill.jakt.constraints.BePositiveConstraint
import io.kotest.core.TestConfiguration
import io.kotest.core.spec.style.freeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.property.Arb
import io.kotest.property.checkAll

/**
 * Tests the [BeNegativeConstraint] using property-based testing within a [freeSpec] scope.
 *
 * This function is designed to evaluate the [BeNegativeConstraint] for a type [T] that is [Comparable].
 * It verifies the functionality of the constraint's validator by using two [Arb] generators: one for values
 * expected to be validated as negative (trueArb), and another for values expected to be non-negative (falseArb).
 * The function initializes the constraint in a [TestConfiguration.beforeEach] block for each test case.
 *
 * ## Test Cases
 * - **Validator Functionality**:include(
 *   - Verifies that the validator returns `true` for negative values.
 *   - Confirms that the validator returns `false` for non-negative values, including the specified 'zero' value.
 *
 * ## Usage
 * ### Example: Testing a 'BeNegativeConstraint' for integers
 * ```kotlin
 * include(`test BeNegative constraint`(
 *     trueArb = Arb.negativeInts(),
 *     falseArb = Arb.nonNegativeInts(),
 * ) { IntNegativeConstraint() })
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @param trueArb An [Arb] generator producing values expected to be negative.
 * @param falseArb An [Arb] generator producing values expected to be non-negative.
 * @param constraint A lambda function that creates an instance of [BeNegativeConstraint].
 */
fun <T> testBeNegativeConstraint(
    trueArb: Arb<T>,
    falseArb: Arb<T>,
    constraint: () -> BeNegativeConstraint<T>,
) where T : Comparable<T> = freeSpec {

    lateinit var testConstraint: BeNegativeConstraint<T>

    beforeEach {
        testConstraint = constraint()
    }

    "A Be Negative constraint" - {
        "should have a [validator] that" - {
            "returns true if the value is negative" {
                checkAll(trueArb) { value ->
                    testConstraint.validator(value).shouldBeTrue()
                }
            }

            "returns false if the value is non-negative" {
                checkAll(falseArb) { value ->
                    testConstraint.validator(value).shouldBeFalse()
                }

                // Checking zero as it's a boundary condition
                testConstraint.validator(testConstraint.zero).shouldBeFalse()
            }
        }
    }
}

fun <T> testBePositiveConstraint(
    trueArb: Arb<T>,
    falseArb: Arb<T>,
    constraint: () -> BePositiveConstraint<T>,
) where T : Comparable<T> = freeSpec {

    lateinit var testConstraint: BePositiveConstraint<T>

    beforeEach {
        testConstraint = constraint()
    }

    "A Be Positive constraint" - {
        "should have a [validator] that" - {
            "returns true if the value is positive" {
                checkAll(trueArb) { value ->
                    testConstraint.validator(value).shouldBeTrue()
                }
            }

            "returns false if the value is non-positive" {
                checkAll(falseArb) { value ->
                    testConstraint.validator(value).shouldBeFalse()
                }
            }
        }
    }
}