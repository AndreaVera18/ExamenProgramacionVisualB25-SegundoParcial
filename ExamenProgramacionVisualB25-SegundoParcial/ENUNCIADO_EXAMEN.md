# EXAMEN PRÁCTICO - PROGRAMACIÓN VISUAL

## Tema
Desarrollo de API REST para mantenimiento de vacaciones de empleados (entidad única).

## Contexto
Se entrega un proyecto Spring Boot parcialmente implementado. Debes completar la lógica marcada con `TODO EXAMEN` para que la API funcione correctamente sobre una base de datos MySQL dockerizada.

## Restricciones
- Solo se maneja una entidad: `Vacacion`.
- No crear nuevas entidades.
- No cambiar la estructura general del proyecto.
- No eliminar ni renombrar métodos existentes.

## Funcionalidades a implementar
1. Registrar vacaciones de un empleado.
2. Listar todas las vacaciones.
3. Consultar una vacación por id.
4. Editar una vacación existente.
5. Inactivar una vacación (baja lógica por `PUT`).

## Endpoints exigidos
- `POST /api/vacaciones`
- `GET /api/vacaciones`
- `GET /api/vacaciones/{id}`
- `PUT /api/vacaciones/{id}`
- `PUT /api/vacaciones/{id}/inactivar`

## Reglas de negocio obligatorias
- Campos obligatorios al crear/editar: `nombreEmpleado`, `fechaInicio`, `fechaFin`.
- Validación de fechas: `fechaFin >= fechaInicio`.
- Si un id no existe, responder `404 Not Found`.
- La inactivación es lógica: cambiar `estado` a `I`, no eliminar registro.

## Detalle de trabajo esperado por capa

### 1. Service (`VacacionService`)
Completar métodos `crear`, `obtenerTodas`, `obtenerPorId`, `actualizar`, `inactivar`.
- Aplicar validaciones de negocio.
- Persistir con `VacacionRepository`.
- Convertir Entity <-> DTO.

### 2. Controller (`VacacionController`)
Completar endpoints con códigos HTTP correctos.
- `POST`: 201
- `GET`: 200
- `PUT`: 200
- Recurso inexistente: 404

### 3. Base de datos y Docker
- Verificar que la tabla `vacaciones` se cree automáticamente al iniciar MySQL.
- Verificar conexión API <-> MySQL mediante `docker compose`.

## Rúbrica (5.00 puntos)
1. `POST /api/vacaciones` (crear): **1.00**
2. `GET` lista + `GET /{id}`: **0.75**
3. `PUT /api/vacaciones/{id}` (editar): **1.00**
4. `PUT /api/vacaciones/{id}/inactivar` (baja lógica): **0.75**
5. Mapeo DTO-Entity + lógica de service limpia: **0.50**
6. Creación automática de tabla en MySQL: **0.50**
7. Dockerización funcional (app + mysql): **0.25**
8. Estados HTTP y validaciones correctas: **0.25**

## Evidencias de entrega
- Código completado.
- Capturas o colección con pruebas de endpoints.
- Proyecto levantado con Docker y base de datos inicializada.
