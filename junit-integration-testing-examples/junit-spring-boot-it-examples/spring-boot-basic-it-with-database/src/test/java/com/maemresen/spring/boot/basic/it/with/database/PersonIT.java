package com.maemresen.spring.boot.basic.it.with.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // Indicates that this is a Spring Boot integration test, so Spring's context will be loaded
class PersonIT {

    @Autowired  // Injects the required Spring bean, in this case, our service for managing persons
    private PersonService personService;

    // Utility method to generate a unique string. Useful for creating unique usernames and passwords.
    private String randomString() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String randomUsername() {
        return "johndoe" + randomString();
    }

    private String randomPassword() {
        return "password" + randomString();
    }

    @Test  // Indicates this is a test method for JUnit
    void testPersonRegistration() {
        // Generate a unique username and create a new person object
        String username = randomUsername();
        Person newUser = new Person(null, username, randomPassword());

        // Register the new person via the service
        personService.register(newUser);

        // Retrieve the person we've just registered to validate the registration process
        Person foundUser = personService.findByUsername(username);

        // Assertions to validate the correctness of our test
        assertNotNull(foundUser, "Newly registered person should be found in the database");
        assertEquals(username, foundUser.getUsername(), "Registered and found persons should have the same username");
    }

    @Test
    void testDuplicateUsernameRegistration() {
        // Register the first person
        String username = randomUsername();
        Person firstPerson = new Person(null, username, randomPassword());
        personService.register(firstPerson);

        // Attempt to register another person with the same username to check the uniqueness constraint
        Person duplicatePerson = new Person(null, username, randomPassword());

        // We expect a DataIntegrityViolationException since usernames should be unique in our system
        assertThrows(DataIntegrityViolationException.class, () -> personService.register(duplicatePerson), "Registration with a duplicate username should throw an exception");
    }

    @Test
    void testRegistrationWithMissingData() {
        // Create person instances with missing crucial data
        Person personMissingUsername = new Person(null, null, "password123");
        Person personMissingPassword = new Person(null, "johndoe", null);

        // We expect DataIntegrityViolationException since our database should enforce the presence of these fields
        assertAll("Registration with missing data should throw an exception",
                () -> assertThrows(DataIntegrityViolationException.class, () -> personService.register(personMissingUsername), "Registration with a missing username should throw an exception"),
                () -> assertThrows(DataIntegrityViolationException.class, () -> personService.register(personMissingPassword), "Registration with a missing password should throw an exception"));
    }

    @Test
    void testQueryingNonexistentPerson() {
        // Try to retrieve a person that surely doesn't exist
        Person foundPerson = personService.findByUsername("nonexistentperson");

        // We expect no person to be found in such a case
        assertNull(foundPerson, "Querying for a non-existent person should return null");
    }
}
