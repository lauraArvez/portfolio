package com.lam.word_adventure.backend.security.jwt;

import static com.lam.word_adventure.backend.security.TokenJwtConfig.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.word_adventure.backend.models.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * clase para autenticar y generar el token
 * @author Laura Arvez
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager authenticationManager;

    /**
     * Constructor que recibe el AuthenticationManager para la autenticación.
     *
     * @param authenticationManager El administrador de autenticación utilizado para autenticar las solicitudes.
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager =  authenticationManager;
    }

    /**
     * convertir el json del request y convertirlo a un objeto de java (deserialización)
     * @param request solicitud HTTP recibida
     * @param response respuesta HTTP a enviar
     * @return autenticación resultante después de intentar autenticar la solicitud.
     * @throws AuthenticationException si ocurre un error durante la autenticación
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UserModel userModel = null;
        String username =null;
        String password=null;
        try {
            userModel = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
            username = userModel.getUsername();
            password = userModel.getPassword();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * Ejecuta acciones después de una autenticación exitosa, generando un token JWT y devolviéndolo al cliente.
     *
     * @param request    La solicitud HTTP recibida.
     * @param response   La respuesta HTTP a enviar.
     * @param chain      Cadena de filtros para continuar con la ejecución.
     * @param authResult El resultado de la autenticación exitosa.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error en el servlet.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        //obtener el username
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)authResult.getPrincipal();
        String username = user.getUsername();
        Collection <? extends GrantedAuthority> roles = authResult.getAuthorities(); // obtener los roles
        //genera claims para pasar los roles
        Claims claims =Jwts.claims()
            .add("authorities", new ObjectMapper().writeValueAsString(roles))// pasamos a json
          //  .add("username", username)
            .build();
        
        //https://github.com/jwtk/jjwt?tab=readme-ov-file#quickstart
        String token = Jwts.builder() //genera el token
            .subject(username)
            .claims(claims)
            .expiration(new Date(System.currentTimeMillis()+3600000)) //hora actual + 1h en milisegundos
            .issuedAt(new Date()) //fecha de creación del token
            .signWith(SECRET_KEY)
            .compact();

        // devuelve el token al cliente
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        // utiliza map para pasar en formato json
        Map<String, String> body = new HashMap<>();
        body.put(("token"), token); //se pasa sin el Bearer
        body.put("username", username);
        body.put("roles", new ObjectMapper().writeValueAsString(roles));
        body.put("message", String.format("Hola %s sesión inciada!", username));
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(body)); //genera el json
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    /**
     * Ejecuta acciones después de una autenticación fallida, enviando un mensaje de error al cliente.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP a enviar.
     * @param failed   La excepción que causó la autenticación fallida.
     * @throws IOException      Si ocurre un error de entrada/salida.
     * @throws ServletException Si ocurre un error en el servlet.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        Map<String, String> body = new HashMap<>();
        body.put("message", String.format("ERROR en la autenticación username o password incorrectos!"));
        body.put("error: 401 Unauthorized", failed.getMessage()); //causa del error

        response.getWriter().write(new ObjectMapper().writeValueAsString(body)); //genera el json
        response.setStatus(401); // no autorizado
        response.setContentType(CONTENT_TYPE); //de la clase de constanttesTokenJwtConfig
    }

}
