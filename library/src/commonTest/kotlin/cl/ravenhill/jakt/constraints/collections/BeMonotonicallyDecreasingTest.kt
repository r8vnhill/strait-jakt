package cl.ravenhill.jakt.constraints.collections

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class BeMonotonicallyDecreasingTest : FreeSpec({

    "A [BeMonotonicallyDecreasing] constraint" - {
        "when validating a collection" - {
            "if the `strict` flag is true" - {
                "should return true if the collection is monotonically decreasing" {
                    BeMonotonicallyDecreasing<Int>(true).validator(listOf(5, 4, 3, 2, 1)).shouldBeTrue()
                }

                "should return false if there are duplicate elements" {
                    BeMonotonicallyDecreasing<Int>(true).validator(listOf(5, 4, 3, 3, 1)).shouldBeFalse()
                }

                "should return false if the collection is not monotonically decreasing" {
                    BeMonotonicallyDecreasing<Int>(true).validator(listOf(5, 4, 3, 4, 1)).shouldBeFalse()
                }
            }

            "if the `strict` flag is false" - {
                "should return true if the collection is monotonically decreasing" {
                    BeMonotonicallyDecreasing<Int>(false).validator(listOf(5, 4, 3, 2, 1)).shouldBeTrue()
                }

                "should return true if there are duplicate elements" {
                    BeMonotonicallyDecreasing<Int>(false).validator(listOf(5, 4, 3, 3, 1)).shouldBeTrue()
                }

                "should return false if the collection is not monotonically decreasing" {
                    BeMonotonicallyDecreasing<Int>(false).validator(listOf(5, 4, 3, 4, 1)).shouldBeFalse()
                }
            }
        }
    }
})
