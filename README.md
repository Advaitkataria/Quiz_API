# Quiz Platform API

A production-quality REST API for managing quiz questions built with Spring Boot 3 and MySQL.

---

## Tech Stack

- **Java 17**
- **Spring Boot 3.4.1**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Lombok**
- **Swagger UI (Springdoc OpenAPI)**
- **Maven**

---

## Features

- Full CRUD for quiz questions
- Filter questions by category and difficulty
- Keyword search across question titles
- Pagination — handle large datasets efficiently
- Custom JPQL queries and derived query methods
- Input validation with meaningful error messages
- Global exception handling with proper HTTP status codes
- Interactive Swagger UI documentation

---

## API Endpoints

### Questions

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/questions` | Get all questions (paginated) |
| GET | `/questions/{id}` | Get question by ID |
| GET | `/questions/category/{category}` | Filter by category |
| GET | `/questions/difficulty/{difficulty}` | Filter by difficulty |
| GET | `/questions/filter?category=&difficulty=` | Filter by both |
| GET | `/questions/search?keyword=` | Search by keyword |
| GET | `/questions/paginated?page=0&size=10` | Paginated results |
| POST | `/questions` | Create a new question |
| PUT | `/questions/{id}` | Update a question |
| DELETE | `/questions/{id}` | Delete a question |

---

## Request Body — Create / Update Question

```json
{
    "questionTitle": "What is Spring Boot?",
    "option1": "A Java framework",
    "option2": "A database",
    "option3": "A programming language",
    "option4": "An operating system",
    "correctAnswer": "A Java framework",
    "difficulty": "Easy",
    "category": "Spring"
}
```

**Validation rules:**
- `questionTitle` — required, 10–200 characters
- `option1` to `option4` — required, cannot be blank
- `correctAnswer` — required
- `difficulty` — must be `Easy`, `Medium`, or `Hard`
- `category` — required

---

## Error Responses

All errors return a consistent JSON format:

```json
{
    "status": 404,
    "message": "Question with id 99 not found",
    "timestamp": "2026-04-17T12:00:00"
}
```

| Status | Meaning |
|--------|---------|
| 400 | Validation failed — check your request body |
| 404 | Question not found |
| 500 | Internal server error |

---

## Swagger UI

Run the project and visit:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Setup & Installation

### Prerequisites

- Java 17+
- MySQL 8+
- Maven

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/yourusername/quiz-platform-api.git
cd quiz-platform-api
```

**2. Create MySQL database**
```sql
CREATE DATABASE quizdb;
```

**3. Configure application.properties**

Open `src/main/resources/application.properties` and update:

```properties
spring.application.name=quiz-api
spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD_HERE
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

**4. Run the project**
```bash
mvn spring-boot:run
```

Tables are created automatically on first run.

---

## Project Structure

```
src/main/java/com/example/practice/
├── controller/
│   └── QuestionController.java
├── model/
│   └── Question.java
├── repository/
│   └── QuestionRepository.java
├── service/
│   └── QuestionService.java
├── exception/
│   ├── QuestionNotFoundException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
└── config/
    └── SwaggerConfig.java
```

---

## Architecture

Clean MVC architecture with strict layer separation:

- **Controller** — handles HTTP requests, delegates to service
- **Service** — contains all business logic
- **Repository** — data access layer, talks to MySQL
- **Exception** — centralized error handling

---

## Key Technical Decisions

- **Constructor injection** over field injection for testability
- **`orElseThrow`** instead of `orElse(null)` for proper error handling
- **`@ControllerAdvice`** for global exception handling
- **Derived query methods** and **JPQL** for custom queries
- **`PageRequest`** for pagination — never return unbounded result sets
