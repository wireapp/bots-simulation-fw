package com.wire.bots.simulation.connector;

import java.util.UUID;

public class LoggedUser {
    public final UUID userId;
    public final UUID teamId;
    public final String token;

    public LoggedUser(UUID userId, UUID teamId, String token) {
        this.userId = userId;
        this.teamId = teamId;
        this.token = token;
    }
}
