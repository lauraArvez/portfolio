package com.lam.word_adventure.backend.responses;
import java.io.Serializable;
import java.util.Set;

import com.lam.word_adventure.backend.models.LocationModel;
import com.lam.word_adventure.backend.models.RoleModel;
import com.lam.word_adventure.backend.models.SexModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
/**
 * Clase de respuesta de usuario serializable que contiene información básica del usuario.
 * Esta clase se utiliza para enviar información de usuario de forma segura.
 *
 * @author Laura Arvez
 */
@Data
@Builder
@AllArgsConstructor
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * nombre de usuario
     */
    private String username;

    /**
     * localización del usuario
     */
    private LocationModel location;
    
    /**
     * edad del usuario
     */
    private int age;

    /**
     * edad del usuario
     */
    private SexModel sex;

    /**
     * roles del usuario
     */
    private Set<RoleModel> roles;

    /**
     * constructor por defecto
     */
    public UserResponse() {
    }

    
}
