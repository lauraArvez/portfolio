package com.lam.word_adventure.backend.mapper;

import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.responses.UserResponse;

/**
 * clase mapper usuario
 * 
 * @author Laura Arvez
 */
public class UserMapper {
    /**
     * Constructor por defecto
     */
    private UserMapper() {
    }

    /**
     * convierte un objeto UserModel a un objeto UserResponse
     * con el patrón builder
     *
     * @param user objeto que se convertirá en UserResponse
     * @return UserResponse contruido a partir de UserModel
     */
    public static UserResponse toResponse (UserModel user){
        return UserResponse.builder()
            .username(user.getUsername())
            .location(user.getLocation())
            .sex(user.getSex())
            .age(user.getAge())
            .roles(user.getRoles())
            .build();
    }
}
