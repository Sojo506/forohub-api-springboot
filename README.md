# Challenge Foro Hub

## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Estado del Proyecto](#estado-del-proyecto)
3. [Demostración de funciones y aplicaciones](#demostración-de-funciones-y-aplicaciones)
4. [Acceso al Proyecto](#acceso-al-proyecto)
5. [Tecnologías utilizadas](#tecnologías-utilizadas)
6. [Personas Contribuyentes](#personas-contribuyentes)
7. [Personas Desarrolladoras del Proyecto](#personas-desarrolladoras-del-proyecto)
8. [Licencia](#licencia)

## Descripción del Proyecto
Foro Hub es una API REST y un challenge construida con Spring Boot que permite gestionar tópicos dentro de un foro. Los usuarios pueden crear, actualizar, eliminar y consultar tópicos. Los tópicos contienen información como título, mensaje y curso relacionado, y están asociados a usuarios específicos. La API también incluye un sistema de autenticación basado en JWT para asegurar las operaciones.

## Estado del Proyecto
- **Etapa actual**: Desarrollo en curso
- **Funcionalidades implementadas**:
  - CRUD de Tópicos (Crear, Leer, Actualizar, Eliminar)
  - Autenticación con JWT
- **Pendientes**:
  - Mejoras en la validación de los datos de entrada

## Demostración de funciones y aplicaciones

### CRUD de Tópicos

1. **Crear un nuevo tópico** (HTTP POST)
   ```
   POST http://localhost:8080/topicos
   ```

   **Cuerpo de la solicitud**:
   ```json
   {
       "idUsuario": "1",
       "titulo": "Fundamentos de Ciberseguridad",
       "mensaje": "La Ciberseguridad es muy importante hoy en día, ya que protege la información sensible de los usuarios.",
       "curso": "Ciberseguridad para Principiantes"
   }
   ```
   
2. **Obtener todos los tópicos** (HTTP GET)
   ```
   GET http://localhost:8080/topicos
   ```

3. **Obtener un tópico por ID** (HTTP GET)
   ```
   GET http://localhost:8080/topicos/{id}
   ``` 

4. **Actualizar un tópico existente** (HTTP PUT)
   ```
   PUT http://localhost:8080/topicos/{id}
   ```

   **Cuerpo de la solicitud**:
   ```json
   {
       "titulo": "Introducción a Docker",
       "mensaje": "Docker simplifica la creación y administración de contenedores para tus aplicaciones.",
       "curso": "Contenerización con Docker"
   }
   ```

### Login de Usuario

- **Autenticación de usuario** (HTTP POST)
   ```
   POST http://localhost:8080/login
   ```

   **Cuerpo de la solicitud**:
   ```json
   {
       "username": "usuario",
       "password": "contraseña"
   }
   ```

## Acceso al Proyecto
Para acceder y probar la API localmente:

1. Clona este repositorio:
   ```bash
   git clone https://github.com/Sojo506/forohub-api-springboot.git
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd forohub
   ```

3. Construye el proyecto:
   ```bash
   ./mvnw clean install
   ```

4. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

La API estará disponible en [http://localhost:8080](http://localhost:8080).

## Tecnologías utilizadas
- **Java 17** (JDK)
- **Spring Boot** (para la construcción de la API REST)
- **Spring Security** (para la autenticación JWT)
- **JPA & Hibernate** (para la interacción con la base de datos)
- **Flyway** (para la migración de la base de datos)
- **MySQL** (base de datos)
- **Lombok** (para la reducción de boilerplate)
- **JWT** (para la autenticación de usuarios)

## Personas Desarrolladoras del Proyecto
- **Fabián Sojo** – Desarrollador principal.

---

¡Gracias por tu interés en el proyecto Foro Hub!
