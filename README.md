# 📦 BatchOptimizer — Export Conversations

> Un batch Spring Boot fiable et maintenable pour traiter, formater et exporter automatiquement des conversations dans des fichiers `.txt`, organisés par type (`call`, `email`, etc.).

---

## 🎯 Objectif du projet

Ce projet démontre la mise en place d’un **batch Java Spring Batch 5+** permettant de :

- Récupérer dynamiquement des **conversations**.
- Les **traiter, filtrer ou enrichir** si besoin.
- Générer un ou plusieurs **fichiers `.txt` horodatés**, un par type de conversation (`call`, `email`, ...).
- **Sauvegarder les fichiers dans un répertoire structuré** : `data/statistic/`.

> 💡 Ce projet est conçu pour être **robuste**, **réutilisable** et **prêt pour la production**.

---

## 🏗️ Architecture technique

```
BatchoptimizerApplication
│
├── config/
│   └── BatchConfig.java           → Déclaration du job et du step principal
│
├── model/
│   └── ConversationResult.java    → Structure d’une conversation à exporter
│
├── processor/
│   └── ConversationProcessor.java → (Facultatif) Traitement des données
│
├── writer/
│   └── ConversationWriter.java    → Génération de fichiers `.txt` par type
│
├── listener/
│   └── ConversationCacheInitializer.java → Initialisation du cache en début de batch
│
└── resources/
    └── application.properties     → Configuration H2, Spring Batch
```

---

## ⚙️ Fonctionnement du batch

1. 🔄 Initialisation des données (conversations).
2. 🧠 Traitement optionnel des données.
3. 📂 Génération des fichiers `.txt` dans `data/statistic/`.
4. 🧾 Chaque fichier est nommé dynamiquement :  
   Exemple → `Export_CALL_20250624_1805.txt`

---

## 📁 Exemple de structure de sortie

```
data/statistic/
├── Export_CALL_20250624_1805.txt
└── Export_EMAIL_20250624_1805.txt
```

📄 Exemple de contenu d’un fichier :

```
CTA: CTA001 | Type: call | Contenu: Message A
CTA: CTA002 | Type: call | Contenu: Message B
```

---

## 🧪 Technologies utilisées

| Outil / Framework    | Version      |
|----------------------|--------------|
| Java                 | 17           |
| Spring Boot          | 3.5.x        |
| Spring Batch         | 5.2.x        |
| H2 Database          | En mémoire   |
| Maven                | ✅            |

---

## 🚀 Lancer le batch

```bash
# Depuis la racine du projet
mvn clean spring-boot:run
```

👉 Tu peux accéder à la console H2 (facultatif) :
```
http://localhost:8080/h2-console
```
(user : `sa`, pas de mot de passe)

---

## 🧬 Schéma du traitement

```mermaid
flowchart TD
    A[Démarrage du Batch] --> B[Lecture des conversations]
    B --> C[Groupement par type (mediaType)]
    C --> D1[Génération fichier CALL]
    C --> D2[Génération fichier EMAIL]
    D1 --> E[Fin du batch ✅]
    D2 --> E
```

---

## 🧘 Pourquoi ce batch est fiable

- 📦 **Architecture claire** basée sur les bonnes pratiques Spring Batch.
- ♻️ **Extensible** : nouveaux types de conversations ou formats de sortie faciles à ajouter.
- 💾 **Séparation des responsabilités** : lecture, traitement, écriture.
- 🔒 **Nommage dynamique et horodaté** : évite les écrasements.
- 🧪 **Base H2 embarquée** : 0 dépendance externe.

---

## 📬 Contact

Développé par **Ali Rekik**   
📧 med.ali.rekik@gmail.com  
📍 Lille, France
