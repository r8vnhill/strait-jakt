package cl.ravenhill.jakt.constraints.collections


data class BeMonotonicallyDecreasing<T>(val strict: Boolean = false) : CollectionConstraint<T> where T : Comparable<T> {
    override val validator = { value: Collection<T> ->
        value.zipWithNext().all { (a, b) ->
            if (strict) {
                a > b
            } else {
                a >= b
            }
        }
    }
}