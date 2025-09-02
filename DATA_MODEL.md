### Entités
- **ConversationResult**  
  - `ctaNo` : identifiant de conversation (String)  
  - `mediaType` : type de média (`call`, `email`, `unknown`)  
  - `content` : contenu textuel  
- **mediaTypeCache** (en mémoire) : `Map<ctaNo, mediaType>` alimentée par `ConversationCacheInitializer`

### Relations / Contraintes
- Pas de persistance des conversations ; seules les tables `BATCH_*` de Spring Batch sont créées dans H2.  
- Aucune contrainte d’unicité ou index spécifique côté application.  
- Les valeurs de `mediaType` sont libres (pas d’`enum`).

### Règles métier essentielles
- Si un `mediaType` est introuvable, le processor affecte `unknown`.  
- Chaque fichier d’export est horodaté pour éviter l’écrasement.  
