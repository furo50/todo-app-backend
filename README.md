# Todo App - Spring Boot Backend

REST API für die Todo-App. Spring Boot 3 mit PostgreSQL und JPA/Hibernate.

## Features

- RESTful CRUD-Operationen
- PostgreSQL Integration
- JPA/Hibernate ORM
- CORS für Frontend
- Auto Schema Generation

## Tech Stack

- Java 21
- Spring Boot 3.5.10
- Spring Data JPA
- PostgreSQL 18
- Maven

## Voraussetzungen

- Java 21
- PostgreSQL (laufend)
- Maven (oder IDE)

## Setup

**Datenbank erstellen:**
```sql
CREATE DATABASE todo_db;
```

**application.properties konfigurieren:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_db
spring.datasource.username=postgres
spring.datasource.password=DEIN_PASSWORD
```

**Starten:**
```bash
git clone https://github.com/furo50/todo-app-backend.git
cd todo-app-backend
mvn clean install
mvn spring-boot:run
```

Backend läuft auf `http://localhost:8080`

## Projekt-Struktur
```
src/main/java/.../todo_backend/
├── controller/
│   └── TodoController.java      # REST Endpoints
├── model/
│   └── Todo.java                # Entity
├── repository/
│   └── TodoRepository.java      # JPA Repository
└── TodoBackendApplication.java
```

## API Endpoints

| Methode | Endpoint | Beschreibung |
|---------|----------|--------------|
| GET | `/api/todos` | Alle Todos |
| POST | `/api/todos` | Todo erstellen |
| PUT | `/api/todos/{id}` | Todo updaten |
| DELETE | `/api/todos/{id}` | Todo löschen |

**Beispiel Request:**
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"text":"Neue Aufgabe","completed":false}'
```

## Datenbank Schema

Hibernate erstellt automatisch:
```sql
todos (
  id BIGSERIAL PRIMARY KEY,
  text VARCHAR(255),
  completed BOOLEAN,
  created_at TIMESTAMP
)
```

## Frontend

React-Frontend: https://github.com/furo50/todo-app-react  
CORS konfiguriert für `localhost:5173`

## Geplante Erweiterungen

- [ ] Input Validation
- [ ] Error Handling
- [ ] Unit Tests
- [ ] Authentication
- [ ] Docker Support

---

**Mehmet Furkan Özer**  
GitHub: [@furo50](https://github.com/furo50)