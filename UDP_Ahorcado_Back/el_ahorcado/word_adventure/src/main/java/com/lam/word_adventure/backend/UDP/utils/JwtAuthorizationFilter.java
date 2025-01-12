/*package com.lam.word_adventure.backend.UDP.utils;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.lam.word_adventure.backend.mapper.UserDetailsMapper;
import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.repositories.UserRepository;
import com.lam.word_adventure.backend.services.UserService;
public class JwtAuthorizationFilter  extends BasicAuthenticationFilter{

    private UserService userService;
    private UserRepository userRepository;

    public JwtAuthorizationFilter (AuthenticationManager authManager, UserService userService) {
            super(authManager);
            this.userService = userService;
    }
    
    /**
     * En este método independiente, processJwtToken, simplemente se procesa el token JWT pasado como parámetro.
     * Se realiza la misma lógica de validación y autenticación que en el método doFilterInternal,
     * pero sin depender de objetos específicos de Servlet.
     * Esto te permitirá reutilizar esta funcionalidad en cualquier parte de la
     * aplicación donde se necesite manejar tokens JWT.
     * @param token
     */
   /* public void processJwtToken(String token) {
    if (StringUtils.hasLength(token)) {
        // Token vacío, no se puede realizar la autenticación
        return;
    }

    // Extraer nombre de usuario del token
    String username = TokenProvider.getUserName(token);
    
    // Buscar el usuario en la base de datos
    Optional<UserModel> user =  userRepository.findByUsername(username);
    //userService.searchByUsername(username);
    if (user.isEmpty()) {
        // Usuario no encontrado, devolver error de autenticación
        return;
    }
    
    // Construir UserDetails a partir del usuario encontrado
    UserDetails userDetails = UserDetailsMapper.build(user.get());

    // Crear token de autenticación
    UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, userDetails);
    
    // Establecer la autenticación en el contexto de seguridad
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
}
}
*/