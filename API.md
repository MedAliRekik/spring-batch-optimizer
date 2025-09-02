L’application n’expose pas d’API métier. Seuls les endpoints techniques sont disponibles.

| Méthode | Endpoint                   | Rôle                       | DTO In | DTO Out              | Codes |
|---------|---------------------------|----------------------------|--------|----------------------|-------|
| GET     | `/actuator/health`        | Statut de l’application    | –      | `{"status":"UP"}`    | 200   |
| GET     | `/h2-console`             | Console H2 en mémoire      | –      | UI HTML              | 200   |

Exemple :
```bash
curl http://localhost:8080/actuator/health
```
Swagger UI : non configuré.
