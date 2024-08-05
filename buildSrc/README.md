# Convention Plugins

Adapted from [Kotest `buildSrc`](https://github.com/kotest/kotest/tree/master/buildSrc).

This project contains a number of plugins that can be applied to a project to enforce conventions.

## Kotlin conventions

Configures a base project which uses kotlin-multiplatform (with no targets specified).

Here we configure anything that should apply to _every_ project, such as common plugins, repositories which should be
used, etc.

## Jakt JVM conventions
Adds a JVM target and sets basic JVM options

## Jakt JS conventions
Adds JS targets

## Jakt native conventions
Adds native targets and creates a common native source set (`desktopMain` / `desktopTest`)

## Jakt publishing conventions
Adds everything related to signing and publishing the libraries
