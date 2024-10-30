/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints

import cl.ravenhill.jakt.arbs.datatypes.anyPrimitive
import cl.ravenhill.jakt.constrained
import cl.ravenhill.jakt.constrainedTo
import cl.ravenhill.jakt.exceptions.ConstraintException
import cl.ravenhill.jakt.shouldContainExceptionOfType
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.checkAll

class BeNullTest : FreeSpec({

    "A BeNull constraint" - {
        "when checking if a null value" - {
            "is null" - {
                "with a constrained block" - {
                    "should return right" {
                        constrained {
                            "'null' must be null" { null must BeNull }
                        }.shouldBeRight()
                    }
                }

                "with a constrainedTo block" - {
                    "should return null wrapped in a right" {
                        null.constrainedTo {
                            "'null' must be null" { it must BeNull }
                        }.shouldBeRight(null)
                    }
                }
            }

            "is not null" - {
                "with a constrained block" - {
                    "should return left" {
                        constrained {
                            "null must not be null" { null mustNot BeNull }
                        }
                            .shouldBeLeft()
                            .shouldContainExceptionOfType<ConstraintException>(
                                "null must not be null"
                            )
                    }
                }

                "with a constrainedTo block" - {
                    "should return left" {
                        null.constrainedTo {
                            "'null' must not be null" { it mustNot BeNull }
                        }
                            .shouldBeLeft()
                            .shouldContainExceptionOfType<ConstraintException>(
                                "'null' must not be null"
                            )
                    }
                }
            }
        }

        "when checking if a non-null value" - {
            "is null" - {
                "with a constrained block" - {
                    "should return left" {
                        checkAll(Arb.anyPrimitive()) { value ->
                                constrained {
                                    "'$value' must be null" { value must BeNull }
                                }
                                    .shouldBeLeft()
                                    .shouldContainExceptionOfType<ConstraintException>(
                                        "'$value' must be null"
                                    )
                            }
                        }
                }

                "with a constrainedTo block" - {
                    "should return left" {
                        checkAll(Arb.anyPrimitive()) { value ->
                            value.constrainedTo {
                                "'$value' must be null" { it must BeNull }
                            }
                                .shouldBeLeft()
                                .shouldContainExceptionOfType<ConstraintException>(
                                    "'$value' must be null"
                                )
                        }
                    }
                }
            }

            "is not null" - {
                "with a constrained block" - {
                    "should return right" {
                        checkAll(Arb.anyPrimitive()) { value ->
                            constrained {
                                "'$value' must not be null" { value mustNot BeNull }
                            }.shouldBeRight()
                        }
                    }
                }

                "with a constrainedTo block" - {
                    "should return right with the value" {
                        checkAll(Arb.anyPrimitive()) { value ->
                            value.constrainedTo {
                                "'$value' must not be null" { it mustNot BeNull }
                            }.shouldBeRight(value)
                        }
                    }
                }
            }
        }
    }
})
