package cl.ravenhill.jakt.arbs.datatypes

import io.kotest.property.Arb
import io.kotest.property.arbitrary.Codepoint
import io.kotest.property.arbitrary.of

/**
 * Provides an arbitrary generator for non-alphanumeric codepoints.
 *
 * This function creates a generator that produces codepoints representing non-alphanumeric characters,
 * specifically within the range of printable ASCII characters ('!' to '~'). It filters out all
 * alphanumeric characters (both letters and digits) from this range.
 *
 * This generator is useful for testing scenarios where non-alphanumeric characters are required,
 * such as password validation, input sanitization, or any case where special characters are involved.
 *
 * ## Usage
 * ### Example: Using in a property test
 * ```kotlin
 * checkAll(Arb.string(1..25, Codepoint.nonAlphanumeric())) { value ->
 *    // Test logic
 * }
 * ```
 *
 * @return An [Arb] of [Codepoint] representing non-alphanumeric characters.
 */
fun Codepoint.Companion.nonAlphanumeric() =
    Arb.of(('!'..'~').filterNot { it.isLetterOrDigit() }.map { Codepoint(it.code) })

