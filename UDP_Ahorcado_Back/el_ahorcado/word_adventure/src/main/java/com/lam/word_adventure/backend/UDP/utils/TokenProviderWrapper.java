package com.lam.word_adventure.backend.UDP.utils;

import io.jsonwebtoken.Claims;

/**
 * clase para uso en el test Token Provider Wrapper
 * @author Laura Arvez
 */
public class TokenProviderWrapper {

    /**
     * constructor por defecto
     */
    public TokenProviderWrapper(){

    }

    /**
     * Verifica la validez y decodifica un token JWT, devolviendo sus claims.
     *
     * @param token Token JWT que se desea verificar y decodificar.
     * @return Objeto Claims que contiene las reclamaciones del token decodificado.
     */
    public Claims verifyJws (String token){
        return TokenProvider.verifyJws(token);
    }

    /**
     * verifica su firma utilizando una clave secreta y devuelve el nombre de usuario contenido
     * en las reclamaciones del token JWT.
     *
     * @param token del cual se desea obtener el usuario
     * @return el nombre del usuario asosciado
     */
    public String getUserName(final String token) {
        return TokenProvider.getUserName(token);
    }
        
    
}
