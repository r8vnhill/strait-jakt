# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.2.0] - TBD

### Added
- `BeAlphanumericTest` to provide testing for alphanumeric string validation.
- `StringConstraintTest` for testing generic string constraints.
- Special characters generator (`Strings` in datatypes) for creating test datasets with non-alphanumeric characters.
- New string validation constraints:
    - `BeAlphanumeric` to validate if a string is composed solely of alphanumeric characters.
    - `BeEmpty` to check if a string is empty.
    - `Contain` to ensure a string matches a specified regular expression.
- Test suites for the newly added string constraints:
    - `BeEmptyTest` for testing the `BeEmpty` constraint.
    - `ContainTest` for testing the `Contain` constraint.


### Removed
- Pair constraints (`BeFinite.kt`, `BeStrictlyOrdered.kt`, `PairConstraint.kt`) and their corresponding tests. These constraints were found to be obsolete or redundant.

### Changed
- Made adjustments to project settings files for improved project configuration.

### Improved
- Enhanced documentation for `BeAlphanumeric` and `BeEmpty` classes, providing more comprehensive explanations and code comments for better readability and understanding.

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
