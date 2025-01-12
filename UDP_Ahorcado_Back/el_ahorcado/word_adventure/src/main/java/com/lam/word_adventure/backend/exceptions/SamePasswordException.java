package com.lam.word_adventure.backend.exceptions;

/**
 * Excepción para cuando se establece un password igual al actual
 */
public class SamePasswordException extends RuntimeException {
    /**
     * crea una nueva excepción con el mensaje
     * @param message mensaje que describe la excepción
     */
    public SamePasswordException(String message) {
        super();
    }
}
