package cl.ravenhill.jakt.constraints.doubles

import cl.ravenhill.jakt.constraints.BeNegativeConstraint

data object BeNegative : DoubleConstraint, BeNegativeConstraint<Double> {
    override val zero: Double = 0.0
}
