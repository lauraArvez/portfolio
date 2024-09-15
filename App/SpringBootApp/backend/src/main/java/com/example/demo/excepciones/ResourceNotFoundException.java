package com.example.demo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada que se lanza cuando un recurso no se encuentra en la base de datos.
 * Anotada con @ResponseStatus(HttpStatus.NOT_FOUND) para devolver un código de estado HTTP 404.
 * 
 * @author Laura Arvez
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String recursoNombre;
    private String nombreCampo;
    private Object valorCampo;

    /**
     * Constructor para crear una excepción con detalles específicos sobre el recurso no encontrado.
     * 
     * @param recursoNombre Nombre del recurso que no se encontró.
     * @param nombreCampo Nombre del campo utilizado para buscar el recurso.
     * @param valorCampo Valor del campo utilizado para buscar el recurso.
     */
    public ResourceNotFoundException(String recursoNombre, String nombreCampo, Object valorCampo) {
        super(String.format("No fue encontrado %s con: %s='%s'", recursoNombre, nombreCampo, valorCampo));
        this.recursoNombre = recursoNombre;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }

    /**
     * Constructor para crear una excepción sin especificar el campo y el valor.
     * 
     * @param recursoNombre Nombre del recurso que no se encontró.
     */
    public ResourceNotFoundException(String recursoNombre) {
        super(String.format("No hay registros de %s en la base de datos.", recursoNombre));
        this.recursoNombre = recursoNombre;
    }
}