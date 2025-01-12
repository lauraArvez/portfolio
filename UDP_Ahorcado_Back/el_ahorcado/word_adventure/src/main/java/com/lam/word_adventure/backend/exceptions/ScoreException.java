package com.lam.word_adventure.backend.exceptions;

/**
 * Clase de exception personalizada para errores de Score
 *
 * @author Laura Arvez
 */
public class ScoreException extends RuntimeException {

    /**
     * crea una nueva excepción de puntuación con un mensaje
     *
     * @param message mensaje que describe la excepción
     */
    public ScoreException (String message){
        super(message);
    }

    /**
     * crea una nueva excepción de puntuación con un mensaje y la causa
     *
     * @param message mensaje que describe la excepción
     * @param cause causa de la excepción
     */
    public ScoreException (String message, Throwable cause){
        super(message,cause);
    }
    
}
