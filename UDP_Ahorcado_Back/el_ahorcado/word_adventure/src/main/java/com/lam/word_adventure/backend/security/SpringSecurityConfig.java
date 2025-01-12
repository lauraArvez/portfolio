package com.lam.word_adventure.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lam.word_adventure.backend.security.jwt.JwtAuthenticationFilter;
import com.lam.word_adventure.backend.security.jwt.JwtValidationFilter;

/**
 * Clase de configuración spring security
 * Para crear el token y retornar al cliente para hacer login
 * 
 * @author Laura Arvez
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    
    /**
     * constructor por defecto de SpringSecurityConfig
     */
    public SpringSecurityConfig() {
    }

    /**
     * genera con el bean el objeto authenticationManager
     * @return authenticationManager
     * @throws Exception
     */
    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * encripta password
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Método que configura la cadena de filtros, la seguridad
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
            .csrf(config -> config.disable()) // token Cross-Site-Request Forgery
            .authorizeHttpRequests(authorize -> authorize// configura el acceso a las url, endpoints
                .requestMatchers(HttpMethod.POST,"/api/v1/users/register").permitAll() // acceso permitido a esta ruta que es un ednpoint público
                .requestMatchers(HttpMethod.GET,"/api/v1/users").permitAll() // acceso permitido a esta ruta que es un endpoint público
                .requestMatchers("/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**", "/v3/api-docs.yaml").permitAll()
                .anyRequest().authenticated()) //cualquier otro debe autenticarse para acceder
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                //para que la sesión http no tenga estado y que todo lo que es autenticación se maneje en el token
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }
}

