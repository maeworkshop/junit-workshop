package com.maemresen.junit.basic.examples.nested.ordered;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

@TestClassOrder(ClassOrderer.Random.class)
class NestedOrderedByRandomTest {

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
