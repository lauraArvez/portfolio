package com.example.demo.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Manejador personalizado para gestionar fallos de autenticación JWT.
 * Devuelve una respuesta JSON detallada con el estado de error, mensaje y ruta.
 * 
 * Este manejador se utiliza cuando un intento de autenticación falla y proporciona detalles
 * específicos sobre la causa del fallo.
 * 
 * @author Laura
 */
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * El estado HTTP que se devolverá en la respuesta en caso de error.
     */
    private final HttpStatus statusErrorResponse;

    /**
     * Constructor que acepta un estado HTTP personalizado para la respuesta de error.
     *
     * @param statusErrorResponse El HttpStatus que se utilizará en la respuesta de error.
     */
    public JwtAuthenticationFailureHandler(HttpStatus statusErrorResponse) {
        this.statusErrorResponse = statusErrorResponse;
    }

    /**
     * Constructor por defecto que usa HttpStatus.UNAUTHORIZED.
     */
    public JwtAuthenticationFailureHandler() {
        this.statusErrorResponse = HttpStatus.UNAUTHORIZED;
    }

     /**
     * Método que se invoca cuando ocurre un fallo de autenticación.
     * Configura la respuesta HTTP con el estado de error y un mensaje en formato JSON.
     * 
     * @param request La solicitud HTTP que resultó en el fallo de autenticación.
     * @param response La respuesta HTTP que se enviará al cliente.
     * @param e La excepción que representa el fallo de autenticación.
     * 
     * @throws IOException En caso de un error de entrada/salida al escribir la respuesta.
     * @throws ServletException En caso de un error relacionado con el servlet.
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException e) throws IOException, ServletException {
        response.setStatus(statusErrorResponse.value());
        response.setContentType("application/json");
        response.getWriter().append(jsonResponse());
    }

    /**
     * Genera el cuerpo de la respuesta en formato JSON con detalles del error.
     * 
     * @return Un String en formato JSON que contiene el error, mensaje y ruta de la autenticación fallida.
     */
    private String jsonResponse() {
        long date = new Date().getTime();
        return "{ \"error\": \"Unauthorized\", "
                + "\"message\": \"Error de autenticación: credenciales incorrectas\", "
                + "\"path\": \"/auth/login\"}";
    }
}