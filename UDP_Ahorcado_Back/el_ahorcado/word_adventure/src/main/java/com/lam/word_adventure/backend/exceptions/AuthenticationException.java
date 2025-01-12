package com.lam.word_adventure.backend.exceptions;

/**
 * Clase de de exception de autenticación
 *
 * @author Laura Arvez
 */
public class AuthenticationException extends RuntimeException {
    
    /**
     * crea una nueva excepción de autenticación con el mensaje especificado.
     *
     * @param message mensaje que describe la excepción.
     */
    public AuthenticationException(String message) {
        super(message);
    }
    /**
     * excepción para contraseñas igual a la anterior en cambio de password
     */
    public static class SamePasswordException extends AuthenticationException {
        /**
         * Crea una excepción de contraseñas iguales.
         *
         * @param message mensaje que describe la excepción.
         */
        public SamePasswordException(String message) {
            super(message);
        }
    }

    /**
     * Excepción lanzada cuando se encuentran credenciales ambiguas.
     */
    public static class AmbiguousCredentialsException extends AuthenticationException {
        /**
         * crea una excepción de credenciales ambiguas
         *
         * @param message mensaje que describe la excepción.
         */
        public AmbiguousCredentialsException(String message) {
            super(message);
        }
    }
}