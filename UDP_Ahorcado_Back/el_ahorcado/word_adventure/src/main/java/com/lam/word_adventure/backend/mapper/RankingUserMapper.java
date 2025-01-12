package com.lam.word_adventure.backend.mapper;

import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.responses.RankingUserResponse;

/**
 * clase mapper ranking usuario
 *
 * @author Laura Arvez
 */
public class RankingUserMapper {
    /**
     * constructor por defecto
     */
    private RankingUserMapper(){
    }

    /**
     * convierte un objeto UserModel a RankingResponse
     *
     * @param user objeto userModel para convertir
     * @return RankingUserResponse construido a partir de UserModel
     */
    public static RankingUserResponse toResponse (UserModel user){
        return RankingUserResponse.builder()
            .username(user.getUsername())
            .location(user.getLocation())
            .sex(user.getSex())
            .age(user.getAge())
            .scores(user.getScores())
        .build();
    }
}
