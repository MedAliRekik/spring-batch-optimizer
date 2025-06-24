package com.ali.batchoptimizer.batch.processor;



import com.ali.batchoptimizer.batch.listener.ConversationCacheInitializer;
import com.ali.batchoptimizer.batch.model.ConversationResult;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * ⚙️ Traitement des conversations avec accès optimisé au cache mediaType.
 *
 * On applique ici le principe SRP (une seule responsabilité : enrichir l'objet).
 */
@Component
public class ConversationProcessor implements ItemProcessor<ConversationResult, ConversationResult> {

    private final ConversationCacheInitializer cacheInitializer;

    // ✅ Injection du listener contenant le cache
    public ConversationProcessor(ConversationCacheInitializer cacheInitializer) {
        this.cacheInitializer = cacheInitializer;
    }

    @Override
    public ConversationResult process(ConversationResult item) {
        // 🔍 Lecture du cache en mémoire préchargé par ConversationCacheInitializer
        String mediaType = cacheInitializer.getMediaTypeCache().get(item.getCtaNo());

        if (mediaType == null) {
            mediaType = "unknown";
        }

        // ✏️ Mise à jour de l’objet avant écriture
        item.setMediaType(mediaType);
        return item;
    }
}