package com.maemresen.tc.basics;

import com.github.dockerjava.api.command.CreateContainerCmd;
import java.util.function.Consumer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Emre Şen (maemresen@yazilim.vip), 11/12/2022
 */
class ContainerLifecycleManagementTest {

    static final String TEST_CONTAINER_NAME = "TEST-REDIS";
    final GenericContainer testRedisContainer;

    ContainerLifecycleManagementTest() {
        DockerImageName dockerImageName = DockerImageName.parse("redis:7.0.5-alpine");
        this.testRedisContainer = new GenericContainer(dockerImageName)
            .withCreateContainerCmdModifier((Consumer<CreateContainerCmd>) cmd -> cmd.withName(TEST_CONTAINER_NAME))
            .withExposedPorts(6379)
            .waitingFor(Wait.forListeningPort());
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        testRedisContainer.start();
        System.out.printf("Starting %s container for %s test%n", TEST_CONTAINER_NAME, testInfo.getDisplayName());
        System.out.println("Executing " + testInfo.getDisplayName());
    }

    @AfterEach
    void wrapUp(TestInfo testInfo) {
        if (testRedisContainer.isRunning()) {
            System.out.printf("Stopping %s container for %s test%n", TEST_CONTAINER_NAME, testInfo.getDisplayName());
            testRedisContainer.stop();
        } else {
            System.out.printf("Could not stop %s container for %s because it has already been stopped%n", TEST_CONTAINER_NAME, testInfo.getDisplayName());
        }
    }

    @Test
    void test_dummyTest1() {
    }

    @Test
    void test_stoppingContainer() {
        System.out.println("Stopping container.");
        testRedisContainer.stop();
    }

    @Test
    void test_stopAndStartContainer() {
        System.out.println("Stopping container");
        testRedisContainer.stop();
        System.out.println("Starting container");
        testRedisContainer.start();
    }
}
