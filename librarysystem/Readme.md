# Library Management System API

A Spring Boot REST API for managing library operations, including book inventory, authors, categories, staff, students, and secure borrowing transactions.

## ⚙️ Prerequisites

Before running the application, ensure you have the following installed:

- **Java 25** or higher
- **Maven**
- **PostgreSQL**

## 🗄️ Database Setup

Create the PostgreSQL database before starting the server.

1. Open PostgreSQL using `psql` or pgAdmin.
2. Run the following SQL command:

   ```sql
   CREATE DATABASE library_db;
   ```

3. Update `src/main/resources/application.yml` with your local PostgreSQL credentials:

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/library_db
       username: postgres
       password: your_postgres_password
   ```

   Replace `your_postgres_password` with your actual PostgreSQL password. Do not commit real credentials to version control.

Hibernate is configured with `ddl-auto: update`, so it can create or update tables automatically when the application starts successfully. This does not create the PostgreSQL database itself.

## 🚀 Running the Application

Run the application using Maven:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```bat
mvnw.cmd spring-boot:run
```

The server runs on port **8080** by default.

## 📡 API Navigation & Endpoints

The API uses JWT (JSON Web Token) authentication. Logging in and reading books are public. All other operations require a valid Bearer token in the `Authorization` header.

### Authentication Endpoints (Public)

| Method | Endpoint | Description | Body / Params |
|---|---|---|---|
| `POST` | `/api/auth/login` | Authenticates a user and returns a JWT | `{"email": "...", "password": "..."}` |

### Book Management (Public Read / Protected Write)

| Method | Endpoint | Description | Body / Params |
|---|---|---|---|
| `GET` | `/api/books` | Retrieves all books | None |
| `POST` | `/api/books` | Adds a book to the library | `title`, `description`, `publicationYear`, `isbn`, `totalCopies`, `authorId`, `categoryId` |

### Author & Category Management (Protected)

| Method | Endpoint | Description | Body / Params |
|---|---|---|---|
| `GET` | `/api/authors` | Retrieves all authors | None |
| `POST` | `/api/authors` | Adds a new author | `name`, `bio` |
| `GET` | `/api/categories` | Retrieves all categories | None |
| `POST` | `/api/categories` | Adds a new category | `name`, `description` |

### Transaction Management (Protected)

| Method | Endpoint | Description | Body / Params |
|---|---|---|---|
| `POST` | `/api/transactions/borrow` | Issues a book to a student | Query parameters: `studentId`, `bookId`, `staffId` |
| `PUT` | `/api/transactions/return/{recordId}` | Records the return of a borrowed book | Path variable: `recordId` |

## 🔐 Authentication Guide

To access protected endpoints:

1. Send a `POST` request to `/api/auth/login` with valid credentials.
2. Copy the `token` value from the JSON response.
3. For subsequent protected requests, add an `Authorization` header.
4. Set its value to `Bearer <your_token>`.

Example header:

```http
Authorization: Bearer <your_token>
```

## 📝 Notes

- Send `Content-Type: application/json` when submitting JSON request bodies.
- The endpoint details above reflect the API contract provided; confirm exact request fields and parameter placement against your controller implementations.
- Keep database credentials and JWT signing secrets out of public repositories.
