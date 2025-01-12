package com.lam.word_adventure.backend.services;

import com.lam.word_adventure.backend.models.WordThema;

/**
 * Service de WordThema
 * @author Laura Arvez
 */
public interface WordThemaService {

    /**
     * obtener temática
     * @param tematica nombre de tematica
     * @return WordThema
     */
    WordThema getThema (String tematica);
    
    
}
