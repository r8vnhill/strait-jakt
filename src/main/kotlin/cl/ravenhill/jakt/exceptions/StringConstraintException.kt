package cl.ravenhill.jakt.exceptions

class StringConstraintException(lazyMessage: () -> String) : ConstraintException(lazyMessage)
