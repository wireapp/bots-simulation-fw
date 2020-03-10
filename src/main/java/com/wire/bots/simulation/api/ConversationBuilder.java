package com.wire.bots.simulation.api;

import com.wire.bots.cryptobox.IStorage;
import com.wire.bots.sdk.WireClient;
import com.wire.bots.sdk.crypto.Crypto;
import com.wire.bots.sdk.crypto.CryptoDatabase;
import com.wire.bots.sdk.factories.CryptoFactory;
import com.wire.bots.sdk.user.UserClient;
import com.wire.bots.simulation.connector.ClientFactory;
import com.wire.bots.simulation.connector.LoggedUser;
import com.wire.bots.simulation.connector.LoginService;
import com.wire.bots.simulation.conversation.ConversationAccess;
import com.wire.bots.simulation.conversation.ConversationService;
import com.wire.bots.simulation.search.Service;
import com.wire.bots.simulation.search.ServiceSearch;

import javax.ws.rs.client.Client;
import java.util.List;

public class ConversationBuilder {
    private SimulationConfiguration simulationConfiguration;


    public void build(String botName, IStorage storage) throws Exception {
        Client client = ClientFactory.obtainClient(simulationConfiguration);

        final LoggedUser user = LoginService.login(client, simulationConfiguration);
        final ServiceSearch serviceSearch = new ServiceSearch(client, simulationConfiguration, user.token);
        final List<Service> servicesList = serviceSearch.search(user.teamId, botName);

        // TODO some validation is necessary
        final Service service = servicesList.get(0);

        final ConversationService conversationService = new ConversationService(client);
        final ConversationAccess conversationAccess = conversationService.createConversationWithService(user, service);

        CryptoFactory cryptoFactory = botId -> new CryptoDatabase(botId, storage);
        Crypto crypto = cryptoFactory.create(user.userId);

        WireClient wireClient = new UserClient(user.userId, "simulation-framework", conversationAccess.conversationId, crypto, conversationAccess.api);
        wireClient.sendText("txt");

    }
}
