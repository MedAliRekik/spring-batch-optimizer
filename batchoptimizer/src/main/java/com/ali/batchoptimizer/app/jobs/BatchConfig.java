package com.ali.batchoptimizer.app.jobs;



import com.ali.batchoptimizer.app.jobs.listener.ConversationCacheInitializer;
import com.ali.batchoptimizer.app.jobs.processor.ConversationProcessor;
import com.ali.batchoptimizer.app.jobs.writer.ConversationWriter;
import com.ali.batchoptimizer.domain.ConversationResult;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public Job conversationJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("conversationJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
    @Bean
    public Step conversationStep(JobRepository jobRepository,
                                 PlatformTransactionManager transactionManager,
                                 ItemReader<ConversationResult> reader,
                                 ConversationProcessor processor,
                                 ConversationWriter writer,
                                 ConversationCacheInitializer listener) {

        return new StepBuilder("conversationStep", jobRepository)
                .<ConversationResult, ConversationResult>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(listener)
                .build();
    }

}
