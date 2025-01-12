package com.lam.word_adventure.backend.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Clase para registrar palabras
 *
 * @author Laura Arvez
 */
@Data
@AllArgsConstructor
@Builder
public class WordRegisterRequest implements Serializable{

    
    /**
     * constructor por defecto
     */
    public WordRegisterRequest() {
    }
    
    /**
     * palabra para el registro
     */
    private String word;

    /**
     * dificultad de la palabra
     */
    private String level;

    /**
     * tematica de la palabra
     */
    private String tematica;
}
