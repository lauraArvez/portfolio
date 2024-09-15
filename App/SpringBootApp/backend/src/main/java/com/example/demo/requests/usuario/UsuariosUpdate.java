package com.example.demo.requests.usuario;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para representar los datos de actualización de un usuario.
 * Contiene campos como nombre de usuario, contraseña y correo electrónico.
 * 
 * Se utiliza para recibir los datos de actualización de un usuario cuando este actualiza su propio perfil.
 * 
 * @author Laura
 */
@Builder
@Data
@AllArgsConstructor
public class UsuariosUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * El nombre de usuario que será actualizado.
     */
    private String username;

    /**
     * La nueva contraseña que será asignada al usuario.
     */
    private String password;

    /**
     * El correo electrónico que será actualizado.
     */
    private String email;

    /**
     * Constructor por defecto para permitir la creación de una instancia vacía.
     */
    public UsuariosUpdate() {
    }
}
