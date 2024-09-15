package com.example.demo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para manejar errores de solicitudes incorrectas (HTTP 400).
 * Se lanzará cuando ocurra un error que deba ser manejado como un "Bad Request".
 * 
 * Anotada con @ResponseStatus para devolver un código de estado HTTP 400 automáticamente.
 * 
 * @author Laura Arvez
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    /**
     * Constructor que permite crear una excepción con un mensaje personalizado.
     * 
     * @param mensaje El mensaje descriptivo del error.
     */
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
