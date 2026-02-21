# Starter Examen - API Vacaciones

Proyecto base para examen práctico de Programación Visual.

## Objetivo
Completar los métodos marcados con `TODO EXAMEN` para dar mantenimiento a una sola entidad: `Vacacion`.

## Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Docker Compose

## Endpoints requeridos
- `POST /api/vacaciones`
- `GET /api/vacaciones`
- `GET /api/vacaciones/{id}`
- `PUT /api/vacaciones/{id}`
- `PUT /api/vacaciones/{id}/inactivar`

## Reglas funcionales
- `nombreEmpleado`, `fechaInicio`, `fechaFin` obligatorios.
- `fechaFin` no puede ser menor a `fechaInicio`.
- La inactivación debe ser lógica (`estado = I`), no física.

## Ejecución rápida
1. Levantar contenedores:
   - `docker compose up --build`
2. API disponible en:
   - `http://localhost:8088`
3. Base de datos:
   - host: `localhost`
   - puerto: `3307`
   - bd: `vacaciones_db`
   - usuario: `vacaciones_user`
   - clave: `vacaciones_pass`

La tabla `vacaciones` se crea automáticamente con el script `db/init/01_schema.sql`.

## Nota importante
No modificar estructura de carpetas ni firmas de métodos. Completar únicamente la lógica faltante de los `TODO EXAMEN`.
