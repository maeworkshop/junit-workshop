package com.maemresen.junit.basic.examples.nested;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NestedTest {

    @Nested
    class NestedTest1 {
        @Test
        void nestedTest1_test1() {
            System.out.println("nestedTest1_test1");
        }

        @Test
        void nestedTest1_test2() {
            System.out.println("nestedTest1_test2");
        }
    }

    @Nested
    class NestedTest2 {
        @Test
        void nestedTest2_test1() {
            System.out.println("nestedTest2_test1");
        }

        @Test
        void nestedTest2_test2() {
            System.out.println("nestedTest2_test2");
        }
    }
}
