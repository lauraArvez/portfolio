package com.lam.word_adventure.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.WordLevel;

/**
 * interface repository de WordLevel
 *
 * @author Laura Arvez
 */
@Repository
public interface WordLevelRepository extends JpaRepository<WordLevel, Long>{
    
    /**
     * obetener por dificultad
     *
     * @param level dificultad
     * @return WordLevel
     */
    WordLevel findByLevel(String level);
}
