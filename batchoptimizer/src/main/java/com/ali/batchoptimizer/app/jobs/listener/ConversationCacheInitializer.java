package com.ali.batchoptimizer.app.jobs.listener;



import com.ali.batchoptimizer.domain.ConversationService;
import com.ali.batchoptimizer.domain.MediaTypeService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Composant Spring Batch responsable de l'initialisation d'un cache
 * contenant les mediaTypes associés aux conversations.
 *
 * Ce cache est préchargé au début du step pour éviter des appels API redondants.
 *
 * Respecte le principe SRP (Responsabilité unique) : une seule fonction = préparer le cache.
 */
@Component
public class ConversationCacheInitializer implements StepExecutionListener {

    private final ConversationService conversationService;
    private final MediaTypeService mediaTypeService;

    // 🔐 Cache local utilisé par le processor (readonly après initialisation)
    private Map<String, String> mediaTypeCache = new HashMap<>();

    // 🧱 Injection des services via constructeur (respect du DIP)
    public ConversationCacheInitializer(ConversationService conversationService,
                                        MediaTypeService mediaTypeService) {
        this.conversationService = conversationService;
        this.mediaTypeService = mediaTypeService;
    }

    // Permet au processor d'accéder au cache (lecture uniquement)
    public Map<String, String> getMediaTypeCache() {
        return mediaTypeCache;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // 1️⃣ Récupération des identifiants de conversation depuis la source
        List<String> conversationIds = conversationService.getAllConversationIds();

        // 2️⃣ Appel optimisé (bulk) pour récupérer les mediaTypes
        mediaTypeCache = mediaTypeService.getMediaTypesFor(conversationIds);

        System.out.printf("[INIT] Cache initialisé avec %d conversations.%n", mediaTypeCache.size());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // 3️⃣ Nettoyage du cache pour éviter les fuites mémoire
        mediaTypeCache.clear();
        return ExitStatus.COMPLETED;
    }
}
