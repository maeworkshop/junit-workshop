package com.maemresen.it.tc.basics;

import com.github.dockerjava.api.command.CreateContainerCmd;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Emre Şen (maemresen@yazilim.vip), 22/01/2023
 */
@Testcontainers
class SharedContainerTest {

    @Container
    static final GenericContainer<?> SHARED_CONTAINER = new GenericContainer<>("nginx:mainline-alpine3.18-slim");

    private static String initialContainerId;

    @BeforeAll
    static void beforeAll() {
        initialContainerId = SHARED_CONTAINER.getContainerId();
    }

    @Test
    void test_dummyTest1() {
        assertNotNewContainerCreated(SHARED_CONTAINER);
    }

    @Test
    void test_dummyTest2() {
        assertNotNewContainerCreated(SHARED_CONTAINER);
    }

    protected void assertNotNewContainerCreated(GenericContainer<?> container) {
        assertEquals(initialContainerId, container.getContainerId(), "Container ID should be same");
    }
}
