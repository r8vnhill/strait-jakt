# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.2.0] - TBD

### Added
- New String Validation Constraints:
  - `BeAlphanumeric`: Validates if a string contains only alphanumeric characters.
  - `BeEmpty`: Checks if a string is empty.
  - `Contain`: Ensures a string matches a specified regular expression.
- Test Suites for String Constraints:
  - `BeAlphanumericTest` for validating alphanumeric strings.
  - `BeEmptyTest` for validating empty strings.
  - `ContainTest` for validating strings against regular expressions.
- `BeMonotonicallyIncreasing` Constraint: Validates that a collection of elements is monotonically increasing, useful for sorted lists and sequences.
- Special Characters Generator in `Strings` (datatypes): Facilitates creating test datasets with non-alphanumeric characters.
- `BeMonotonicallyDecreasing` Constraint:
  - A new constraint designed to validate if a collection of elements is monotonically decreasing.
  - Useful in scenarios such as verifying sorted arrays in descending order.
- `BeMonotonicallyDecreasingTest`:
  - A test suite developed to ensure the correctness of the `BeMonotonicallyDecreasing` constraint under various conditions.

### Removed
- Obsolete Pair Constraints:
  - `BeFinite.kt`
  - `BeStrictlyOrdered.kt`
  - `PairConstraint.kt`
  - Corresponding tests for these constraints.

### Changed
- Project Settings Files: Adjustments made for improved project configuration.
- `CollectionConstraint` Refactoring:
  - Enhanced to support generic collections.
  - Replaced wildcards with generic types for increased type safety and to avoid unexpected behaviors.

### Improved
- Documentation:
  - `BeAlphanumeric` and `BeEmpty` classes now have enhanced documentation for better readability and comprehension.
- `JaktTest` Enhancements:
  - Added assertions to verify the `BeEqualTo` constraint behavior.
  - Included comprehensive tests for `CompositeException` handling.
  - Assured correct application of constraints and appropriate exception handling.


## [1.1.0] - 2023-11-08

### Added
- Predicate support to the `HaveSize` constraint for more flexible collection size checks.
- A `CHANGELOG` file to the project for documenting changes per industry standards.

### Improved
- The `CompositeException` to ensure that it is only thrown when there is at least one exception present.

### Refactored
- The `Ranges` utility class to simplify its implementation.

### Removed
- Deprecated functions from the `Ranges` utility class.

### Changed
- Moved the `Ranges` utility class to an appropriate package to better reflect its usage within the project.
