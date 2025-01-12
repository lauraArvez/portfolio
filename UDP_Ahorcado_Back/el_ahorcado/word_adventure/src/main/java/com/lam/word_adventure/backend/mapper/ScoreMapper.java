package com.lam.word_adventure.backend.mapper;

import com.lam.word_adventure.backend.models.ScoreModel;
import com.lam.word_adventure.backend.responses.ResponseScore;

/**
 * clase mapper
 * 
 * @author Laura Arvez
 */
public class ScoreMapper {
    
    /**
     * contructor por defecto de ScoreMapper
     */
    private ScoreMapper(){
    }

    /**
     * convierte un objeto ScoreModel a un objeto ResponseScore
     * con el patrón builder
     *
     * @param score objeto que se convertirá en ResponseScore
     * @return ResponseScore contruido a partir de ScoreModel
     */
    public static ResponseScore toResponse (ScoreModel score){
        return ResponseScore.builder()
        .user(score.getUser())
        .score(score.getScore())
        .build();
    }
    
}
