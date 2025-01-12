package com.lam.word_adventure.backend.UDP.utils;

import static com.lam.word_adventure.backend.security.TokenJwtConfig.*;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

/**
 * provee, gestiona token
 * @author Laura Arvez
 */
public class TokenProvider {

    //private static TokenService tokenService;

    private TokenProvider() {
    }

    /**
     * Genera un token JWT para el nombre de usuario dado.
     *
     * @param username Nombre de usuario para el cual se generará el token.
     * @return Token JWT generado como un String.
     */
    public static String generateToken(String username) {
        // Genera el token con roles, issuer, fecha, expiració
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis()+3600000)) //hora actual + 1h en milisegundos
                .issuedAt(new Date()) //fecha de creación del token
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Verifica la validez y decodifica un token JWT, devolviendo sus claims.
     *
     * @param token Token JWT que se desea verificar y decodificar.
     * @return Objeto Claims que contiene las reclamaciones del token decodificado.
     */
    public static Claims verifyJws(String token) {
        try {
            String tokenAuth = token.replace("Bearer ","");
            return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(tokenAuth)
                .getPayload();
            
        } catch (JwtException e) {
            System.err.println("Error al procesar el token JWT: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Se ha producido un error inesperado: " + e.getMessage());
            return null;
        }
    }

    /**
     *  esta función toma un token JWT y los detalles del usuario como entrada,
     * verifica el token y genera un objeto UsernamePasswordAuthenticationToken que
     * representa la autenticación del usuario en Spring Security
     *
     * Obtiene una instancia de autenticación basada en un token JWT y detalles de usuario.
     *
     * @param token Token JWT que se utilizará para la autenticación.
     * @param userDetails Detalles del usuario para asociar con la autenticación.
     * @return Instancia de UsernamePasswordAuthenticationToken para la autenticación.
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(final String token,
            final UserDetails userDetails) {

        final JwtParser jwtParser = (JwtParser) Jwts.parser().verifyWith(SECRET_KEY);
        //.setSigningKey(SECRET_KEY);

        final Jws<Claims> claimsJws = jwtParser.parseSignedClaims(token);

        final Claims claims = claimsJws.getPayload();

        /* final Collection<SimpleGrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());*/

            //return new UsernamePasswordAuthenticationToken(token,"");
        return new UsernamePasswordAuthenticationToken(userDetails, "");
    }

    /**
     * verifica su firma utilizando una clave secreta y devuelve el nombre de usuario contenido
     * en las reclamaciones del token JWT.
     * 
     * @param token del cual se desea obtener el usuario
     * @return el nombre del usuario asosciado
     */
    public static String getUserName(final String token) { //token sin el prefijo
        
        try{
            String tokenAuth = token.replace("Bearer ","");

            //JwtParser jwtParser = (JwtParser) Jwts.parser().verifyWith(SECRET_KEY);

            //Jws<Claims> claimsJws = jwtParser.parseSignedClaims(tokenAuth);

            // Parsear y verificar el token JWT
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(tokenAuth);
                        //.getPayload();
                
                            //.build()
                    //.parseClaimsJws(tokenAuth);

            return claimsJws.getPayload().getSubject();
        
        } catch (JwtException e) {
            System.err.println("Error al procesar el token JWT: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Se ha producido un error inesperado: " + e.getMessage());
            return null;
        }
    }

    
}
