# Task Manager API

A Spring Boot REST API for tracking and managing tasks, utilizing PostgreSQL for robust data persistence.

## ⚙️ Prerequisites

Before running the application, ensure the following are installed:

- **Java 25** or higher
- **Maven**
- **PostgreSQL**

## 🗄️ Database Setup

Create the database before starting the application to prevent a database connection error.

1. Open your PostgreSQL environment using `psql` or pgAdmin.
2. Run the following SQL command:

   ```sql
   CREATE DATABASE taskmanager_db;
   ```

3. Verify that `src/main/resources/application-dev.yml` contains the correct local database credentials:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/taskmanager_db
       username: postgres
       password: your_postgres_password
   ```

   Replace `your_postgres_password` with your actual PostgreSQL password. Do not commit real credentials to version control.

## 🚀 Running the Application

Start the server using your IDE or Maven. The instructions below assume the `dev` profile is active and the application is configured to run on **port 4002**.

Run with Maven:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```bat
mvnw.cmd spring-boot:run
```

If the `dev` profile is not activated automatically, run:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## 📡 API Endpoints

All task operations use the following base URL:

```text
http://localhost:4002/api/tasks
```

| Method | Endpoint | Description | Expected JSON Body |
|---|---|---|---|
| `GET` | `/api/tasks` | Retrieves all tasks | None |
| `POST` | `/api/tasks` | Creates a new task | `{"title": "...", "description": "...", "completed": false}` |
| `PUT` | `/api/tasks/{id}` | Updates an existing task | `{"title": "...", "description": "...", "completed": true}` |
| `DELETE` | `/api/tasks/{id}` | Deletes a task by its ID | None |

## 🧪 Example Requests

### Create a task

**`POST /api/tasks`**

```json
{
  "title": "Complete documentation",
  "description": "Write the Task Manager API README",
  "completed": false
}
```

### Update a task

**`PUT /api/tasks/{id}`**

Replace `{id}` with the ID of the task you want to update.

```json
{
  "title": "Complete documentation",
  "description": "Finalize the Task Manager API README",
  "completed": true
}
```

## 📝 Notes

- Ensure PostgreSQL is running before launching the application.
- Use the `Content-Type: application/json` header for requests with JSON bodies.
- The API examples assume task fields named `title`, `description`, and `completed`.
