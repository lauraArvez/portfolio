package com.lam.word_adventure.backend.security.jwt;

import static com.lam.word_adventure.backend.security.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.word_adventure.backend.security.SimpleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * clase JwtValidatationFilter recibe el token y valida que sea correcto
 * @author Laura Arvez
 */

public class JwtValidationFilter extends BasicAuthenticationFilter{

    /**
     * constructor requerido, pasa el authenticationManager a la clase padre mediante super
     * @param authenticationManager administrador de autenticación para autenticar las solicitudes
     */
    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        
    }

    /**
     * Sobrescribe el método para filtrar las solicitudes HTTP, verificando la autenticación mediante JWT.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP a enviar.
     * @param chain    Cadena de filtros para continuar con la ejecución.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error en el servlet.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
            //obtener la cabecera del token
            String header = request.getHeader(HEADER_AUTHORIZATION);
            //validar: puede ser una ruta pública un recurso no protegido
            if (header == null || !header.startsWith(PREFIX_TOKEN)){
                chain.doFilter(request, response);
                
                return;
            }

            // obtener el token sin el prefijo Bearer
            String token = header.replace(PREFIX_TOKEN, "");

            // verificar el JWT.
            try {
                    // https://github.com/jwtk/jjwt?tab=readme-ov-file#quickstart
                    Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
                    //obtener username
                    String username = claims.getSubject();
                    //obtener roles
                    Object authoritiesClaims = claims.get("authorities"); // es como se llama en la clase JwtAuthenticationFilter

                    //procesar roles
                    Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
                    //configuramos la clase SimpleGrantedAuthorityJsonCreator, clase original y la clase mixIn
                    .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                    .readValue(authoritiesClaims.toString().getBytes(), //pasamos los roles y tipo de datos a convertir
                        SimpleGrantedAuthority[].class));

                    //iniciar sesión. validamos el token
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,null,authorities);
                    //autenticar
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    //cadena de filtros
                    chain.doFilter(request, response);


            } catch (JwtException e) {
                Map<String,String> body = new HashMap<>();
                body.put("error", e.getMessage());
                body.put("message","El token JWT no es válido");

                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(CONTENT_TYPE); //"application/json"
            }
        
    }

}
