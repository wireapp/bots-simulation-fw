package com.wire.bots.simulation.server;

import com.wire.bots.sdk.MessageHandlerBase;
import com.wire.bots.sdk.Server;
import com.wire.bots.simulation.api.SimulationConfiguration;
import com.wire.bots.simulation.connector.LoggedUser;
import com.wire.bots.simulation.connector.LoginService;
import com.wire.bots.simulation.conversation.ConversationService;
import com.wire.bots.simulation.search.Service;
import com.wire.bots.simulation.search.ServiceSearch;
import io.dropwizard.setup.Environment;

import java.util.List;

public class SimulationService extends Server<SimulationConfiguration> {
    static SimulationService instance;

    public static void main(String[] args) throws Exception {
        instance = new SimulationService();
        instance.run(args);
    }

    @Override
    protected void initialize(SimulationConfiguration config, Environment env) {
    }

    @Override
    protected MessageHandlerBase createHandler(SimulationConfiguration config, Environment env) {
        return new MessageHandler();
    }


    @Override
    protected void onRun(SimulationConfiguration config, Environment env) throws Exception {
        final LoggedUser user = LoginService.login(client, config);

        final ServiceSearch serviceSearch = new ServiceSearch(client, config, user.token);
        final List<Service> servicesList = serviceSearch.search(user.teamId, config.botUnderTheTest);

        // TODO some validation is necessary
        final Service service = servicesList.get(0);

        final ConversationService conversationService = new ConversationService(client);

        conversationService.createConversationWithService(user, service);
    }
}
