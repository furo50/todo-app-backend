# 🚀 Todo App - Backend

REST API Backend für die Todo-Anwendung, gebaut mit Spring Boot, PostgreSQL und JPA/Hibernate. Bietet vollständige CRUD-Operationen für Todo-Management.

## 🛠️ Features

- ✅ **RESTful API**: Saubere REST-Endpoints für alle CRUD-Operationen
- 🗄️ **PostgreSQL Integration**: Persistente Datenspeicherung
- 🔄 **JPA/Hibernate**: Automatische Datenbank-Mapping ohne SQL
- 🌐 **CORS-Support**: Konfiguriert für Frontend-Kommunikation
- 📊 **Auto-Schema Generation**: Hibernate erstellt Tabellen automatisch
- ⚡ **Spring Boot**: Schnelle Entwicklung mit Convention over Configuration

## 🛠️ Tech Stack

- **Java 21** - Programmiersprache
- **Spring Boot 3.5.10** - Framework
- **Spring Data JPA** - Datenbank-Abstraction
- **Hibernate** - ORM (Object-Relational Mapping)
- **PostgreSQL 18** - Datenbank
- **Maven** - Build Tool & Dependency Management

## 📋 Voraussetzungen

- Java 21 (JDK)
- PostgreSQL (installiert und laufend)
- Maven (meist in IDE integriert)
- IntelliJ IDEA oder andere Java IDE (optional)

## 🗄️ Datenbank Setup

1. **PostgreSQL installieren** (falls noch nicht geschehen)

2. **Datenbank erstellen:**
```sql
CREATE DATABASE todo_db;
```

3. **Verbindungsdaten konfigurieren** in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=postgres
spring.datasource.password=DEIN_PASSWORT
```

## 🔧 Installation

1. **Repository klonen:**
```bash
git clone https://github.com/furo50/todo-app-backend.git
cd todo-app-backend
```

2. **Dependencies installieren:**
```bash
mvn clean install
```

3. **Application starten:**
```bash
mvn spring-boot:run
```

**Oder in IntelliJ:**
- Öffne `TodoBackendApplication.java`
- Klicke auf den grünen Play-Button ▶️

4. **Backend läuft auf:**
```
http://localhost:8080
```

## 📁 Projekt-Struktur
```
src/main/java/com/mehmet/furkan/ozer/todo_backend/
├── controller/
│   └── TodoController.java      # REST Endpoints
├── model/
│   └── Todo.java                # Entity/Datenmodell
├── repository/
│   └── TodoRepository.java      # JPA Repository
└── TodoBackendApplication.java  # Main Class
```

## 🌐 API Endpoints

| Methode | Endpoint | Beschreibung |
|---------|----------|--------------|
| `GET` | `/api/todos` | Alle Todos abrufen |
| `POST` | `/api/todos` | Neues Todo erstellen |
| `PUT` | `/api/todos/{id}` | Todo aktualisieren |
| `DELETE` | `/api/todos/{id}` | Todo löschen |

### Beispiel Requests:

**GET alle Todos:**
```bash
curl http://localhost:8080/api/todos
```

**POST neues Todo:**
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"text":"Mein Todo","completed":false}'
```

**PUT Todo aktualisieren:**
```bash
curl -X PUT http://localhost:8080/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{"text":"Updated","completed":true}'
```

**DELETE Todo:**
```bash
curl -X DELETE http://localhost:8080/api/todos/1
```

## 🗃️ Datenbank Schema

Hibernate erstellt automatisch folgende Tabelle:
```sql
CREATE TABLE todos (
    id BIGSERIAL PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    completed BOOLEAN NOT NULL,
    created_at TIMESTAMP
);
```

## 🔗 Frontend Integration

Dieses Backend ist designed für das Frontend:
- **Repository:** [todo-app-react](https://github.com/furo50/todo-app-react)
- **CORS:** Konfiguriert für `http://localhost:5173`

## 🧪 Testing

**Mit IntelliJ HTTP Client:**
- Nutze `src/main/test-api.http` für schnelle Tests

**Mit Postman/Insomnia:**
- Importiere die Endpoints und teste manuell

## 🎯 Zukünftige Verbesserungen

- [ ] User Authentication (Spring Security)
- [ ] Input Validation
- [ ] Unit & Integration Tests
- [ ] Docker Support
- [ ] API Documentation (Swagger/OpenAPI)
- [ ] Pagination für große Datenmengen

## 👤 Autor

**Mehmet Furkan Özer**
- GitHub: [@furo50](https://github.com/furo50)

## 📄 Lizenz

Dieses Projekt steht unter der MIT Lizenz.