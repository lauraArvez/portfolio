package com.example.demo.security;

import com.example.demo.mapper.usuarioMapper.UserDetailsMapper;
import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.services.usuario.UsuarioService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.TokenProvider;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Filtro de autorización JWT que extiende BasicAuthenticationFilter.
 * Se encarga de validar tokens JWT y autenticar solicitudes.
 */

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UsuarioService usuarioService;

    /**
     * Constructor que acepta un AuthenticationManager y un UsuarioService.
     *
     * @param authManager el AuthenticationManager a usar
     * @param usuarioService el UsuarioService a usar
     */
    public JwtAuthorizationFilter(AuthenticationManager authManager, UsuarioService usuarioService) {
        super(authManager);
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZATION_KEY);

        if (StringUtils.isEmpty(authorizationHeader) || !authorizationHeader
                .startsWith(Constants.TOKEN_BEARER_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        final String token = authorizationHeader.replace(Constants.TOKEN_BEARER_PREFIX + " ", "");
        log.info("Token received: {}", token);

        String userName = TokenProvider.getUserName(token);
        UsuarioModel user = usuarioService.obtenerUsuarioPorUsername(userName);
        if (user == null) {
            log.warn("User not found for token: {}", token);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        UserDetails userDetails = UserDetailsMapper.build(user);

        UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, userDetails);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        log.info("User {} authenticated and set to security context", userName);
    }
}