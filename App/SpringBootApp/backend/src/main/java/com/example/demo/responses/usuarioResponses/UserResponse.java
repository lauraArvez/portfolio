package com.example.demo.responses.usuarioResponses;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * DTO (Data Transfer Object) que representa la respuesta al cliente con la información de un usuario.
 * Contiene el nombre de usuario y el correo electrónico.
 * 
 * Se utiliza para devolver información básica del usuario en las respuestas de la API, sin incluir detalles sensibles.
 * 
 * @author Laura
 */
@Data
@Builder
@AllArgsConstructor
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * El nombre de usuario.
     */
    private String user;

    /**
     * El correo electrónico del usuario.
     */
    private String email;
}