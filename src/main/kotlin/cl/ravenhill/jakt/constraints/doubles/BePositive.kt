package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.ExperimentalJakt
import cl.ravenhill.jakt.constraints.BePositiveConstraint

@ExperimentalJakt
data object BePositive : DoubleConstraint, BePositiveConstraint<Double> {
    override val zero = 0.0
}
