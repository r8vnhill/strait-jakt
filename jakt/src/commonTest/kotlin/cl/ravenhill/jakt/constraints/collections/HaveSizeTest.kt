/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

package cl.ravenhill.jakt.constraints.collections

import cl.ravenhill.jakt.arbs.datatypes.collection
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.collections.shouldNotHaveSize
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.assume
import io.kotest.property.checkAll

class HaveSizeTest : FreeSpec({

    "A [HaveSize] constraint" - {
        "when created" - {
            "with a size" - {
                "should have a validator that returns true if the collection has the specified size" {
                    checkAll(Arb.collection(), Arb.int()) { collection, size ->
                        assume { collection shouldNotHaveSize size }
                        HaveSize(size).validator(collection).shouldBeFalse()
                    }
                }

                "should have a validator that returns false if the collection does not have the specified size" {
                    checkAll(Arb.collection()) { collection ->
                        assume { collection.shouldNotBeEmpty() }
                        HaveSize(collection.size).validator(collection).shouldBeTrue()
                    }
                }
            }

            "with a predicate" - {
                "should have a validator that returns true if the collection has the specified size" {
                    checkAll(Arb.collection()) { collection ->
                        assume { collection.shouldNotBeEmpty() }
                        HaveSize { it > 0 }.validator(collection).shouldBeTrue()
                    }
                }

                "should have a validator that returns false if the collection does not have the specified size" {
                    checkAll(Arb.collection()) { collection ->
                        assume { collection.size shouldBeGreaterThan 5 }
                        HaveSize { it <= 5 }.validator(collection).shouldBeFalse()
                    }
                }
            }
        }

        "have a validator that" - {
            "returns true if the collection has the specified size" {
                checkAll(Arb.collection()) { collection ->
                    HaveSize(collection.size).validator(collection).shouldBeTrue()
                }
            }

            "returns false if the collection does not have the specified size" {
                checkAll(Arb.collection(), Arb.int()) { collection, size ->
                    assume { collection shouldNotHaveSize size }
                    HaveSize(size).validator(collection).shouldBeFalse()
                }
            }
        }
    }
})
