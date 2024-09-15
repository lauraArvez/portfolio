package com.example.demo.mapper.usuarioMapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.models.usuario.RoleModel;
import com.example.demo.models.usuario.UsuarioModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que mapea un UsuarioModel a un objeto UserDetails utilizado por Spring Security.
 * Proporciona los métodos para convertir los datos del usuario y sus roles en el formato esperado por Spring Security.
 * 
 * @author Laura
 */
public class UserDetailsMapper {

    /**
     * Construye un objeto UserDetails a partir de un UsuarioModel.
     * 
     * @param user El UsuarioModel que contiene la información del usuario.
     * @return Un objeto UserDetails con el nombre de usuario, la contraseña y las autoridades.
     */
    public static UserDetails build(UsuarioModel user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(), 
                getAuthorities(user)
        );
    }

    /**
     * Convierte los roles del usuario en autoridades de seguridad utilizadas por Spring Security.
     * Cada rol del usuario se convierte en una autoridad con el prefijo "ROLE_".
     * 
     * @param retrievedUser El UsuarioModel que contiene los roles del usuario.
     * @return Un conjunto de autoridades que representa los roles del usuario en el sistema de seguridad.
     */
    private static Set<? extends GrantedAuthority> getAuthorities(UsuarioModel retrievedUser) {
        Set<RoleModel> roles = retrievedUser.getRoles();

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Convierte cada rol del usuario en una autoridad de seguridad
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

        return authorities;
    }
}