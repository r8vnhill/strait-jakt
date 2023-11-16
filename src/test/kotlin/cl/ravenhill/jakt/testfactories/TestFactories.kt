package cl.ravenhill.jakt.testfactories

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.arbs.datatypes.orderedTriple
import cl.ravenhill.jakt.constraints.BeAtLeastConstraint
import cl.ravenhill.jakt.constraints.BeAtMostConstraint
import cl.ravenhill.jakt.constraints.BeInRangeConstraint
import cl.ravenhill.jakt.constraints.BeNegativeConstraint
import cl.ravenhill.jakt.constraints.Constraint
import io.kotest.core.spec.style.freeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Gen
import io.kotest.property.checkAll

/**
 * Tests a binary [Constraint] validator within a [FreeSpecContainerScope] using property-based testing.
 *
 * This function is designed for testing binary constraints, where a pair of values either meets or does not meet
 * the constraint conditions. It uses generators to produce pairs of test values and a constraint factory
 * ([constraintFactory]) to instantiate the constraint being tested. The [trueCase] and [falseCase] functions
 * determine the expected outcome for each pair of test values.
 *
 * ## Usage
 * ### Example: Testing a custom binary constraint validator
 * ```kotlin
 * // Within a FreeSpec test suite
 * "My custom constraint test" - {
 *     `test binary Constraint validator`(
 *         trueGenerator = myTrueGen,
 *         falseGenerator = myFalseGen,
 *         constraintFactory = { MyCustomConstraint(it) },
 *         trueCase = { first, second -> /* condition when true */ },
 *         falseCase = { first, second -> /* condition when false */ }
 *     )
 * }
 * ```
 *
 * @param T The type parameter for the values being tested.
 * @param C The [Constraint] type being validated.
 * @param trueGenerator A [Gen] generator producing pairs of [T] for which the constraint is expected to be true.
 * @param falseGenerator A [Gen] generator producing pairs of [T] for which the constraint is expected to be false.
 * @param constraintFactory A lambda function that takes a value of type [T] and returns an instance of [C].
 * @param trueCase A lambda function that defines the expected true condition logic between two values of type [T].
 * @param falseCase A lambda function that defines the expected false condition logic between two values of type [T].
 */
suspend fun <T, C> FreeSpecContainerScope.`test binary Constraint validator`(
    trueGenerator: Gen<Pair<T, T>>,
    falseGenerator: Gen<Pair<T, T>>,
    constraintFactory: (T) -> C,
    trueCase: (T, T) -> Boolean,
    falseCase: (T, T) -> Boolean
) where C : Constraint<T> {
    "should have a validator that" - {
        "returns true when the constraint condition is met" {
            checkAll(trueGenerator) { (first, second) ->
                constraintFactory(first).validator(second) shouldBe trueCase(first, second)
            }
        }

        "returns false when the constraint condition is not met" {
            checkAll(falseGenerator) { (first, second) ->
                constraintFactory(first).validator(second) shouldBe !falseCase(first, second)
            }
        }
    }
}

/**
 * Tests a generic [Constraint] validator using property-based testing.
 *
 * This function is designed to test any [Constraint] by verifying its validator logic against specified conditions.
 * It employs a generator ([gen]) to produce a range of test values and a constraint factory ([constraintFactory])
 * to instantiate the constraint being tested. The [trueCase] function determines the condition to be
 * checked for each pair of generated test values.
 *
 * ## Usage
 * ### Example: Testing a custom constraint validator
 * ```kotlin
 * include(`test Constraint validator`(Arb.int(), { MyCustomConstraint(it) }) { constraintValue, testValue ->
 *     // Define the condition for the custom constraint here.
 * })
 * ```
 *
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraintFactory A lambda function that takes a value of type [T] and returns an instance of [C].
 * @param trueCase A lambda function that defines the validation logic between two values of type [T].
 * @param T The type parameter for the values being tested, which must be [Comparable].
 * @param C The type of [Constraint] being tested.
 */
fun <T, C> `test factory binary Constraint validator`(
    gen: Arb<T>,
    constraintFactory: (T) -> C,
    trueCase: (T, T) -> Boolean,
    falseCase: (T, T) -> Boolean
) where T : Comparable<T>, C : Constraint<T> = freeSpec {
    "A constraint validation" - {
        `test binary Constraint validator`(
            Arb.orderedPair(gen),
            Arb.orderedPair(gen, strict = true, reverted = true),
            constraintFactory,
            trueCase,
            falseCase
        )
    }
}

fun <T, C> `test Constraint object validator`(
    gen: Arb<T>,
    constraintFactory: () -> C,
    isConstraintValid: (T) -> Boolean
) where T : Comparable<T>, C : Constraint<T> = freeSpec {
    "A constraint validation" - {
        "should have a validator that" - {
            "returns true when the constraint condition is met" {
                checkAll(gen) { value ->
                    constraintFactory().validator(value) shouldBe isConstraintValid(value)
                }
            }

            "returns false when the constraint condition is not met" {
                checkAll(gen) { value ->
                    constraintFactory().validator(value) shouldBe !isConstraintValid(value)
                }
            }
        }
    }
}

/**
 * Validates the [BeAtLeastConstraint] using property-based testing.
 *
 * This function checks if the [BeAtLeastConstraint] correctly validates that a value is greater than or equal to
 * a specified minimum.
 *
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraint A lambda function that takes a minimum inclusive value and returns a [BeAtLeastConstraint].
 * @param T The type parameter which must be [Comparable].
 */
suspend fun <T> FreeSpecContainerScope.`test BeAtLeast validator`(
    gen: Arb<T>,
    constraint: (minInclusive: T) -> BeAtLeastConstraint<T>
) where T : Comparable<T> = `test binary Constraint validator`(
    Arb.orderedPair(gen),
    Arb.orderedPair(gen, strict = true, reverted = true),
    constraint,
    { min, value -> value >= min },
    { min, value -> value < min })

/**
 * Validates the [BeAtMostConstraint] using property-based testing.
 *
 * This function checks if the [BeAtMostConstraint] correctly validates that a value is less than or equal to
 * a specified maximum.
 *
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraint A lambda function that takes a maximum inclusive value and returns a [BeAtMostConstraint].
 * @param T The type parameter which must be [Comparable].
 */
fun <T> `validate BeAtMostConstraint`(
    gen: Arb<T>,
    constraint: (maxInclusive: T) -> BeAtMostConstraint<T>
) where T : Comparable<T> =
    `test factory binary Constraint validator`(
        gen,
        constraint,
        { max, value -> value <= max },
        { max, value -> value > max })

fun <T> `test BeNegative validator`(
    gen: Arb<T>,
    constraint: () -> BeNegativeConstraint<T>
) where T : Comparable<T> {
}

/**
 * Validates the behavior of the [BeInRangeConstraint] using property-based testing.
 *
 * This function is a test suite designed to ensure that the [BeInRangeConstraint] operates correctly.
 * It uses the provided [Arb] generator to create a range of test cases. The test cases check if the
 * [BeInRangeConstraint] validator correctly identifies values that are within a specified range and those
 * that are not.
 *
 * ## Test Cases
 * - **Range Property**: Validates that the `range` property of the constraint returns the correct range provided in the
 *   constructor.
 * - **Validator Functionality**:
 *   - Checks if the constraint returns `true` for values within the specified range.
 *   - Checks if the constraint returns `false` for values outside the specified range.
 *
 * ## Usage
 * ### Example: Testing a custom `BeInRangeConstraint`
 * ```kotlin
 * include(`validate BeInRangeConstraint`(Arb.int()) { range ->
 *    IntInRangeConstraint(range)
 * })
 * ```
 *
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraint A lambda function that takes a closed range of type [T] and returns an instance of
 *   [BeInRangeConstraint].
 * @param T The type parameter which must be [Comparable].
 */
fun <T> `validate BeInRangeConstraint`(
    gen: Arb<T>,
    constraint: (range: ClosedRange<T>) -> BeInRangeConstraint<T>
) where T : Comparable<T> = freeSpec {
    "A [BeInRange] constraint" - {

        "has a range property that" - {
            "returns the range provided in the constructor" {
                checkAll(Arb.orderedPair(gen)) { (first, second) ->
                    constraint(first..second).range shouldBe first..second
                }
            }
        }

        "should have a validator that" - {
            "returns true when the constraint condition is met" {
                checkAll(Arb.orderedTriple(gen, strict = true)) { (lo, mid, hi) ->
                    constraint(lo..hi).validator(mid).shouldBeTrue()
                }
            }

            "returns false when the constraint condition is not met" {
                checkAll(Arb.orderedTriple(gen, strict = true)) { (lo, mid, hi) ->
                    constraint(lo..mid).validator(hi).shouldBeFalse()
                    constraint(mid..hi).validator(lo).shouldBeFalse()
                }
            }
        }
    }
}
