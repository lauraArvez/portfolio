package com.example.demo.requests.usuario;


import java.io.Serializable;
import java.util.Set;

import com.example.demo.models.usuario.RoleModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para representar la información que puede ser actualizada por un administrador sobre un usuario.
 * Contiene campos como el nombre de usuario, el conjunto de roles y el correo electrónico.
 * 
 * Esta clase se utiliza principalmente para recibir y transferir datos en las operaciones de actualización de usuarios por parte de administradores.
 * 
 * @author Laura
 */
@Builder
@Data
@AllArgsConstructor
public class UserUpdateAdmin implements Serializable {

    /**
     * El nombre de usuario que será actualizado.
     */
    private String username;

    /**
     * Conjunto de roles que se asignarán o actualizarán para el usuario.
     */
    private Set<RoleModel> roles;

    /**
     * El correo electrónico que será actualizado.
     */
    private String email;

}
