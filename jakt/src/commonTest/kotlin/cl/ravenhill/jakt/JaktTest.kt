/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt

import cl.ravenhill.jakt.arbs.collectionHaveSize
import cl.ravenhill.jakt.arbs.datatypes.anyPrimitive
import cl.ravenhill.jakt.constraints.ints.BeEqualTo
import cl.ravenhill.jakt.exceptions.CompositeException
import cl.ravenhill.jakt.exceptions.ConstraintException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.property.Arb
import io.kotest.property.PropTestConfig
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

@OptIn(ExperimentalKotest::class, ExperimentalJakt::class)
class JaktTest : FreeSpec({

    "A [Jakt.Scope]" - {
        "should have a [StringScope] that" - {
            "can be created with a message" {
                checkAll<String>(PropTestConfig(iterations = 100)) { message ->
                    Jakt.Scope().StringScope(message).message shouldBe message
                }
            }

            "can be converted to a [String]" {
                checkAll<String> { message ->
                    Jakt.Scope().StringScope(message).toString() shouldBe
                          "StringScope(message='$message')"
                }
            }

            "when validating a `must` clause" - {
                "should add a [Success] or [Failure] to the [Jakt.Scope]" {
                    checkAll(
                        Arb.string(),
                        Arb.collectionHaveSize(Arb.int(0..10)),
                        Arb.list(Arb.list(Arb.anyPrimitive(), 0..10), 0..10)
                    ) { message, constraint, vss ->
                        with(Jakt.Scope().StringScope(message)) {
                            vss.forEach { vs ->
                                vs must constraint
                                if (constraint.validator(vs)) {
                                    outerScope.results.last().shouldBeSuccess()
                                } else {
                                    outerScope.results.last().shouldBeFailure()
                                }
                            }
                            outerScope.results.size shouldBe vss.size
                        }
                    }
                }
            }

            "when validating a `mustNot` clause" - {
                "should add a [Success] or [Failure] to the [Jakt.Scope]" {
                    checkAll(
                        Arb.string(),
                        Arb.collectionHaveSize(Arb.int(0..10)),
                        Arb.list(Arb.list(Arb.anyPrimitive(), 0..10), 0..10)
                    ) { message, constraint, vss ->
                        with(Jakt.Scope().StringScope(message)) {
                            vss.forEach { vs ->
                                vs mustNot constraint
                                if (!constraint.validator(vs)) {
                                    outerScope.results.last().shouldBeSuccess()
                                } else {
                                    outerScope.results.last().shouldBeFailure()
                                }
                            }
                            outerScope.results.size shouldBe vss.size
                        }
                    }
                }
            }

            "when validating a `constraint` clause" - {
                "should add a [Success] or [Failure] to the [Jakt.Scope]" {
                    checkAll(
                        Arb.string(),
                        Arb.collectionHaveSize(Arb.int(0..10)),
                        Arb.list(Arb.list(Arb.anyPrimitive(), 0..10), 0..10)
                    ) { message, constraint, vss ->
                        with(Jakt.Scope().StringScope(message)) {
                            vss.forEach { vs ->
                                constraint { constraint.validator(vs) }
                                if (constraint.validator(vs)) {
                                    outerScope.results.last().shouldBeSuccess()
                                } else {
                                    outerScope.results.last().shouldBeFailure()
                                }
                            }
                            outerScope.results.size shouldBe vss.size
                        }
                    }
                }
            }
        }

        "should have a list of [result]s that" - {
            "should be empty when created" {
                Jakt.Scope().results.shouldBeEmpty()
            }

            "should contain the results of the clauses" {
                checkAll(
                    Arb.string(),
                    Arb.collectionHaveSize(Arb.int(0..10)),
                    Arb.list(Arb.list(Arb.anyPrimitive(), 0..10), 0..10)
                ) { message, constraint, vss ->
                    with(Jakt.Scope().StringScope(message)) {
                        vss.forEach { vs ->
                            vs must constraint
                        }
                        outerScope.results.size shouldBe vss.size
                    }
                }
            }
        }

        "should have a list of [failure]s that" - {
            "should be empty when created" {
                Jakt.Scope().failures.shouldBeEmpty()
            }

            "should contain the failures of the clauses" {
                checkAll(
                    Arb.string(),
                    Arb.collectionHaveSize(Arb.int(0..10)),
                    Arb.list(Arb.list(Arb.anyPrimitive(), 0..10), 0..10)
                ) { message, constraint, vss ->
                    with(Jakt.Scope().StringScope(message)) {
                        var failures = 0
                        vss.forEach { vs ->
                            vs must constraint
                            if (!constraint.validator(vs)) {
                                failures++
                            }
                        }
                        outerScope.failures.size shouldBe failures
                    }
                }
            }

            "should be able to invoke a [String] to create a [StringScope]" {
                checkAll<String> { message ->
                    with(Jakt.Scope()) {
                        message { 1 must BeEqualTo(1) }
                        results.last().shouldBeSuccess()
                        results.last().getOrNull() shouldBe 1
                    }
                }
            }
        }

        "should be able to apply a series of constraints when" - {
            "the `skipChecks` flag is set to `true` should not throw an exception" {
                Jakt.skipChecks = true
                shouldNotThrow<CompositeException> {
                    Jakt.constraints {
                        "1" { 1 must BeEqualTo(2) }
                        "2" { 2 must BeEqualTo(3) }
                        "3" { 3 must BeEqualTo(4) }
                    }
                }
                Jakt.skipChecks = false
            }

            "all the constraints are met should not throw an exception" {
                shouldNotThrow<CompositeException> {
                    Jakt.constraints {
                        "1" { 1 must BeEqualTo(1) }
                        "2" { 2 must BeEqualTo(2) }
                        "3" { 3 must BeEqualTo(3) }
                    }
                }
            }

            "any of the constraints are not met should throw an exception" {
                shouldThrow<CompositeException> {
                    Jakt.constraints {
                        "1" { 1 must BeEqualTo(1) }
                        "2" { 2 must BeEqualTo(3) }
                        "3" { 3 must BeEqualTo(3) }
                    }
                }
            }
        }

        "can enforce a constraint" - {
            "with a specific exception" {
                shouldThrow<CompositeException> {
                    Jakt.constraints {
                        "1"(::TestConstraintException) { 1 must BeEqualTo(2) }
                    }
                }.throwables.first().shouldBeInstanceOf<TestConstraintException>().message shouldBe "1"
            }
        }
    }
})

private class TestConstraintException(message: String) : ConstraintException(message)
