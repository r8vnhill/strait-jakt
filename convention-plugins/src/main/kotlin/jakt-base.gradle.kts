/*
 * Copyright (c) 2024, Ignacio Slater M.
 * 2-Clause BSD License.
 */

import org.gradle.kotlin.dsl.base


// Apply the base plugin to the project.
// The base plugin provides basic tasks and configurations that are common to all projects, including tasks like `clean`
// and `assemble`.
plugins {
    base
}
