package com.wire.bots.simulation.conversation;

import com.wire.bots.sdk.user.API;

import java.util.UUID;

public class ConversationAccess {
    public final API api;
    public final UUID conversationId;

    public ConversationAccess(API api, UUID conversationId) {
        this.api = api;
        this.conversationId = conversationId;
    }
}
