# Task Manager API

A Spring Boot REST API for tracking and managing tasks, using PostgreSQL for data persistence.

## ⚙️ Prerequisites

Before running the application, ensure you have the following installed:

- **Java 25** or higher
- **Maven**
- **PostgreSQL**

## 🗄️ Database Setup

Create the PostgreSQL database before starting the application.

1. Open PostgreSQL using `psql` or pgAdmin.
2. Run the following SQL command:

   ```sql
   CREATE DATABASE taskmanager_db;
   ```

3. Update `src/main/resources/application-dev.yml` with your local PostgreSQL credentials:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/taskmanager_db
       username: postgres
       password: your_postgres_password
   ```

   Replace `your_postgres_password` with your actual PostgreSQL password. Avoid committing real credentials to version control.

## 🚀 Running the Application

Start the application from your IDE or run it with Maven:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```bat
mvnw.cmd spring-boot:run
```

The development profile is expected to be active, and the server runs on port **4002**.

If your project does not activate the `dev` profile automatically, configure it in your application settings or run:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## 📡 API Endpoints

Base URL:

```text
http://localhost:4002/api/tasks
```

| Method | Endpoint | Description | Request Body |
|---|---|---|---|
| `GET` | `/api/tasks` | Retrieves all tasks | None |
| `POST` | `/api/tasks` | Creates a new task | JSON |
| `PUT` | `/api/tasks/{id}` | Updates an existing task | JSON |
| `DELETE` | `/api/tasks/{id}` | Deletes a task by its ID | None |

### Create a task

**`POST /api/tasks`**

Example JSON body:

```json
{
  "title": "Complete project documentation",
  "description": "Write the Task Manager API README",
  "completed": false
}
```

### Update a task

**`PUT /api/tasks/{id}`**

Replace `{id}` with the ID of the task you want to update.

Example JSON body:

```json
{
  "title": "Complete project documentation",
  "description": "Finalize the Task Manager API README",
  "completed": true
}
```

## 📝 Notes

- Send JSON request bodies with the `Content-Type: application/json` header for `POST` and `PUT` requests.
- The API examples assume the task fields are `title`, `description`, and `completed`.
- Ensure PostgreSQL is running and the database credentials match your local configuration.
