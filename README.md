# Task Logger

## Description

A Spring backend enabling the submission and storage of caseworker tasks.

## API


### Base URI

```http://localhost:8080/```

The base endpoint for this service.

### Endpoints

#### POST ```/task```
Adds a new task to the database
```json
[
    {
        "title": "Email",
        "description": "Catch yp on emails.",
        "Status": "CREATED",
        "dueDateTime": "2025-12-01T17:00:00"
    }
]
```
## Submission Details

Status must be submitted with one of three values.

| Status      |
|-------------|
| CREATED     |
| IN_PROGRESS |
| COMPLETED   |

## Documentation

The codebase is split into a number of packages.

1. Repository
2. Model
    - Includes a DTO model data transfer to/from the database
3. Service
    - Business logic drawing on the repository layer and supplying the controller
4. Controller
    - Endpoint definition with custom exception handling to provide users with detailed responses
