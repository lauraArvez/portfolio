package com.example.demo.excepciones;

/**
 * Excepción personalizada para manejar el caso en que un rol no se encuentra en la base de datos.
 * 
 * @author Laura Arvez
 */
public class CustomRoleNotFoundException extends RuntimeException {

    /**
     * Constructor que permite crear una excepción con un mensaje personalizado.
     * 
     * @param message El mensaje descriptivo del error.
     */
    public CustomRoleNotFoundException(String message) {
        super(message);
    }
}