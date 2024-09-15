package com.example.demo.repositories.usuario;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.usuario.UsuarioModel;

/**
 * Repositorio para gestionar operaciones con la base de datos de usuarios.
 * Extiende {@link JpaRepository} para proporcionar las funcionalidades CRUD básicas,
 * así como métodos personalizados para buscar usuarios por su nombre de usuario y estado de activación.
 * 
 * Incluye soporte para paginación y consultas nativas.
 * 
 * @author Laura
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    /**
     * Busca un usuario por su nombre de usuario exacto.
     * 
     * @param username El nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si es encontrado, o vacío si no existe.
     */
    Optional<UsuarioModel> findByUsername(String username);

    /**
     * Busca usuarios cuyo nombre de usuario contenga una cadena específica, ignorando mayúsculas.
     * 
     * @param username El nombre (o parte del nombre) de usuario a buscar.
     * @return Un Optional que contiene el usuario si es encontrado, o vacío si no existe.
     */
    Optional<UsuarioModel> findByUsernameContainingIgnoreCase(String username);


    /**
     * Devuelve todos los usuarios con paginación.
     * 
     * @param pageable Parámetro que define la paginación de los resultados.
     * @return Una página con los usuarios del sistema.
     */
    Page<UsuarioModel> findAll(Pageable pageable);
    
    /**
     * Consulta nativa para obtener todos los usuarios, incluyendo solo el nombre de usuario y email.
     * Esta consulta usa paginación para manejar grandes volúmenes de datos de manera eficiente.
     * 
     * @param pageable Parámetro que define la paginación de los resultados.
     * @return Una página con los usuarios del sistema, mostrando únicamente el username y el email.
     */
    @Query( value = "SELECT username, email FROM usuarios;", nativeQuery = true )
    Page <UsuarioModel>todosLosUsuarios(Pageable pageable);

    /**
     * Obtiene una lista de todos los usuarios que están activos.
     * 
     * @return Una lista de usuarios activos (donde el campo "activo" es true).
     */
    List<UsuarioModel> findByActivoTrue();

    /**
     * Verifica si existe un usuario con un nombre de usuario específico.
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);
}