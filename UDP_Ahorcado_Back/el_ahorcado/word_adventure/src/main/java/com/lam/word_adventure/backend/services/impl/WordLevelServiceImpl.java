package com.lam.word_adventure.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.models.WordLevel;
import com.lam.word_adventure.backend.repositories.WordLevelRepository;
import com.lam.word_adventure.backend.services.WordLevelService;


/**
 * clase implementación de service
 *
 * @author Laura Arvez
 */
@Service
public class WordLevelServiceImpl  implements WordLevelService{

    @Autowired
    private WordLevelRepository wordLevelRepository;

    /**
     * constructor por defecto
     */
    public WordLevelServiceImpl() {
    }

    @Override
    public WordLevel getLevel(String level) {
        return wordLevelRepository.findByLevel(level);
    }
    
}
