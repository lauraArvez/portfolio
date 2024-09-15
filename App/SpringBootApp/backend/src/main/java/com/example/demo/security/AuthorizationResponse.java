package com.example.demo.security;

/**
 * Clase modelo que representa la respuesta de autorización con un token JWT.
 * 
 * Esta clase es utilizada para enviar el token JWT como respuesta al cliente después de una autenticación exitosa.
 * 
 * @author Laura
 */
public class AuthorizationResponse {

    /**
     * El token JWT generado para la autenticación.
     */
    private String token;

    /**
     * Constructor por defecto.
     * Se utiliza cuando se necesita crear una instancia vacía de la clase.
     */
    public AuthorizationResponse() {
    }

    /**
     * Constructor que acepta un token JWT.
     *
     * @param token el token JWT generado
     */
    public AuthorizationResponse(String token) {
        this.token = token;
    }

    /**
     * Devuelve el token JWT.
     * 
     * @return el token JWT
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Establece el token JWT.
     *
     * @param token el token JWT a asignar
     */
    public void setToken(String token) {
        this.token = token;
    }
}
