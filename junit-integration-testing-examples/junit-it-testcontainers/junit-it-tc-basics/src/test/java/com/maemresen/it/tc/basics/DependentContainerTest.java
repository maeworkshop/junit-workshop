package com.maemresen.it.tc.basics;


import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startable;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class DependentContainerTest {

    @Container
    static final GenericContainer<?> REDIS_CONTAINER = new GenericContainer<>("redis:5.0.3-alpine");

    @Container
    static final GenericContainer<?> NGINX_CONTAINER = new GenericContainer<>("nginx:1.17.10-alpine");

    static {
        REDIS_CONTAINER.withExposedPorts(6379);
        REDIS_CONTAINER.withCreateContainerCmdModifier(cmd -> {
            System.out.println("Creating redis container");
        });

        NGINX_CONTAINER.withExposedPorts(80);
        NGINX_CONTAINER.withCreateContainerCmdModifier(cmd -> {
            System.out.println("Creating nginx container");
        });
        NGINX_CONTAINER.dependsOn(REDIS_CONTAINER);
    }

    @Test
    void test() {
        Set<Startable> dependencies = NGINX_CONTAINER.getDependencies();
        assertNotNull(dependencies);
        assertTrue(dependencies.contains(REDIS_CONTAINER), "nginx should depend on redis");
    }
}
