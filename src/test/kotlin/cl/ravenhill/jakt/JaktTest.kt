package cl.ravenhill.jakt

import cl.ravenhill.jakt.arbs.collectionHaveSize
import cl.ravenhill.jakt.arbs.datatypes.anyPrimitive
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class JaktTest : FreeSpec({

    "A [Jackt.Scope]" - {
        "should have a [StringScope] that" - {
            "can be created with a message" {
                checkAll<String> { message ->
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
    }
})
