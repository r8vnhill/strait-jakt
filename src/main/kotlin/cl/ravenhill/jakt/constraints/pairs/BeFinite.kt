package cl.ravenhill.jakt.constraints.pairs

/**
 * [BeFinite] is a [PairConstraint] that requires that both elements in a [Pair] are finite
 * doubles.
 */
data object BeFinite : PairConstraint<Double, Double> {
    override val validator =
        { value: Pair<Double, Double> -> value.first.isFinite() && value.second.isFinite() }
}