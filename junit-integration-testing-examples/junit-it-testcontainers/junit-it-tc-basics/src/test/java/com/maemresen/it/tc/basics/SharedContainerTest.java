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
    static final GenericContainer<?> SHARED_CONTAINER = new GenericContainer<>("hello-world:latest");

    private static String initialContainerId;

    @BeforeAll
    static void beforeAll() {
        initialContainerId = SHARED_CONTAINER.getContainerId();
    }

    @Test
    void test_dummyTest1() {
        assertEquals(initialContainerId, SHARED_CONTAINER.getContainerId(), "Container ID should be same for test 1");
    }

    @Test
    void test_dummyTest2() {
        assertEquals(initialContainerId, SHARED_CONTAINER.getContainerId(), "Container ID should be same for test 2");
    }
}
