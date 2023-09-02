package com.maemresen.it.tc.basics.shared.global;

import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractBaseGlobalSharedContainerTest {

    static final GenericContainer<?> GLOBAL_SHARED_CONTAINER = new GenericContainer<>("nginx:mainline-alpine3.18-slim");
    static final String initialContainerId;

    static {
        GLOBAL_SHARED_CONTAINER.start();
        initialContainerId = GLOBAL_SHARED_CONTAINER.getContainerId();
        System.out.println("Created container ID: " + initialContainerId);
    }

    protected void assertEqualsInitialGlobalContainerId() {
        assertEquals(initialContainerId, GLOBAL_SHARED_CONTAINER.getContainerId(), "Container ID should be same");
    }
}
