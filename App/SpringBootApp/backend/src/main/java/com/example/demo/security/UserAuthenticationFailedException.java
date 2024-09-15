package com.example.demo.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepción personalizada que se lanza cuando falla la autenticación del usuario.
 */
public class UserAuthenticationFailedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor que acepta un mensaje de error.
     *
     * @param msg el mensaje de error
     */
    public UserAuthenticationFailedException(String msg) {
        super(msg);
    }

}