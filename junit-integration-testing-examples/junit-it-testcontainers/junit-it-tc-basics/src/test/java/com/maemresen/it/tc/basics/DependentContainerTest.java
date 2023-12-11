package com.maemresen.it.tc.basics;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DependentContainerTest {
	GenericContainer<?> redisContainer;
	GenericContainer<?> nginxContainer;

	@BeforeEach
	void init(TestInfo testInfo) {
		final var testName = testInfo.getDisplayName();
		redisContainer = getContainer("redis:5.0.3-alpine", "RedisContainer" + testName);
		nginxContainer = getContainer("nginx:1.17.10-alpine", "NginxContainer" + testName);
	}

	@AfterEach
	void tearDown(){
		redisContainer.stop();
		nginxContainer.stop();
	}

	private GenericContainer<?> getContainer(final String image, final String name) {
		try(final var container = new GenericContainer<>(image)) {
			container.withCreateContainerCmdModifier(cmd -> cmd.withName(name));
			return container;
		}
	}

	@Test
	@DisplayName("WithDependency")
	void redisContainerShouldBeCreated() {
		nginxContainer.dependsOn(redisContainer);
		nginxContainer.start();
		assertAll(
			() -> assertTrue(redisContainer.isCreated()),
			() -> assertTrue(nginxContainer.isCreated())
		);
	}

	@Test
	@DisplayName("NoDependency")
	void redisContainerShouldNotBeCreated() {
		nginxContainer.start();
		assertAll(
			() -> assertFalse(redisContainer.isCreated()),
			() -> assertTrue(nginxContainer.isCreated())
		);
	}
}

