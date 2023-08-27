package com.maemresen.junit.basic.examples.ordered;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedByOrderAnnotationTest {

    @Test
    @Order(2)
    void test1() {
        System.out.println("test1");
    }

    @Test
    @Order(0)
    void test2() {
        System.out.println("test2");
    }

    @Test
    @Order(1)
    void test3() {
        System.out.println("test3");
    }
}