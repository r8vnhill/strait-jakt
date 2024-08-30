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
