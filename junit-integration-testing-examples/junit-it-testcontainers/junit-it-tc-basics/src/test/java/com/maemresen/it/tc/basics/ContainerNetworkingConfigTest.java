package com.maemresen.it.tc.basics;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerNetworkingConfigTest {

    static final Network NETWORK = Network.newNetwork();

    @Test
    void expectResponseFromNginx() throws IOException, InterruptedException {
        try (
                final GenericContainer<?> nginxContainer = new GenericContainer<>("nginx:1.17.10-alpine");
                final GenericContainer<?> curlContainer = new GenericContainer<>("curlimages/curl:8.2.1")
        ) {
            final String expectedResponse = "{name: Emre, surname: Sen}";
            final String nginxNetworkAlias = "app-nginx";
            final int nginxNetworkPort = 8080;
            final String nginxHttpRequestListenerCmd = """
                        while true; 
                        do 
                            printf 'HTTP/1.1 200 OK\\n\\n%s' | nc -l -p %d;
                        done
                    """.formatted(expectedResponse, nginxNetworkPort);
            System.out.println("Created nginx command is: " + nginxHttpRequestListenerCmd);
            nginxContainer.withNetwork(NETWORK);
            nginxContainer.withNetworkAliases(nginxNetworkAlias);
            nginxContainer.withCommand("/bin/sh", "-c", nginxHttpRequestListenerCmd);

            curlContainer.withNetwork(NETWORK);
            curlContainer.withCommand("sleep", "infinity");

            nginxContainer.start();
            curlContainer.start();

            final var finalUrl = String.format("http://%s:%s", nginxNetworkAlias, nginxNetworkPort);
            System.out.println("CURL container will try to reach " + finalUrl);
            var execResult = curlContainer.execInContainer("curl", finalUrl);
            final var response = execResult.getStdout();
            System.out.println("Result: "+ response);
            assertEquals(expectedResponse, response, "response must be " + expectedResponse);
        }
    }
}
