package com.ali.batchoptimizer.batch.service;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🧪 Retourne un mediaType simulé pour chaque conversation.
 */
@Service
public class FakeMediaTypeService implements MediaTypeService {
    @Override
    public Map<String, String> getMediaTypesFor(List<String> conversationIds) {
        Map<String, String> map = new HashMap<>();
        for (String id : conversationIds) {
            map.put(id, id.endsWith("1") ? "call" : "email");
        }
        return map;
    }
}