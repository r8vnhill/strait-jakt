# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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
