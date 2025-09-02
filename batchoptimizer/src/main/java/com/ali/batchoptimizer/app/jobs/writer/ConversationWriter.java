package com.ali.batchoptimizer.app.jobs.writer;

import com.ali.batchoptimizer.domain.ConversationResult;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 🖨 Writer qui exporte les conversations dans un fichier par type (call, email...) dans /data/statistic/
 */
@Component
public class ConversationWriter implements ItemWriter<ConversationResult> {
    private final String outputDir = "data/statistic";
    private final String timestamp;

    public ConversationWriter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        this.timestamp = LocalDateTime.now().format(formatter);
        new File(outputDir).mkdirs(); // crée le dossier s'il n'existe pas
    }

    @Override
    public void write(Chunk<? extends ConversationResult> chunk) throws Exception {
        Map<String, List<ConversationResult>> groupedByType = new HashMap<>();
        for (ConversationResult conv : chunk) {
            groupedByType
                    .computeIfAbsent(conv.getMediaType(), key -> new ArrayList<>())
                    .add(conv);
        }

        for (Map.Entry<String, List<ConversationResult>> entry : groupedByType.entrySet()) {
            String mediaType = entry.getKey();
            List<ConversationResult> conversations = entry.getValue();

            String filename = String.format("Export_%s_%s.txt", mediaType.toUpperCase(), timestamp);
            File file = new File(outputDir, filename);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                for (ConversationResult conv : conversations) {
                    String line = String.format("CTA: %s | Type: %s | Contenu: %s",
                            conv.getCtaNo(),
                            conv.getMediaType(),
                            conv.getContent());
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}