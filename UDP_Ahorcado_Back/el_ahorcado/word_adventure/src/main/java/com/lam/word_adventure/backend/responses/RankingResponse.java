package com.lam.word_adventure.backend.responses;

import java.io.Serializable;

import com.lam.word_adventure.backend.models.LocationModel;
import com.lam.word_adventure.backend.models.SexModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * clase respuestas de ranking de usuario
 * 
 * @author Laura
 */
@Data
@Builder
@AllArgsConstructor
public class RankingResponse implements Serializable {
    
    /**
     * constructor por defecto de RankingResponse
     */
    public RankingResponse() {
    }
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
     * total de puntación del usuario para la respuesta
     */
    private int totalScore;
}
