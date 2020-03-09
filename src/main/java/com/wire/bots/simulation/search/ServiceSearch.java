package com.wire.bots.simulation.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wire.bots.sdk.Configuration;

import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class providing search functionality.
 */
public class ServiceSearch {
    private final WebTarget target;
    private final String token;

    public ServiceSearch(Client client, Configuration config, @NotNull String token) {
        this.token = token;
        target = client.target(config.apiHost);
    }

    /**
     * Search for the service starting with given prefix for given team.
     */
    public List<Service> search(UUID teamId, String prefix) throws IOException {
        final Response response = target.
                path("teams").
                path(teamId.toString()).
                path("services").
                path("whitelisted").
                queryParam("prefix", prefix).
                request(MediaType.APPLICATION_JSON).
                header("Authorization", "Bearer " + token).
                get();

        if (response.getStatus() != 200) {
            throw new IOException(response.readEntity(String.class));
        }

        final Result result = response.readEntity(Result.class);
        return result.services;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Result {
        @JsonProperty("has_more")
        public boolean hasMore;
        @JsonProperty
        public ArrayList<Service> services;
    }

}
