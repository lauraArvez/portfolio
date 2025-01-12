package com.lam.word_adventure.backend.mapper;

import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.responses.ResponseUserGame;

/**
 * clase mapper
 * 
 * @author Laura Arvez
 */
public class GamesPlayedMapper {
    /**
     * constructor por defecto
     */
    private GamesPlayedMapper(){
    }

    /**
     * convierte un objeto UserModel a un objeto ResponseUserGame
     * con el patrón builder
     *
     * @param user objeto que se convertirá en ResponseUserGame
     * @return ResponseUserGame contruido a partir de UserModel
     */
    public static ResponseUserGame toResponse (UserModel user){
        return ResponseUserGame.builder()
        .username(user.getUsername())
        .gamesPlayed(user.getGamesPlayed())
        .build();
    }
}
