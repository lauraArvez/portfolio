package com.example.demo.mapper.usuarioMapper;

import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.responses.usuarioResponses.UserUpdateResponse;


/**
 * Clase utilitaria para mapear objetos de tipo UsuarioModel a objetos de tipo UserUpdateResponse.
 * Esta clase proporciona métodos estáticos para convertir los datos del usuario en respuestas específicas para actualizaciones.
 * 
 * El constructor está privado para evitar la creación de instancias, ya que esta clase es utilitaria.
 * 
 * @author Laura
 */
public class UsuariosUpdateMapper {

    /**
     * Constructor privado para evitar la instanciación de la clase utilitaria.
     */
    private UsuariosUpdateMapper() {
    }

    /**
     * Convierte un objeto de tipo UsuarioModel en un objeto de tipo UserUpdateResponse.
     * 
     * El objeto UserUpdateResponse solo contiene el nombre de usuario (username) y el correo electrónico (email),
     * que son los campos más relevantes para actualizar los datos de un usuario.
     * 
     * @param user El objeto UsuarioModel que contiene la información del usuario.
     * @return Un objeto UserUpdateResponse con los campos username y email.
     */
    public static UserUpdateResponse toResponse(UsuarioModel user) {
        return UserUpdateResponse.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .build();
    }
}