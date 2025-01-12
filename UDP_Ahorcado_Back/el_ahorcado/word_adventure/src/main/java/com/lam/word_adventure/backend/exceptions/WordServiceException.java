package com.lam.word_adventure.backend.exceptions;

/**
 * Clase de exception personalizada para errores de Word
 *
 * @author Laura Arvez
 */
public class WordServiceException extends RuntimeException{

    /**
     * crea una nueva excepción de service de palabras con el mensaje.
     *
     * @param message que describe la excepción
     */
    public WordServiceException(String message) {
        super(message);
    }

    /**
    * crea una nueva excepción de service de palabras con el mensaje y
    * la causa.
    *
    * @param message mensaje que describe la excepción
    * @param cause causa de la excepción
    */
    public WordServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * excepción lanzada cuando no se encuentra la dificultad
     */
    public static class DifficultyNotFoundException extends WordServiceException {
        /**
         * excepción lanzada cuando no se encuentra la dificultad
         * @param message mensaje que describe la excepción
         */
        public DifficultyNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * excepción lanzada cuando no se encuentra la temática
     */
    public static class ThemeNotFoundException extends WordServiceException {
        /**
         * excepción lanzada cuando no se encuentra la temática
         * @param message mensaje que describe la excepción
         */
        public ThemeNotFoundException(String message) {
            super(message);
        }
    }
    
    /**
     * excepción lanzada cuando no se encuentra la palabra especificada.
     */
    public static class WordNotFoundException extends WordServiceException {
        /**
         *  excepción lanzada cuando no se encuentra la palabra especificada
         *
         * @param message mensaje que describe la excepción.
         */
        public WordNotFoundException(String message) {
            super(message);
        }
    }

    /**
     * excepción lanzada cuando ocurre un error al procesar la solicitud de palabra.
     */
    public static class ProcessingException extends WordServiceException {
        /**
         * excepción lanzada cuando ocurre un error al procesar la solicitud de palabra.
         * 
         * @param message mensaje que describe la excepción.
         */
        public ProcessingException(String message) {
            super(message);
        }
    }

    /**
     * excepción lanzada cuando se intenta agregar una palabra que ya existe.
     */
    public static class WordAlreadyExistsException extends WordServiceException {
        /**
         * excepción lanzada cuando se intenta agregar una palabra que ya existe.
         * @param message mensaje que describe la excepción.
         */
        public WordAlreadyExistsException(String message) {
            super(message);
        }
    }
}
