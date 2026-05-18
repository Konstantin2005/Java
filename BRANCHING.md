# Branching Workflow

This repository uses a simple workflow:

- `main` holds stable, releasable code
- `develop` is the integration branch for upcoming work
- `feature/*` branches are used for individual tasks

## Rules

- Create new work from `develop`
- Keep feature branches focused on one task
- Merge completed features back into `develop`
- Merge `develop` into `main` when the code is ready for release

## Suggested Branch Names

- `feature/add-meal-search`
- `feature/recipe-validation`
- `feature/export-planner`

## Quick Start

```powershell
git checkout develop
git checkout -b feature/my-task
```
