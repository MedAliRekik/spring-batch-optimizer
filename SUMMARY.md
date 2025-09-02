### Pitch produit  
BatchOptimizer est un micro‑batch Spring Boot qui lit une liste de conversations, enrichit chaque élément via un cache de mediaTypes puis exporte un fichier texte par type de conversation. L’application est autonome, sans dépendance externe, et facilement extensible vers des sources réelles.

### Fonctionnalités clés  
- Job/step Spring Batch 5 configurables  
- Cache `mediaType` chargé avant l’exécution du step  
- Export de fichiers horodatés par type (`call`, `email`…)  
- Groupement et écriture par type de média  
- Tables Spring Batch initialisées dans H2  
- Lancement automatique du job au démarrage  
- Console H2 intégrée pour inspection  
- Actuator pour monitoring basique

### Stack & URLs  
- **Java 17**, **Spring Boot 3.5.3**, **Spring Batch 5.2.x**  
- H2 in‑memory, Maven, Lombok, Micrometer (Brave)  
- Healthcheck : `http://localhost:8080/actuator/health`  
- Console H2 : `http://localhost:8080/h2-console`  

### Lancer localement  
1. `cd batchoptimizer`  
2. `mvn clean spring-boot:run`  
3. Les fichiers sont générés dans `data/statistic/`  
