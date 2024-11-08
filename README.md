To-do Application, per the requirements of SportsUnity backend test

To launch standalone application windows (jdk 17):
```
java -jar {path to executable}.jar --server.port=8080
```


# Relationship table
![Blank Diagram - Page 2](https://github.com/user-attachments/assets/7650045b-81c3-4bf7-be53-bf9c1ba2f1c1)


# API Endpoints

## User Controller

| Entity | Endpoint                | HTTP Method | Description                   | Request JSON Example                                                 | Response JSON Example                                                |
|--------|-------------------------|-------------|-------------------------------|-----------------------------------------------------------------------|----------------------------------------------------------------------|
| User   | `/api/users`            | POST        | Create a new user             | `{ "name": "John Doe", "role": "USER", "company": { "id": 1, "name": "Example Company" } }` | `{ "id": 1, "name": "John Doe", "role": "USER", "company": { "id": 1, "name": "Example Company" } }` |
| User   | `/api/users`            | GET         | Retrieve all users            | N/A                                                                   | `[ { "id": 1, "name": "John Doe", "role": "USER", "company": { "id": 1, "name": "Example Company" } } ]` |
| User   | `/api/users/{id}`       | GET         | Retrieve user by ID           | N/A                                                                   | `{ "id": 1, "name": "John Doe", "role": "USER", "company": { "id": 1, "name": "Example Company" } }` |
| User   | `/api/users/{id}`       | PUT         | Update an existing user       | `{ "name": "John Doe Updated", "role": "ADMIN", "company": { "id": 1, "name": "Example Company" } }` | `{ "id": 1, "name": "John Doe Updated", "role": "ADMIN", "company": { "id": 1, "name": "Example Company" } }` |
| User   | `/api/users/{id}`       | DELETE      | Delete a user by ID           | N/A                                                                   | N/A (204 No Content on Success)                                      |

## Task Controller

| Entity | Endpoint                | HTTP Method | Description                   | Request JSON Example                                                 | Response JSON Example                                                |
|--------|-------------------------|-------------|-------------------------------|-----------------------------------------------------------------------|----------------------------------------------------------------------|
| Task   | `/api/tasks`            | POST        | Create a new task             | `{ "description": "New Task", "status": "PENDING" }`                 | `{ "id": 1, "description": "New Task", "status": "PENDING", "createdAt": "2024-10-01T12:00:00Z" }` |
| Task   | `/api/tasks`            | GET         | Retrieve all tasks            | N/A                                                                   | `[ { "id": 1, "description": "New Task", "status": "PENDING", "createdAt": "2024-10-01T12:00:00Z" } ]` |
| Task   | `/api/tasks/{id}`       | GET         | Retrieve task by ID           | N/A                                                                   | `{ "id": 1, "description": "New Task", "status": "PENDING", "createdAt": "2024-10-01T12:00:00Z" }` |
| Task   | `/api/tasks/{id}`       | PUT         | Update a task                 | `{ "description": "Updated Task", "status": "COMPLETED" }`           | `{ "id": 1, "description": "Updated Task", "status": "COMPLETED", "createdAt": "2024-10-01T12:00:00Z" }` |
| Task   | `/api/tasks/{id}`       | DELETE      | Delete a task by ID           | N/A                                                                   | N/A (204 No Content on Success)                                      |

## Company Controller

| Entity  | Endpoint                | HTTP Method | Description                   | Request JSON Example                                                 | Response JSON Example                                                |
|---------|-------------------------|-------------|-------------------------------|-----------------------------------------------------------------------|----------------------------------------------------------------------|
| Company | `/api/companies`        | POST        | Create a new company          | `{ "name": "Example Company", "location": "New York", "industry": "Tech" }` | `{ "id": 1, "name": "Example Company", "location": "New York", "industry": "Tech" }` |
| Company | `/api/companies`        | GET         | Retrieve all companies        | N/A                                                                   | `[ { "id": 1, "name": "Example Company", "location": "New York", "industry": "Tech" } ]` |
| Company | `/api/companies/{id}`   | GET         | Retrieve company by ID        | N/A                                                                   | `{ "id": 1, "name": "Example Company", "location": "New York", "industry": "Tech" }` |
| Company | `/api/companies/{id}`   | PUT         | Update an existing company    | `{ "name": "Updated Company", "location": "San Francisco", "industry": "Finance" }` | `{ "id": 1, "name": "Updated Company", "location": "San Francisco", "industry": "Finance" }` |
| Company | `/api/companies/{id}`   | DELETE      | Delete a company by ID        | N/A                                                                   | N/A (204 No Content on Success)                                      |
