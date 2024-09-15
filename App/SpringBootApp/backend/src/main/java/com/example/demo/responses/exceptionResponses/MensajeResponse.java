package com.example.demo.responses.exceptionResponses;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Clase que representa una respuesta estándar de mensaje, utilizada para enviar mensajes y objetos adicionales
 * en las respuestas HTTP o en las comunicaciones internas de la aplicación.
 * Implementa Serializable para permitir la transmisión del objeto a través de la red o en procesos de serialización.
 * 
 * @author Laura
 */
@Data
@ToString
@Builder
public class MensajeResponse  implements Serializable {
    
    // Asegurar la compatibilidad en la serialización entre diferentes versiones de la clase.
    private static final long serialVersionUID = 1L;


    /**
     * Mensaje que describe la respuesta.
     */
    private String mensaje;

    /**
     * Objeto adicional que puede acompañar al mensaje, como datos relacionados o resultados de operaciones.
     */
    private Object object;

}



