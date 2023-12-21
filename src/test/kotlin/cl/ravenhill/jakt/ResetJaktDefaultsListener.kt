package cl.ravenhill.jakt

import io.kotest.property.PropTestListener

/**
 * A property test listener that resets Jakt's default settings after each test.
 *
 * `ResetJaktDefaultsListener` is a singleton data object that implements the [PropTestListener] interface. Its primary
 * role is to reset the default settings of the Jakt library to their original state after the execution of each
 * property test. This ensures that the changes made to Jakt's settings during a test do not affect subsequent tests,
 * maintaining test isolation and consistency.
 *
 * ## Usage:
 * This listener can be attached to property tests where Jakt's settings are modified. It is especially useful in a
 * testing suite where multiple tests require different configurations of Jakt's settings, and a consistent starting
 * state is necessary for each test.
 *
 * ### Example: Attaching to a property test
 * ```
 * class MyPropertyTest : StringSpec({
 *
 *     "my test" {
 *         checkAll(
 *             PropTestConfig(
 *                 listeners = listOf(ResetJaktDefaultsListener),
 *                 /* other configuration settings */
 *             ), /* other parameters */
 *         ) { /* test logic */ }
 *     }
 * })
 * ```
 *
 * By attaching this listener to a property test, the default settings of Jakt are restored after the test is executed.
 */
data object ResetJaktDefaultsListener : PropTestListener {
    override suspend fun afterTest() {
        Jakt.skipChecks = false
        Jakt.shortCircuit = false
    }
}
