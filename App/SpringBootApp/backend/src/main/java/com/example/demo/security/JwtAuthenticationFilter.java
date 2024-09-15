package com.example.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.requests.AuthorizationRequest;
import com.example.demo.utils.TokenProvider;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.demo.utils.Constants.HEADER_AUTHORIZATION_KEY;
import static com.example.demo.utils.Constants.LOGIN_URL;
import static com.example.demo.utils.Constants.TOKEN_BEARER_PREFIX;

/**
 * Filtro de autenticación JWT que extiende UsernamePasswordAuthenticationFilter.
 * Se encarga de procesar las solicitudes de inicio de sesión, autenticar al usuario y generar tokens JWT.
 * 
 * Este filtro es activado en las solicitudes a la URL de login y devuelve un token JWT en caso de autenticación exitosa.
 * 
 * @author Laura
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * El AuthenticationManager se encarga de realizar la autenticación del usuario.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor que acepta un AuthenticationManager.
     *
     * @param authenticationManager el AuthenticationManager utilizado para autenticar al usuario
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // Establecer la URL en la cual se procesarán las solicitudes de autenticación.
        super.setFilterProcessesUrl(LOGIN_URL);

        // Establecer el manejador de fallos de autenticación.
        super.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());

    }

    /**
     * Intenta autenticar al usuario utilizando las credenciales recibidas en la solicitud.
     * Si la autenticación falla, lanza una excepción personalizada.
     *
     * @param request La solicitud HTTP que contiene las credenciales del usuario.
     * @param response La respuesta HTTP.
     * @return Un objeto Authentication si la autenticación es exitosa.
     * @throws UserAuthenticationFailedException si ocurre un error de autenticación.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws UserAuthenticationFailedException {
        try {
            // Extraer las credenciales del usuario de la solicitud HTTP.
            AuthorizationRequest userCredentials = new ObjectMapper().readValue(request.getInputStream(),
                    AuthorizationRequest.class);
                log.info("Attempting authentication for user: {}", userCredentials.getUsername());
            
                // Autenticar utilizando el AuthenticationManager.
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userCredentials.getUsername(), userCredentials.getPassword()));
        } catch (IOException e) {

            // Manejar errores de entrada/salida al procesar la solicitud.
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            throw new UserAuthenticationFailedException(e.getMessage());
        }
    }

     /**
     * Procesa una autenticación exitosa generando un token JWT y devolviéndolo en la respuesta.
     *
     * @param request La solicitud HTTP.
     * @param response La respuesta HTTP que contendrá el token JWT.
     * @param chain La cadena de filtros.
     * @param authResult El resultado de la autenticación.
     * @throws IOException Si ocurre un error al escribir la respuesta.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        // Generar el token JWT basado en el resultado de la autenticación.
        String token = TokenProvider.generateToken(authResult);

        // Crear una respuesta de autorización con el token generado.
        AuthorizationResponse loginRes = new AuthorizationResponse(token);

        // Agregar el token JWT a los encabezados de la respuesta.
        response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);

        // Configurar el tipo de contenido y devolver la respuesta en formato JSON.
        response.addHeader("Content-Type", "application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(loginRes));
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
        log.info("User successfully authenticated. Token generated: {}", token);
    }

}