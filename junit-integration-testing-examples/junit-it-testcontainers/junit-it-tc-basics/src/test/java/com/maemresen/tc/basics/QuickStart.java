package com.maemresen.tc.basics;

import com.github.dockerjava.api.command.CreateContainerCmd;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Emre Şen (maemresen@yazilim.vip), 22/01/2023
 */
@Testcontainers
class QuickStart {

    @Container
    final GenericContainer testRedisContainer;

    QuickStart() {
        DockerImageName dockerImageName = DockerImageName.parse("redis:7.0.5-alpine");
        this.testRedisContainer = new GenericContainer(dockerImageName)
            .withCreateContainerCmdModifier((Consumer<CreateContainerCmd>) cmd -> cmd.withName("TEST-REDIS"))
            .withExposedPorts(6379)
            .waitingFor(Wait.forListeningPort());
    }


    @BeforeEach
    void logTestExecution(TestInfo testInfo) {
        System.out.println("Executing " + testInfo.getDisplayName());
    }


    @Test
    void test_dummyTest1() {
    }

    @Test
    void test_dummyTest2() {
    }
}
