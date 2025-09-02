### Diagramme
```
+-----------------------------+
| BatchLauncher (CMD runner)  |
+-----------------------------+
             |
             v
+-----------------------------------------------+
| Job conversationJob                           |
|  Step conversationStep (chunk=10)             |
|                                               |
|  +---------+   +-----------+   +------------+ |
|  | Reader  |-->| Processor |-->|  Writer    | |
|  +---------+   +-----------+   +------------+ |
|       ^            ^              |           |
|       |            |              v           |
| ConversationService|     MediaTypeService     |
|       \____________|_______CacheInitializer___|
+-----------------------------------------------+
                        |
                        v
                 Fichiers /data/statistic
```

### Modules / Packages
- **config** – `BatchConfig`, `BatchLauncher` (déclare job/step, lance le job)  
- **reader** – `ConversationReader` (liste en mémoire)  
- **processor** – `ConversationProcessor` (enrichit via cache)  
- **writer** – `ConversationWriter` (fichiers `.txt` par mediaType)  
- **listener** – `ConversationCacheInitializer` (précharge & nettoie le cache)  
- **service** – Interfaces `ConversationService`, `MediaTypeService` + implémentations factices  
- **model** – `ConversationResult` (POJO de travail)

### Composants transverses
- **Sécurité** : aucune  
- **Exceptions** : aucune gestion centralisée  
- **Swagger / OpenAPI** : absent  
- **Actuator** : exposé pour healthcheck

### Flux critique : export de conversations
1. **BatchLauncher** démarre `conversationJob` avec un timestamp unique.  
2. **beforeStep** du listener charge les mediaTypes dans un cache en mémoire.  
3. **Reader** fournit une liste de conversations simulées.  
4. **Processor** enrichit chaque conversation avec son `mediaType` depuis le cache.  
5. **Writer** groupe par `mediaType` et écrit un fichier horodaté par groupe.  
6. **afterStep** vide le cache et le job s’achève.  
