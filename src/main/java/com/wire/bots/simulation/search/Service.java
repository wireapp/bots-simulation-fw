package com.wire.bots.simulation.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * DTO class representing single service in the Wire bots context.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {
    @JsonProperty
    public String name;
    @JsonProperty
    public String description;
    @JsonProperty
    public String summary;
    @JsonProperty
    public String[] tags;
    /**
     * Service ID  that should be used for creating conversation with this service.
     */
    @JsonProperty("id")
    public UUID serviceId;
    @JsonProperty("provider")
    public UUID providerId;
    @JsonProperty
    public boolean enabled;
}
