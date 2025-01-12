package com.lam.word_adventure.backend.responses;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * clase de respuesta de ranking de jugadas
 * 
 * @author Laura Arvez
 */
@Data
@Builder
@AllArgsConstructor
public class ResponseUserGame implements Serializable {
    
    /**
     * Constructor por defecto de: ResponseUserGame
     */
    public ResponseUserGame() {
    }
    private static final long serialVersionUID = 1L;

    /**
     * nombre de usuario
     */
    private String username;

    /**
     * jugadas del usuario
     */
    private int gamesPlayed;
    
}
