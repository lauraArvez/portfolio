package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import com.example.demo.services.springSecurity.UsuarioDetailsService;
import com.example.demo.services.usuario.UsuarioService;
import lombok.extern.slf4j.Slf4j;

/**
 * Configuración de seguridad web de Spring.
 * Configura CORS, CSRF, las rutas permitidas y protegidas, además de integrar los filtros de autenticación
 * y autorización JWT para gestionar la seguridad de las solicitudes.
 * 
 * Esta clase extiende {@link WebSecurityConfigurerAdapter} para personalizar la seguridad de la aplicación.
 * 
 * @author Laura
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Servicio personalizado de detalles del usuario.
     */
    @Autowired
    UsuarioDetailsService userDetailsService;

    /**
     * Servicio de usuario para manejar operaciones relacionadas con usuarios.
     */
    @Autowired
    UsuarioService usuarioService;

    /**
     * Cifra contraseñas usando BCrypt.
     *
     * @return Una instancia de {@link BCryptPasswordEncoder} para cifrar contraseñas.
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exponer el bean de AuthenticationManager para que se pueda inyectar en otros componentes.
     * 
     * @return el bean de {@link AuthenticationManager}.
     * @throws Exception en caso de que la configuración falle.
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configura la seguridad HTTP, permitiendo el acceso a ciertas rutas y protegiendo otras.
     * Define CORS, CSRF, rutas abiertas y restringidas, y los filtros JWT de autenticación y autorización.
     *
     * @param http La instancia de {@link HttpSecurity} para configurar la seguridad.
     * @throws Exception si ocurre algún error durante la configuración.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors() // Cross-Origin Resource Sharing
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()  // Permitir acceso al login
                .antMatchers(HttpMethod.POST, "/api/usuario/registrar").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/usuario/reset-password").hasRole("ADMIN")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-ui/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()

                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), usuarioService))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            log.info("Security configuration completed.");
    }

    /**
     * Define un manejador personalizado para los errores de acceso denegado.
     *
     * @return un {@link AccessDeniedHandler} que devuelve una respuesta JSON con un mensaje de error.
     */
    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"No tienes permisos para acceder a este recurso. Debes ser un usuario ADMIN.\"}");
            response.getWriter().flush();
            log.warn("Access denied: {}", accessDeniedException.getMessage());
        };
    }

    /**
     * Configura el AuthenticationManagerBuilder para usar el servicio de detalles de usuario personalizado.
     * También se define un cifrador de contraseñas.
     *
     * @param auth el builder para configurar el AuthenticationManager.
     * @throws Exception si ocurre algún error durante la configuración.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
        log.info("AuthenticationManager configured with custom user details service.");
    }
}