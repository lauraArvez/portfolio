package com.lam.word_adventure.backend.services;

import com.lam.word_adventure.backend.models.WordLevel;

/**
 * Service de Word level.
 *
 * @author Laura Arvez
 */
public interface WordLevelService {
    
    /**
     * obtener dificultad
     *
     * @param level ndificultad
     * @return WordLevel
     */
    WordLevel getLevel (String level);
}
