# Meal Planner

Spring Boot приложение для планирования питания.

## Возможности

- CRUD для рецептов через REST
- создание планов питания по датам
- in-memory хранилище для быстрого старта
- валидация входных данных
- централизованная обработка ошибок
- seed-данные при запуске

## Основные сущности

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

## Запуск

```bash
mvn spring-boot:run
```

## Тесты

```bash
mvn test
```
