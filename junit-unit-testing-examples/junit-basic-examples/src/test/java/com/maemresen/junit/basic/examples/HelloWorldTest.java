package com.maemresen.junit.basic.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

    @Test
    void successTest() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine", str);
    }

    @Test
    void failureTest() {
        String str = "Junit is not working fine";
        assertEquals("Junit is working fine", str);
    }
}
