### Différences README ↔ Code
- **Nom des fichiers d’export** : README montre `Export_CALL_YYYYMMDD_HHmm.txt` alors que le code ajoute les secondes (`yyyyMMdd_HHmmss`).

:::task-stub{title="Aligner l’exemple de nommage des fichiers"}
Mettre à jour `README.md` ou le formateur de date dans `ConversationWriter` pour utiliser un format cohérent (`yyyyMMdd_HHmm` ou `yyyyMMdd_HHmmss`).
:::

### Warnings techniques
- **Lecture factice** : le `ConversationReader` et les services `Fake*` sont codés en dur, ce qui contredit la “récupération dynamique” annoncée.

:::task-stub{title="Implémenter un Reader et des services réels"}
Remplacer `ConversationReader` par un `ItemReader` connecté à la source de données réelle et fournir des implémentations concrètes de `ConversationService` et `MediaTypeService`.
:::

- **Absence de tests** : seul un test “contextLoads” est présent.

:::task-stub{title="Ajouter des tests d’intégration du batch"}
Créer un test Spring Batch qui exécute `conversationJob` et vérifie la création des fichiers dans `data/statistic/`.
:::

- **Propriété JPA inutile** : `spring.jpa.properties.hibernate.format_sql=true` est définie alors qu’aucune entité JPA n’est utilisée.

:::task-stub{title="Nettoyer la configuration JPA"}
Supprimer `spring.jpa.properties.hibernate.format_sql` de `application.properties` pour éviter toute confusion.
:::

### TODO priorisé
- **P1** : Ajout de tests d’intégration du batch  
- **P2** : Alignement README/code sur le format de nommage des fichiers  
- **P3** : Nettoyage de la configuration JPA et remplacement des services factices  
