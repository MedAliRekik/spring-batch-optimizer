package com.ali.batchoptimizer.infra.adapters;


import com.ali.batchoptimizer.domain.ConversationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 🧪 Implémentation factice pour tester localement.
 */
@Service
public class FakeConversationService implements ConversationService {
    @Override
    public List<String> getAllConversationIds() {
        return List.of("CTA001", "CTA002", "CTA003");
    }
}
