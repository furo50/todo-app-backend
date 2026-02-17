# Todo App - Spring Boot Backend

REST API für die Todo-App. Spring Boot 3 mit PostgreSQL, JPA/Hibernate und JWT Authentication.

## Features

- RESTful CRUD-Operationen für Todos
- JWT-basierte Authentication
- User Registration & Login
- BCrypt Password Hashing
- User-Isolation (jeder sieht nur seine eigenen Todos)
- PostgreSQL Integration
- JPA/Hibernate ORM
- Spring Security
- CORS für Frontend

## Tech Stack

- Java 21
- Spring Boot 3.5.10
- Spring Security
- JWT (jjwt 0.12.3)
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
│   ├── AuthController.java      # Login & Signup Endpoints
│   └── TodoController.java      # Todo CRUD Endpoints
├── dto/
│   ├── AuthResponse.java        # Token Response
│   ├── LoginRequest.java        # Login Request Body
│   └── SignupRequest.java       # Signup Request Body
├── model/
│   ├── Todo.java                # Todo Entity
│   └── User.java                # User Entity
├── repository/
│   ├── TodoRepository.java      # Todo JPA Repository
│   └── UserRepository.java      # User JPA Repository
├── security/
│   ├── config/
│   │   └── SecurityConfig.java          # Spring Security Config
│   ├── jwt/
│   │   ├── JwtAuthenticationFilter.java # JWT Request Filter
│   │   └── JwtTokenProvider.java        # Token Generation & Validation
│   └── service/
│       └── CustomUserDetailsService.java # User laden für Spring Security
└── TodoBackendApplication.java
```

## API Endpoints

### Auth (öffentlich)

| Methode | Endpoint | Beschreibung |
|---------|----------|--------------|
| POST | `/api/auth/signup` | User registrieren |
| POST | `/api/auth/login` | User einloggen, Token erhalten |

### Todos (JWT erforderlich)

| Methode | Endpoint | Beschreibung |
|---------|----------|--------------|
| GET | `/api/todos` | Alle Todos des Users |
| POST | `/api/todos` | Todo erstellen |
| PUT | `/api/todos/{id}` | Todo updaten |
| DELETE | `/api/todos/{id}` | Todo löschen |

### Beispiel Flow:

**1. Registrieren:**
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"username":"max","email":"max@example.com","password":"password123"}'
```

**2. Einloggen:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"max","password":"password123"}'
```

**3. Todo erstellen (mit Token):**
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer DEIN_TOKEN" \
  -d '{"text":"Neue Aufgabe","completed":false}'
```

## Datenbank Schema

Hibernate erstellt automatisch:
```sql
users (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(255) UNIQUE,
  email VARCHAR(255) UNIQUE,
  password VARCHAR(255),
  created_at TIMESTAMP
)

todos (
  id BIGSERIAL PRIMARY KEY,
  text VARCHAR(255),
  completed BOOLEAN,
  created_at TIMESTAMP,
  user_id BIGINT REFERENCES users(id)
)
```

## Frontend

React-Frontend: https://github.com/furo50/todo-app-react  
CORS konfiguriert für `localhost:5173`

## Geplante Erweiterungen

- [ ] Input Validation
- [ ] Error Handling verbessern
- [ ] Unit Tests
- [ ] Docker Support

---

**Mehmet Furkan Özer**  
GitHub: [@furo50](https://github.com/furo50)