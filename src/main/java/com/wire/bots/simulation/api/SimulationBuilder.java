package com.wire.bots.simulation.api;

import com.wire.bots.cryptobox.IStorage;

public class SimulationBuilder {
    private String username;
    private String password;

    private String host;

    private IStorage storage;

    public SimulationBuilder addStorage(IStorage storage) {
        this.storage = storage;
        return this;
    }
}
