package cl.ravenhill.jakt.constraints.collections

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class BeMonotonicallyIncreasingTest : FreeSpec({

    "A [BeMonotonicallyIncreasing] constraint" - {
        "when validating a collection" - {
            "if the `strict` flag is true" - {
                "should return true if the collection is monotonically increasing" {
                    BeMonotonicallyIncreasing<Int>(true).validator(listOf(1, 2, 3, 4, 5)).shouldBeTrue()
                }

                "should return false if there are duplicate elements" {
                    BeMonotonicallyIncreasing<Int>(true).validator(listOf(1, 2, 3, 3, 5)).shouldBeFalse()
                }

                "should return false if the collection is not monotonically increasing" {
                    BeMonotonicallyIncreasing<Int>(true).validator(listOf(1, 2, 3, 2, 5)).shouldBeFalse()
                }
            }

            "if the `strict` flag is false" - {
                "should return true if the collection is monotonically increasing" {
                    BeMonotonicallyIncreasing<Int>(false).validator(listOf(1, 2, 3, 4, 5)).shouldBeTrue()
                }

                "should return true if there are duplicate elements" {
                    BeMonotonicallyIncreasing<Int>(false).validator(listOf(1, 2, 3, 3, 5)).shouldBeTrue()
                }

                "should return false if the collection is not monotonically increasing" {
                    BeMonotonicallyIncreasing<Int>(false).validator(listOf(1, 2, 3, 2, 5)).shouldBeFalse()
                }
            }
        }
    }
})
