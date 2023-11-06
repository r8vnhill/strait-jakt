package cl.ravenhill.jakt.arbs.datatypes

import io.kotest.property.Arb
import io.kotest.property.arbitrary.boolean
import io.kotest.property.arbitrary.byte
import io.kotest.property.arbitrary.char
import io.kotest.property.arbitrary.choice
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.float
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.short
import io.kotest.property.arbitrary.string

/**
 * Provides an arbitrary generator for producing values of type [Any].
 *
 * This function is an extension to the `Arb.Companion` and creates a generator that can produce a wide range
 * of basic Kotlin types. It ensures a diverse set of possible values, making it useful for property-based
 * tests that need a truly random value with no specific constraints.
 *
 * The produced types include:
 * - Booleans
 * - Bytes
 * - Chars
 * - Shorts
 * - Integers
 * - Floats
 * - Doubles
 * - Strings
 * - Lists (with random elements and lengths between 1 and 100)
 * - Maps (with random key-value pairs and sizes between 1 and 100)
 *
 * ## Examples
 * ### Example 1: Using in property-based tests
 * ```kotlin
 * checkAll(Arb.any()) { value ->
 *     // Some test logic
 * }
 * ```
 *
 * @return An [Arb] capable of generating random values of type [Any].
 *
 * @author <a href="https://www.github.com/r8vnhill">Ignacio Slater M.</a>
 * @since 1.0.0
 * @version 1.0.0
 */
fun Arb.Companion.anyPrimitive() = choice(
    boolean(),
    byte(),
    char(),
    short(),
    int(),
    float(),
    double(),
    string()
)
