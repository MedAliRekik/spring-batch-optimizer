### Profils & Ports
| Profil | Port | Base de données | Particularités |
|--------|------|-----------------|----------------|
| par défaut | 8080 | H2 en mémoire (`jdbc:h2:mem:testdb`) | Tables Spring Batch chargées via `sql/schema-all.sql` |

### Variables d’environnement
Aucune variable requise. Toutes les valeurs sont codées en dur dans `application.properties`.  

### Commandes Maven
| Usage            | Commande                           |
|------------------|------------------------------------|
| Lancer le batch  | `mvn clean spring-boot:run`        |
| Mode offline (*) | `mvn -o spring-boot:run`           |
(*) nécessite les dépendances déjà en cache Maven.

### URLs utiles
- Health : `http://localhost:8080/actuator/health`  
- H2 console : `http://localhost:8080/h2-console` (user `sa`, pas de mot de passe)  
- Fichiers exportés : `data/statistic/`
