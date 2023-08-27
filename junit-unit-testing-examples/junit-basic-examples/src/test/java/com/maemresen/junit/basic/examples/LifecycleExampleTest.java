package com.maemresen.junit.basic.examples;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LifecycleExampleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("This will be executed once before all tests executed");
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        System.out.println("Executed before " + testInfo.getDisplayName());
    }


    @AfterAll
    static void afterAll() {
        System.out.println("This will be executed once after all tests executed");
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.println("Executed after " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Test1")
    void test1() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine", str);
    }

    @Test
    @DisplayName("Test2")
    void test2() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine", str);
    }
}
