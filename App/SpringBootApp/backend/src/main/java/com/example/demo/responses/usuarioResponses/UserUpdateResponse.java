package com.example.demo.responses.usuarioResponses;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data Transfer Object) que representa la respuesta cuando se actualiza un usuario.
 * Contiene información básica como el nombre de usuario y el correo electrónico.
 * 
 * Se utiliza para devolver la información actualizada del usuario en las respuestas de la API después de que los datos del usuario han sido modificados.
 * 
 * @author Laura
 */
@Data
@Builder
@AllArgsConstructor
public class UserUpdateResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * El nombre de usuario actualizado.
     */
    private String username;

    /**
     * El correo electrónico actualizado.
     */
    private String email;
}
