package cl.ravenhill.jakt.assertions.constraints

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.constraints.BeAtLeastConstraint
import cl.ravenhill.jakt.constraints.BeAtMostConstraint
import cl.ravenhill.jakt.constraints.Constraint
import io.kotest.core.spec.style.freeSpec
import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Gen
import io.kotest.property.checkAll


/**
 * Tests a binary [Constraint] validator within a [FreeSpecContainerScope] using property-based testing.
 *
 * This function is designed for testing binary constraints with a pair of different types [T] and [U].
 * It uses generators to produce pairs of test values and a constraint factory ([constraintFactory]) to instantiate
 * the constraint being tested. The functions [trueCase] and [falseCase] determine the expected outcome for
 * each pair of generated test values.
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
 * @param T The type parameter for the first value in the pair being tested.
 * @param U The type parameter for the second value in the pair and the type being constrained.
 * @param C The [Constraint] type being validated, which constrains the type [U].
 * @param trueGenerator A [Gen] generator producing pairs of [T, U] for which the constraint is expected to be true.
 * @param falseGenerator A [Gen] generator producing pairs of [T, U] for which the constraint is expected to be false.
 * @param constraintFactory A lambda function that takes a value of type [T] and returns an instance of [C].
 * @param trueCase A lambda function that defines the expected true condition logic between values of type [T] and [U].
 * @param falseCase A lambda function that defines the expected false condition logic between values of type [T] and
 *   [U].
 */
private suspend fun <T, U, C> FreeSpecContainerScope.`test binary Constraint validator`(
    trueGenerator: Gen<Pair<T, U>>,
    falseGenerator: Gen<Pair<T, U>>,
    constraintFactory: (T) -> C,
    trueCase: (T, U) -> Boolean,
    falseCase: (T, U) -> Boolean
) where C : Constraint<U> {
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
 * Tests the assignment of a property within a constraint using property-based testing within a
 * [FreeSpecContainerScope].
 *
 * This function is designed to verify that a specific property of a [Constraint] is correctly assigned its value
 * upon construction. It uses a generator ([gen]) to produce test values and a constraint factory ([constraint])
 * to instantiate the constraint being tested. The [propertyGetter] function is used to retrieve the property value
 * from the constraint instance for validation.
 *
 * @param T The type parameter for the value being tested.
 * @param C The [Constraint] type being validated.
 * @param gen An [Arb] generator for the type [T], which provides values to test the property assignment.
 * @param constraint A lambda function that takes a value of type [T] and returns an instance of [C].
 * @param propertyName The name of the property being tested, used for descriptive purposes in test output.
 * @param propertyGetter A lambda function that retrieves the property value from the constraint instance.
 */
private suspend fun <T, C> FreeSpecContainerScope.`test property assignment`(
    gen: Arb<T>,
    constraint: (T) -> C,
    propertyName: String,
    propertyGetter: C.() -> T
) where C : Constraint<T> {
    "should have a $propertyName value that" - {
        "returns the value provided in the constructor" {
            checkAll(gen) { value ->
                constraint(value).propertyGetter() shouldBe value
            }
        }
    }
}

/**
 * Tests the [BeAtLeastConstraint] using property-based testing within a [freeSpec] scope.
 *
 * This function conducts a comprehensive test of the [BeAtLeastConstraint] for a type [T]. It validates
 * both the correct assignment of the 'minimum inclusive' property and the proper functionality of the constraint's
 * validator. The function uses an [Arb] generator to create test cases and a constraint factory to instantiate
 * the [BeAtLeastConstraint].
 *
 * ## Test Cases
 * - **Property Assignment**: Validates that the 'minimum inclusive' property is correctly assigned.
 * - **Constraint Validator**: Tests the validator logic to ensure it behaves as expected for both valid and invalid
 *   cases.
 *
 * ## Usage
 * ### Example: Testing a 'BeAtLeastConstraint' for integers
 * ```kotlin
 * include(`test BeAtLeast constraint`(Arb.int(), { BeAtLeastConstraint(it) }))
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraint A lambda function that takes a minimum inclusive value of type [T] and returns a
 *   [BeAtLeastConstraint].
 */
fun <T> `test BeAtLeast constraint`(
    gen: Arb<T>,
    constraint: (minInclusive: T) -> BeAtLeastConstraint<T>
) where T : Comparable<T> = freeSpec {
    "A [BeAtLeast] constraint" - {

        `test property assignment`(gen, constraint, "minimum inclusive") { minInclusive }

        `test binary Constraint validator`(
            Arb.orderedPair(gen),
            Arb.orderedPair(gen, strict = true, reverted = true),
            constraint,
            { min, value -> value >= min },
            { min, value -> value < min })
    }
}

/**
 * Tests the [BeAtMostConstraint] using property-based testing within a [freeSpec] scope.
 *
 * This function is designed to conduct a comprehensive evaluation of the [BeAtMostConstraint] for a type [T].
 * It verifies both the correct assignment of the 'maximum inclusive' property and the proper functionality of the
 * constraint's validator. The function utilizes an [Arb] generator to create test cases and a constraint factory
 * to instantiate the [BeAtMostConstraint].
 *
 * ## Test Cases
 * - **Property Assignment**: Ensures that the 'maximum inclusive' property is correctly assigned.
 * - **Constraint Validator**: Checks the validator logic to confirm it behaves as expected for both valid and invalid
 *   cases.
 *
 * ## Usage
 * ### Example: Testing a 'BeAtMostConstraint' for integers
 * ```kotlin
 * include(`test BeAtMost constraint`(Arb.int(), { BeAtMostConstraint(it) }))
 * ```
 *
 * @param T The type parameter which must be [Comparable].
 * @param gen An [Arb] generator for the type [T], providing values to test the constraint against.
 * @param constraint A lambda function that takes a maximum inclusive value of type [T] and returns a
 * [BeAtMostConstraint].
 */
fun <T> `test BeAtMost constraint`(
    gen: Arb<T>,
    constraint: (maxInclusive: T) -> BeAtMostConstraint<T>
) where T : Comparable<T> = freeSpec {
    "A [BeAtMost] constraint" - {

        `test property assignment`(gen, constraint, "maximum inclusive") { maxInclusive }

        `test binary Constraint validator`(
            Arb.orderedPair(gen),
            Arb.orderedPair(gen, strict = true, reverted = true),
            constraint,
            { max, value -> value <= max },
            { max, value -> value > max })
    }
}

