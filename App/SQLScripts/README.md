# SQL Scripts - InterfazRest

## Descripción del Proyecto

Este repositorio contiene el script SQL necesario para la creación de la base de datos utilizada por la aplicación **InterfazRest**.

## Estructura del Proyecto

El repositorio está organizado de la siguiente manera:

- **01_create_database.sql**: Script para la creación de la base de datos **demo** con el conjunto de caracteres UTF-8.

## Script Detallado

### 1. **01_create_database.sql**

Este script crea la base de datos **demo** con el conjunto de caracteres **utf8mb4** para soportar caracteres Unicode completos.

```sql
-- Crear la base de datos
CREATE DATABASE demo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE demo;
