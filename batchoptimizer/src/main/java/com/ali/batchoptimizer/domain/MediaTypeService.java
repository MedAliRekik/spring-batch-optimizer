package com.ali.batchoptimizer.domain;

import java.util.List;
import java.util.Map;

/**
 * 🎯 Service qui récupère les mediaTypes associés à chaque conversation.
 * L’implémentation peut appeler une API en masse ou une base optimisée.
 */
public interface MediaTypeService {
    Map<String, String> getMediaTypesFor(List<String> conversationIds);
}