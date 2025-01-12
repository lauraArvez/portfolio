package com.lam.word_adventure.backend.security;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

/**
 * clase de constantes relacionadas con la generación y validación de tokens JWT
 * @author Laura Arvez
 */
public class TokenJwtConfig {

    /**
     * contructor por defecto
     */
    TokenJwtConfig (){

    }
    
    /**
     * Clave secreta utilizada para firmar y verificar tokens JWT.
     * Consulta: https://github.com/jwtk/jjwt?tab=readme-ov-file#quickstart
     */
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); //genera la clave secreta
    
    /**
     * Prefijo utilizado para los tokens JWT en el encabezado de autorización
     */
    public static final String PREFIX_TOKEN = "Bearer ";

    /**
     * Nombre del encabezado de autorización HTTP utilizado para enviar el token JWT.
     */
    public static final String HEADER_AUTHORIZATION = "Authorization";

    /**
     * Tipo de contenido utilizado para las respuestas relacionadas con tokens JWT.
     */
    public static final String CONTENT_TYPE = "application/json";

    /**
     * Clave utilizada en las reclamaciones del token JWT para almacenar la información de los roles.
     */
    public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

    //public static final String SIGNING_KEY = "KEY_ALEATORIA_1234";

    /**
     * Duración de validez del token de acceso en segundos.
     */
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 3600;

    /**
     * Emisor (issuer)del token JWT
     */
    public static final String ISSUER_TOKEN = "TOKEN_ISS";
    
}
