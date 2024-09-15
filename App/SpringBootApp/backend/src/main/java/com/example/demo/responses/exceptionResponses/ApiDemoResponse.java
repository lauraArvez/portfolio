package com.example.demo.responses.exceptionResponses;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Clase que representa la respuesta estándar de la API en caso de errores o excepciones.
 * Contiene información sobre la hora en que ocurrió el error, el mensaje de error y la URL donde ocurrió el error.
 * 
 * @author Laura
 */
@Data
@NoArgsConstructor
public class ApiDemoResponse {

    /**
     * La fecha y hora en que ocurrió el error. Se inicializa con la fecha y hora actual.
     */
    private Date tiempo = new Date(); // Hora del error

    /**
     * Mensaje que describe el error o la excepción.
     */
    private String mensaje; // Mensaje de error

    /**
     * URL o ruta de donde proviene el error.
     */
    private String url; // URL de origen del error

    /**
     * Constructor que permite establecer el mensaje de error y la URL de origen.
     * La fecha y hora del error se establece automáticamente al crear la instancia.
     * 
     * @param mensaje El mensaje de error.
     * @param url La URL de donde proviene el error.
     */
    public ApiDemoResponse(String mensaje, String url) {
        this.mensaje = mensaje;
        this.url = url;
    }

}
