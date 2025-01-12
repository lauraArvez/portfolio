package com.lam.word_adventure.backend.exceptions;

/**
* Excepción personalizada que representa un token JWT no válido.
* Esta excepción se utiliza para indicar que un token JWT proporcionado no es válido o ha expirado.
*
 * @author Laura Arvez
 */
public class TokenInvalidException extends Exception{
    
    /**
     * Constructor que inicializa una instancia de TokenInvalidException con un mensaje y una causa.
     *
     * @param message mensaje descriptivo que indica la razón de la excepción
     * @param cause   la causa raíz que provocó esta excepción
     */
    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
