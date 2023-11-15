package cl.ravenhill.jakt.testfactories

import cl.ravenhill.jakt.arbs.datatypes.orderedPair
import cl.ravenhill.jakt.arbs.datatypes.orderedTriple
import cl.ravenhill.jakt.arbs.datatypes.real
import cl.ravenhill.jakt.constraints.BeAtLeastConstraint
import cl.ravenhill.jakt.constraints.BeAtMostConstraint
import cl.ravenhill.jakt.constraints.BeInRangeConstraint
import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.constraints.doubles.BeInRange
import io.kotest.core.spec.style.freeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

/**
 * Validates a given constraint using property-based testing.
 *
 * This generic function is designed to validate any [Constraint] by checking if it meets specified conditions.
 * It uses a generator ([gen]) to produce test cases and a constraint factory ([constraintFactory]) to create
 * instances of the constraint being tested. The [isConstraintValid] function defines the condition to check
 * for each generated test case.
 *
 * ## Usage
 * ### Example: Validating a custom constraint
 * ```kotlin
 * `validate Constraint`(Arb.int(), { MyCustomConstraint(it) }) { constraintValue, testValue ->
 *     // Condition to check
 * }
 * ```
 *
 * @param gen An [Arb] generator for the type [T], which provides values to test the constraint against.
 * @param constraintFactory A lambda function that takes a value of type [T] and returns an instance of a constraint
 * [C].
 * @param isConstraintValid A lambda function that defines the validation logic between two values of type [T].
 * @param T The type parameter for the values being tested.
 * @param C The [Constraint] type being validated.
 */
fun <T, C : Constraint<T>> `validate Constraint`(
    gen: Arb<T>,
    constraintFactory: (T) -> C,
    isConstraintValid: (T, T) -> Boolean
) where T : Comparable<T> = freeSpec {
    "A constraint validation" - {
        "should have a validator that" - {
            "returns true when the constraint condition is met" {
                checkAll(Arb.orderedPair(gen)) { (first, second) ->
                    constraintFactory(first).validator(second) shouldBe isConstraintValid(first, second)
                }
            }

            "returns false when the constraint condition is not met" {
                checkAll(Arb.orderedPair(gen, strict = true)) { (first, second) ->
                    constraintFactory(second).validator(first) shouldBe isConstraintValid(second, first)
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
fun <T> `validate BeAtLeast constraint`(
    gen: Arb<T>,
    constraint: (minInclusive: T) -> BeAtLeastConstraint<T>
) where T : Comparable<T> =
    `validate Constraint`(gen, constraint) { min, value -> value >= min }

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
    `validate Constraint`(gen, constraint) { max, value -> value <= max }

fun <T> `validate BeInRangeConstraint`(
    gen: Arb<T>,
    constraint: (range: ClosedRange<T>) -> BeInRangeConstraint<T>
) where T : Comparable<T> = freeSpec {

    "A [BeInRange] constraint" - {
        "when created" - {
            "with a range should set the range correctly" {
                checkAll(Arb.orderedPair(gen, strict = true)) { (start, end) ->
                    with(constraint(start..end)) {
                        range.start shouldBe start
                        range.endInclusive shouldBe end
                    }
                }
            }
        }

        "have a validator that" - {
            "returns true if the value is within the range" {
                checkAll(Arb.orderedTriple(gen, strict = true)) { (lo, mid, hi) ->
                    constraint(lo..hi).validator(mid).shouldBeTrue()
                }
            }

            "returns false if the value is outside the range" {
                checkAll(Arb.orderedTriple(gen, strict = true)) { (lo, mid, hi) ->
                    constraint(lo..mid).validator(hi).shouldBeFalse()
                    constraint(mid..hi).validator(lo).shouldBeFalse()
                }
            }
        }
    }
}