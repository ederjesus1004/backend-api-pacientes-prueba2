# API de Pacientes

API REST para registrar a los pacientes de una clínica. Tiene un CRUD completo con validaciones, reglas de negocio y manejo de errores en un solo lugar.

## Tecnologías

- Java 21
- Spring Boot 4 (Web MVC, Data JPA, Validation)
- PostgreSQL
- Lombok
- Maven

## Endpoints

| Método | Ruta | Descripción | Respuesta |
|---|---|---|---|
| GET | `/api/pacientes` | Lista todos los pacientes | 200 |
| GET | `/api/pacientes/{id}` | Busca un paciente por id | 200 / 404 |
| POST | `/api/pacientes` | Registra un paciente | 201 / 400 / 409 |
| PUT | `/api/pacientes/{id}` | Actualiza un paciente | 200 / 400 / 404 / 409 |
| DELETE | `/api/pacientes/{id}` | Elimina un paciente | 204 / 404 |

## Reglas

- El DNI tiene 8 dígitos y no se puede repetir.
- El email no se puede repetir. Se guarda en minúsculas, así que `Carlos@Mail.com` y `carlos@mail.com` cuentan como el mismo.
- Nombres, apellidos y seguro son obligatorios.
- La edad va de 0 a 120.
- Al actualizar, un paciente puede conservar su propio DNI y email, pero no usar los de otro paciente.

## Errores

Todos los errores responden con el mismo formato:

| Código | Cuándo |
|---|---|
| 400 | Datos que no cumplen las validaciones, o JSON mal escrito |
| 404 | El paciente no existe |
| 409 | El DNI o el email ya están registrados |
| 500 | Error inesperado (el detalle queda en el log) |

```json
{
  "fecha": "2026-10-14T10:45:00",
  "estado": 400,
  "mensaje": "Datos inválidos",
  "errores": {
    "dni": "El DNI debe tener 8 dígitos",
    "edad": "La edad máxima es 120"
  }
}
```

## Estructura

```
src/main/java/dev/eder/paciente/
├── entity/         Paciente (la tabla)
├── repository/     consultas a la base de datos
├── dto/            PacienteRequest (entrada) y PacienteResponse (salida)
├── excepcion/      excepciones propias, ErrorResponse y GlobalExceptionHandler
├── mapper/         convierte entre DTO y entidad
├── service/        interface + implementación con las reglas de negocio
└── controller/     endpoints REST
```

## Cómo correrlo

1. Crea la base de datos en PostgreSQL:

```sql
   CREATE DATABASE pacientes_db;
```

2. Configura tu contraseña de PostgreSQL como variable de entorno:

```
   DB_PASSWORD=tu_contraseña
```

   Si no la defines, el proyecto usa `postgres`.

3. Ejecuta el proyecto:

```bash
   ./mvnw spring-boot:run
```

La API queda en `http://localhost:8080` y la tabla `pacientes` se crea sola al arrancar.

## Ejemplo

**POST** `/api/pacientes`

```json
{
  "dni": "45678912",
  "nombres": "Carlos",
  "apellidos": "Quispe Mendoza",
  "email": "Carlos.Q@Mail.com",
  "seguro": "EsSalud",
  "edad": 34
}
```

Respuesta **201 Created**:

```json
{
  "id": 1,
  "dni": "45678912",
  "nombres": "Carlos",
  "apellidos": "Quispe Mendoza",
  "descripcion": "Carlos Quispe Mendoza - EsSalud",
  "email": "carlos.q@mail.com",
  "seguro": "EsSalud",
  "edad": 34,
  "fechaRegistro": "2026-10-14T10:45:00"
}
```

## Autor

Eder Cuaresma · [GitHub](https://github.com/ederjesus1004)
