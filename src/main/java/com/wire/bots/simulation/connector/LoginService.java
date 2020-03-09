package com.wire.bots.simulation.connector;

import com.wire.bots.sdk.exceptions.HttpException;
import com.wire.bots.sdk.tools.Logger;
import com.wire.bots.sdk.user.API;
import com.wire.bots.sdk.user.LoginClient;
import com.wire.bots.sdk.user.model.Access;
import com.wire.bots.simulation.api.SimulationConfiguration;

import javax.ws.rs.client.Client;
import java.util.UUID;

/**
 * Class that can be used for logging the user in.
 */
public class LoginService {
    /**
     * Logs in user as specified in given configuration.
     */
    public static LoggedUser login(Client client, SimulationConfiguration configuration) throws HttpException {
        final String email = configuration.userMode.email;
        final String password = configuration.userMode.password;

        final LoginClient loginClient = new LoginClient(client);
        final Access access = loginClient.login(email, password);

        final String token = access.getToken();
        final UUID userId = access.getUserId();

        final API api = new API(client, null, token);
        final UUID teamId = api.getTeam();

        Logger.info("Logged in as: %s, id: %s", email, userId);

        return new LoggedUser(userId, teamId, token);
    }
}
