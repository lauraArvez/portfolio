# Windows Forms Application - InterfazRest

## Descripción del Proyecto

Esta aplicación **Windows Forms** es la interfaz de usuario de la aplicación **InterfazRest**, la cual está conectada a una API desarrollada en **Spring Boot**. 
Esta interfaz gráfica permite a los administradores y usuarios gestionar el acceso mediante la autenticación, registro de usuarios, y la modificación de contraseñas. 
Aunque otras secciones de la aplicación están en desarrollo, las funcionalidades mencionadas están completamente integradas con el servidor.

## Características

- **Funcionalidades Conectadas al Servidor**:
  - **Login de Administrador**: Permite a los administradores iniciar sesión y gestionar el acceso.
  - **Login de Usuario**: Los usuarios pueden iniciar sesión en la aplicación.
  - **Registro de Nuevos Usuarios**: El administrador puede registrar nuevos usuarios a través de la interfaz de registro.
  - **Modificación de Contraseñas**: El administrador puede restablecer y modificar las contraseñas.
  
- **Funcionalidades en Desarrollo** (Aún no conectadas al servidor):
  - Gestión de atletas
  - Visualización y registro de competencias
  - Gestión de eventos y calendarios
  - Gestión del perfil de usuario y tienda en línea

## Tecnologías Utilizadas

- **C#** - Lenguaje principal para el desarrollo de la aplicación.
- **Windows Forms** - Plataforma utilizada para la creación de la interfaz gráfica.
- **FontAwesome.Sharp** - Librería de iconos utilizada en los botones y menús.
- **Spring Boot API** - API RESTful a la cual se conecta la interfaz para interactuar con los datos.
- **MySQL Workbench** - Base de datos para la persistencia de la información.
