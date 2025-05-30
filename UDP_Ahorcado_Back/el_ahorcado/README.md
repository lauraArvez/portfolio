# Aventura de Palabras

**Aventura de Palabras** es un servidor interactivo que da vida al clásico juego del ahorcado.  
Este proyecto incluye funcionalidades avanzadas para la gestión de usuarios, palabras y puntuaciones, proporcionando una experiencia completa y segura. También incorpora autenticación mediante JWT para proteger los datos y garantizar el acceso autorizado.

## Descripción
Este servidor permite:

### Gestión de usuarios
- Creación de usuarios con roles personalizados: **user** y **admin**.
- Cambio de contraseñas con almacenamiento seguro (contraseñas encriptadas).
- Listado de usuarios con opciones de filtrado avanzadas:
  - Filtrar por **sexo**, **rol**, **edad** o **ubicación**.
- Eliminación de usuarios de la base de datos.
- Recuperación de un **ranking de juegos** por usuario.

### Gestión de palabras
- Registro de palabras categorizadas por **dificultad**: muy fácil, fácil, difícil, muy difícil.
- Asignación de palabras a **temáticas específicas** como "animales", "deportes", etc.
- Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para palabras.
- Listado de palabras filtradas por dificultad o temática.

### Gestión de puntuaciones
- Registro de puntuaciones obtenidas por los usuarios.
- Listado de puntuaciones con opciones avanzadas de filtrado:
  - Filtrar por **rangos de edad**, **ubicación** o **rankings**.
- Listado general de todas las puntuaciones almacenadas.

### Autenticación y seguridad
- Implementación de inicio de sesión mediante **JWT (JSON Web Tokens)**.
- Seguridad adicional para proteger los datos y garantizar el acceso autorizado.

## Características principales
- **Usuarios**: Crear, listar, filtrar, editar y eliminar usuarios.
- **Palabras**: Gestión completa de palabras con clasificación por dificultad y temática.
- **Puntuaciones**: Registro y listado de puntuaciones con filtros avanzados.
- **Seguridad**: Sistema de autenticación robusto con JWT.

## Tecnologías utilizadas
- **Back-end**:
  - Java
  - Spring Boot
  - Maven
  - MySQL
  - JWT (JSON Web Tokens)
  - UDP
- **Pruebas**:
  - Packet Sender
- **Herramientas de desarrollo**:
  - Visual Studio Code
  - Docker

## Cómo usarlo
Este proyecto está completamente 


