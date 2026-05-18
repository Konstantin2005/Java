# Meal Planner

Spring Boot application for meal planning.

## Features

- CRUD for recipes over REST
- meal plan creation by date
- in-memory storage for a fast start
- input validation
- centralized error handling
- seed data on startup

## Core Entities

- `Recipe`
- `Ingredient`
- `MealPlanEntry`
- `MealType`

## API

- `POST /api/recipes`
- `GET /api/recipes`
- `GET /api/recipes/{id}`
- `POST /api/meal-plans`
- `GET /api/meal-plans`
- Swagger UI: `/swagger-ui.html`
- OpenAPI JSON: `/v3/api-docs`

## Run

```bash
mvn spring-boot:run
```

## Tests

```bash
mvn test
```
