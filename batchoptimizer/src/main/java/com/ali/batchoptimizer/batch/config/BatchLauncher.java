package com.ali.batchoptimizer.batch.config;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * ▶️ Lancement automatique du batch au démarrage de l'application.
 *
 * Le JobParameters inclut un timestamp pour permettre plusieurs exécutions.
 */
@Component
public class BatchLauncher implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job conversationJob;

    public BatchLauncher(JobLauncher jobLauncher, Job conversationJob) {
        this.jobLauncher = jobLauncher;
        this.conversationJob = conversationJob;
    }

    @Override
    public void run(String... args) throws Exception {
        JobExecution execution = jobLauncher.run(
                conversationJob,
                new JobParametersBuilder()
                        .addLong("timestamp", System.currentTimeMillis()) // Permet exécutions multiples
                        .toJobParameters()
        );

        System.out.printf("[RUNNER] Job terminé avec le statut : %s%n", execution.getStatus());
    }
}