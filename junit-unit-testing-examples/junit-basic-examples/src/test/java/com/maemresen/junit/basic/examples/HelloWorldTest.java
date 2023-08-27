package com.maemresen.junit.basic.examples;

// Import necessary JUnit 5 annotations and methods.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// The HelloWorldTest class contains basic test methods demonstrating JUnit functionality.
class HelloWorldTest {

    // A test method that will pass because the assertion condition is true.
    @Test
    void successTest() {
        String str = "Junit is working fine";
        // This assertion checks if the value of "str" is "Junit is working fine".
        // Since it is, this test will pass.
        assertEquals("Junit is working fine", str, "Expected string to match the defined value");
    }

    // A test method that will fail due to a mismatch in expected and actual values.
    @Test
    void failureTest() {
        String str = "Junit is not working fine";
        // This assertion checks if the value of "str" is "Junit is working fine".
        // Since "str" has a different value, this test will fail.
        assertEquals("Junit is working fine", str, "Expected string to match the defined value but it did not");
    }
}
