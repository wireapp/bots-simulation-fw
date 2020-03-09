package com.wire.bots.simulation.connector;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.wire.bots.simulation.api.SimulationConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.client.Client;

/**
 * Factory for building client.
 */
public class ClientFactory {
    /**
     * Creates instance of Client for given configuration.
     */
    public static Client obtainClient(SimulationConfiguration configuration) {
        return new JerseyClientBuilder(configuration.environment)
                .using(configuration.jerseyClient)
                .withProvider(MultiPartFeature.class)
                .withProvider(JacksonJsonProvider.class)
                .build("bot-simulation");
    }
}
