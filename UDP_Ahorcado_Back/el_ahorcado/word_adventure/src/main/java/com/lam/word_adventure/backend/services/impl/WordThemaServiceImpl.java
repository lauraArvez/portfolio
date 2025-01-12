package com.lam.word_adventure.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.models.WordThema;
import com.lam.word_adventure.backend.repositories.WordThemaRepository;
import com.lam.word_adventure.backend.services.WordThemaService;

/**
 * clase implementación de service
 *
 * @author Laura Arvez
 */
@Service
public class WordThemaServiceImpl implements WordThemaService{
    
    @Autowired
    private WordThemaRepository wordThemaRepository;

    /**
     * constructor por defecto
     */
    public WordThemaServiceImpl() {
    }


    @Override
    public WordThema getThema(String tematica) {
        return  wordThemaRepository.findByThema(tematica);
        //wordThemaRepository.finByTemaNativeQuery(tematica);
    }
    
}
