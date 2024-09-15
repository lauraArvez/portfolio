package com.example.demo.services.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.usuarioMapper.UserDetailsMapper;
import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.services.usuario.UsuarioService;

/**
 * Servicio para gestionar la autenticación de usuarios en el sistema.
 * Implementa la interfaz {@link UserDetailsService} de Spring Security para cargar los detalles de un usuario por su nombre de usuario (username).
 * Se encarga de interactuar con el servicio de usuarios {@link UsuarioService} y mapear los datos a un objeto {@link UserDetails}.
 * 
 * @author Laura
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Carga un usuario por su nombre de usuario (username) y lo convierte en un objeto {@link UserDetails}
     * para que pueda ser gestionado por el sistema de autenticación de Spring Security.
     * 
     * @param userName El nombre de usuario del usuario a cargar.
     * @return Un objeto {@link UserDetails} que contiene la información del usuario para la autenticación.
     * @throws UsernameNotFoundException Si el usuario no se encuentra en la base de datos.
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Obtenemos el usuario
        UsuarioModel user = usuarioService.obtenerUsuarioPorUsername(userName);
        
         // Si el usuario no existe, lanzamos una excepción
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        // Convertimos UsuarioModel a UserDetails
        return UserDetailsMapper.build(user);
    }
}