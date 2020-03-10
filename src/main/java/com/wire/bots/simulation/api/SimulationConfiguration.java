package com.wire.bots.simulation.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wire.bots.sdk.Configuration;

import javax.validation.constraints.NotNull;

public class SimulationConfiguration extends Configuration {

    @JsonProperty("testedBot")
    @NotNull
    public String botUnderTheTest;
}
