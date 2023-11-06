# StraitJakt: Validate Your Data with Precision

[![License](https://img.shields.io/badge/License-BSD_2--Clause-orange.svg)](https://opensource.org/licenses/BSD-2-Clause)

**StraitJakt** is a versatile Kotlin library designed to apply precise constraints to your data, ensuring it remains sane and valid.

## How to Use StraitJakt

Here's a brief example of how you can employ StraitJakt to validate user registration data:

```kotlin
fun registerUser(username: String, name: String, age: Int, password: String) {
    constraints {
        "Username should only contain alphanumeric characters" {
            username must BeAlphanumeric
        }
        "Username should be between 5 to 20 characters in length." {
            username must HaveLength { it in 5..20 }
        }
        "Username can only have underscores or hyphens as special characters" {
            username must Match(Regex("^[a-zA-Z0-9_-]*$"))
        }
        "Name field cannot be left blank" {
            name mustNot BeEmpty
        }
        "Users should be 18 years of age or older" {
            age must BeAtLeast(18)
        }
        "Password needs to be a minimum of 8 characters" {
            password must HaveLength { it >= 8 }
        }
        "Password should include at least one uppercase letter" {
            password must Contain(Regex("[A-Z]"))
        }
        "Password should include at least one lowercase letter" {
            password must Contain(Regex("[a-z]"))
        }
        "Password should include at least one digit" {
            password must Contain(Regex("[0-9]"))
        }
    }
    // Further implementation details...
}
```

Consider the following scenarios:

```kotlin
registerUser("johndoe", "John Doe", 17, "password")
```

This will throw an exception with the following message:

```
Exception in thread "main" cl.ravenhill.jakt.exceptions.CompositeException: Multiple exceptions occurred: { Users should be 18 years of age or older }, { Password should include at least one uppercase letter }, { Password should include at least one digit }
```

```kotlin
registerUser("johndoe", "John Doe", 18, "Password")
```

This will throw an exception with the following message:

```
StringConstraintException: Password should include at least one digit
```
