package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.ExperimentalJakt

@ExperimentalJakt
data object BeFinite : DoubleConstraint {
    override val validator: (Double) -> Boolean = { it.isFinite() }
}
