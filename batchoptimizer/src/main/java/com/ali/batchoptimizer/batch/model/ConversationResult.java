package com.ali.batchoptimizer.batch.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResult {

    private String ctaNo;
    private String mediaType;
    private String content;
}
