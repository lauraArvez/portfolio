package com.lam.word_adventure.backend.responses;

import java.io.Serializable;
import java.util.List;

import com.lam.word_adventure.backend.models.LocationModel;
import com.lam.word_adventure.backend.models.ScoreModel;
import com.lam.word_adventure.backend.models.SexModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * clase respuesta del ranking del usuario
 * 
 * @author Laura Arvez
 */
@Data
@Builder
@AllArgsConstructor
public class RankingUserResponse implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * nombre de usuario
     */
    private String username;
    
    /**
     * localización de tipo LocationModel
     */
    private LocationModel location;

    /**
     * edad del usuario para la respuesta
     */
    private int age;
    /**
     * sexo del usuario para la respuesta
     */
    private SexModel sex;

    /**
     * puntuaciones del usuario para la respuesta
     */
    private List<ScoreModel> scores;

    /**
     * constructor por defecto de RankingUserResponse
     */
    public RankingUserResponse() {
    }
}
