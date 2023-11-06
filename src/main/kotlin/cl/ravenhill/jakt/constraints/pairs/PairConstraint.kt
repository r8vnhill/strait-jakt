/**
 * Copyright (c) 2023, R8V.
 * BSD Zero Clause License.
 */

package cl.ravenhill.jakt.constraints.pairs

import cl.ravenhill.jakt.constraints.Constraint
import cl.ravenhill.jakt.exceptions.PairConstraintException

/**
 * An interface for enforcing a constraint on a pair of elements.
 *
 * This interface extends the [Constraint] interface to specifically handle
 * constraints applicable to pairs. It requires implementing classes to define
 * their validation logic through the `validator` function, tailored for pairs
 * of elements of types [T] and [U].
 *
 * Upon validation failure, a [PairConstraintException] can be generated to
 * convey the specific constraint violation.
 *
 * ## Usage
 * Implement this interface when you need to apply constraints to a pair of elements,
 * and provide custom validation logic within the `validator` implementation.
 *
 * @param T The type of the first element in the pair.
 * @param U The type of the second element in the pair.
 *
 * @throws PairConstraintException when the pair does not satisfy the imposed constraint.
 */
interface PairConstraint<T, U> : Constraint<Pair<T, U>> {

    /**
     * Generates a [PairConstraintException] with the provided description.
     *
     * @param description A message describing the constraint violation.
     * @return A [PairConstraintException] with the given description.
     */
    override fun generateException(description: String) = PairConstraintException { description }
}
