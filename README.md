# Task Tracker API

A RESTful task management API built with Spring Boot 4 and Spring Framework 7.

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.5**
- **Spring Data JPA** + Hibernate 7
- **H2** in-memory database
- **MapStruct** for DTO mapping
- **Lombok** for boilerplate reduction
- **Bean Validation** for request validation
- **JUnit 5** + **Mockito** for testing

## Features

- Full CRUD for tasks
- DTO layer separating API contracts from domain model
- Global exception handling with meaningful HTTP responses
- Input validation with field-level error messages
- Three layers of automated tests: repository, service, and controller

## Running the Application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

The H2 console is available at `http://localhost:8080/h2-console`:
- JDBC URL: `jdbc:h2:mem:taskdb`
- Username: `sa`
- Password: *(leave blank)*

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/tasks` | Get all tasks |
| GET | `/tasks/{id}` | Get task by ID |
| POST | `/tasks` | Create a new task |
| PUT | `/tasks/{id}` | Update an existing task |
| DELETE | `/tasks/{id}` | Delete a task |
| GET | `/health` | Health check |

### Request Body (POST / PUT)

```json
{
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": false
}
```

Both `title` and `description` are required and cannot be blank.

### Response Body

```json
{
  "id": 1,
  "title": "Buy groceries",
  "description": "Milk, eggs, bread",
  "completed": false
}
```

## Error Responses

**404 Not Found** — task ID does not exist:
```
Task with 99 not found
```

**400 Bad Request** — validation failure:
```json
{
  "title": "must not be blank"
}
```

## Running Tests

```bash
./mvnw test
```

The test suite covers:
- **Repository layer** — integration test against H2
- **Service layer** — unit tests with Mockito (no Spring context)
- **Controller layer** — full context tests with MockMvc

## Project Structure

```
src/main/java/com/shadowSin/task_tracker/
├── controller/       # REST controllers
├── service/          # Business logic
├── repository/       # Spring Data JPA interfaces
├── model/            # JPA entities
├── dto/              # Request and response records
├── mapper/           # MapStruct mappers
└── exception/        # Custom exceptions and global handler
```
