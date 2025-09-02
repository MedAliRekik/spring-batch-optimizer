package com.ali.batchoptimizer.domain;

import java.util.List;

/**
 * 🎯 Interface métier pour fournir la liste des identifiants de conversation à traiter.
 * Peut être connectée à une base de données, un SDK externe ou une API.
 */
public interface ConversationService {
    List<String> getAllConversationIds();
}
