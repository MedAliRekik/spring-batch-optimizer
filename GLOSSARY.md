| Terme                     | Définition |
|---------------------------|------------|
| **Conversation**         | Échange (call, email…) à exporter |
| **CTA**                  | Identifiant unique de conversation (`ctaNo`) |
| **mediaType**            | Type de média (`call`, `email`, `unknown`…) |
| **ItemReader**           | Composant Spring Batch qui lit les données source |
| **ItemProcessor**        | Composant qui transforme/enrichit les items |
| **ItemWriter**           | Composant qui persiste ou écrit les items |
| **StepExecutionListener**| Hook exécuté avant/après un step (init du cache) |
| **Chunk**                | Unité de traitement par lots (ici, 10 items) |
| **Job/Step**             | Concepts Spring Batch : job composé de steps |
| **H2**                   | Base de données relationnelle embarquée en mémoire |
