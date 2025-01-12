package com.lam.word_adventure.backend.responses;
import java.io.Serializable;

import com.lam.word_adventure.backend.models.UserModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * clase de respuesta de Score para un usuario
 * 
 * @author Laura Arvez
 */
@Data
@AllArgsConstructor
@Builder
public class ResponseScore implements Serializable{

    /**
     * Constructor por defecto de: ResponseScore
     */
    public ResponseScore() {
    }
    
    /**
     * usuario model
     */
    private UserModel user;

    /**
     * puntuacion del usuario
     */
    private int score;
    
}
