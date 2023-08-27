# Testing Spring Boot with PostgreSQL using Testcontainers

A straightforward guide on testing Spring Boot applications connected to a PostgreSQL database using Testcontainers.

## 📑 Table of Contents

- [Introduction](#introduction)
- [How to Use](#how-to-use)
- [Test Scenarios](#test-scenarios)
- [Wrap-up](#wrap-up)

## Introduction

This repository showcases how to leverage Testcontainers to run integration tests against a PostgreSQL database within a Spring Boot application. It helps developers to ensure data interactions are properly tested without setting up a real database instance.

## How to Use

1. 🐳 Ensure Docker is installed and actively running.
2. 📦 Clone this repository.
3. 🚀 Execute the tests either via your favorite IDE or by running the Maven/Gradle test task.

## Test Scenarios

### 1. Database Accessibility Test

**Class**: `DatabaseAccessibilityTest`

**Key Points**:
- A PostgreSQL container is initialized.
- Spring's `DynamicPropertySource` sets the database connection properties to the ones from the container.
- **Test Case**: The database connection is tested by invoking a repository method and then tested for failure after intentionally stopping the container.

### 2. Person Service Tests

**Class**: `PersonServiceImplTest`

**Key Points**:
- Similar to the `DatabaseAccessibilityTest`, a PostgreSQL container is set up, and Spring's properties are overridden.
- Multiple test cases are provided for various CRUD operations and edge cases:
    - **Add Multiple Persons**: Inserts a set of mocked `Person` entities and then retrieves them for validation.
    - **Find by Name (Case Sensitive)**: Validates fetching by name, considering the case sensitivity.
    - **Find by Name (Case Insensitive)**: Validates fetching by name, disregarding the case sensitivity.
    - **Find by Non-Existent Name**: Checks the behavior when trying to retrieve a non-existent `Person` entity.

## Wrap-up

Integration testing in a Spring Boot application, especially with databases, can be challenging. However, Testcontainers simplify this process by allowing you to test against real database instances without tedious setup steps. With this approach, you can ensure that your application will behave correctly in a production-like environment.

Dive in, run the tests, and explore the code to get a comprehensive understanding. Happy coding and testing! 🚀🧪
