package com.example.demo.mapper.usuarioMapper;
import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.responses.usuarioResponses.UserResponse;
import com.example.demo.responses.usuarioResponses.UserUpdateResponse;

/**
 * Clase utilitaria para mapear objetos de tipo UsuarioModel a objetos de respuesta.
 * Esta clase está diseñada para convertir un UsuarioModel a diferentes tipos de respuesta como UserResponse o UserUpdateResponse.
 * Utiliza métodos estáticos para realizar las conversiones.
 * 
 * La clase está diseñada con un constructor privado para evitar su instanciación.
 * 
 * @author Laura
 */
public class UserMapper {
    
    /**
     * Constructor privado para evitar la instanciación de la clase.
     * Esta clase es utilitaria y no necesita ser instanciada.
     */
    private UserMapper() {
    }

    /**
     * Convierte un objeto de tipo UsuarioModel en un objeto de tipo UserResponse.
     * El objeto UserResponse solo contiene el nombre de usuario (username) y el correo electrónico (email) del UsuarioModel.
     * 
     * @param user Objeto de UsuarioModel que contiene la información del usuario.
     * @return Un objeto UserResponse con los campos username y email.
     */
    public static UserResponse toResponse(UsuarioModel user) {
        return UserResponse.builder()
            .user(user.getUsername())
            .email(user.getEmail())
            .build();
    }

    /**
     * Convierte un objeto de tipo UsuarioModel en un objeto de tipo UserUpdateResponse.
     * El objeto UserUpdateResponse se usa para actualizaciones y contiene el nombre de usuario (username) y el correo electrónico (email).
     * 
     * @param user Objeto de UsuarioModel que contiene la información del usuario.
     * @return Un objeto UserUpdateResponse con los campos username y email.
     */
    public static UserUpdateResponse toResponseUpdate (UsuarioModel user){
        return UserUpdateResponse.builder()
        .username(user.getUsername())
        .email(user.getEmail())
        .build();
    }
}
