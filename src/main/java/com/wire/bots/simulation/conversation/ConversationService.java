package com.wire.bots.simulation.conversation;

import com.wire.bots.sdk.exceptions.HttpException;
import com.wire.bots.sdk.server.model.Conversation;
import com.wire.bots.sdk.server.model.User;
import com.wire.bots.sdk.tools.Logger;
import com.wire.bots.sdk.user.API;
import com.wire.bots.simulation.search.Service;

import javax.ws.rs.client.Client;

import java.util.UUID;

/**
 * Service used for managing the conversation resources.
 */
public class ConversationService {

    private Client client;

    public ConversationService(Client client) {
        this.client = client;
    }

    /**
     * Creates new conversation between the current user and provided service.
     * Returns ID of the conversation.
     */
    public UUID connectService(UUID teamId, Service service, String token) throws HttpException {
        API api = new API(client, null, token);
        final Conversation conv = api.createConversation(service.name, teamId, null);

        api = new API(client, conv.id, token);
        final User bot = api.addService(service.serviceId, service.providerId);

        Logger.info("New Bot  `%s`, id:: %s", bot.name, bot.id);

        return conv.id;
    }
}
