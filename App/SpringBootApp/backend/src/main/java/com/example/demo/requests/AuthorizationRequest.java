package com.example.demo.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO (Data Transfer Object) que representa una solicitud de autorización.
 * Contiene los campos necesarios para realizar una autenticación, como el nombre de usuario y la contraseña.
 * 
 * Esta clase se utiliza principalmente en procesos de autenticación y autorización.
 * 
 * @author Laura
 */
@Builder
@Data
@AllArgsConstructor
public class AuthorizationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * El nombre de usuario que se utilizará para la autenticación.
     */
    private String username;

    /**
     * La contraseña que se utilizará para la autenticación.
     */
    private String password;

    /**
     * Constructor por defecto para permitir la creación de una instancia vacía.
     */
    public AuthorizationRequest() {
    }
}