package com.ali.batchoptimizer.app.jobs.reader;


import com.ali.batchoptimizer.domain.ConversationResult;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 📥 Reader simple : retourne une liste de conversations simulées.
 * Permet de tester le batch sans base de données.
 */
@Configuration
public class ConversationReader {

    @Bean
    public ListItemReader<ConversationResult> reader() {
        return new ListItemReader<>(
                List.of(
                        new ConversationResult("CTA001", null, "Message A"),
                        new ConversationResult("CTA002", null, "Message B"),
                        new ConversationResult("CTA003", null, "Message C")
                )
        );
    }
}