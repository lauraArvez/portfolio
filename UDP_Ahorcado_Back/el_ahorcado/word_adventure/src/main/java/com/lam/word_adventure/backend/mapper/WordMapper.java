package com.lam.word_adventure.backend.mapper;

import com.lam.word_adventure.backend.models.Word;
import com.lam.word_adventure.backend.responses.WordResponse;

/**
 * clase WordMapper
 * @author Laura Arvez
 */
public class WordMapper {
    
    /**
     * constructor por defecto
     */
    private WordMapper(){
    }
        
    /**
     * convierte un objeto Word a un objeto WordResponse
     * con el patrón builder
     *
     * @param word objeto que se convertirá en WordResponse
     * @return WordResponse contruido a partir de Word
     */
    public static WordResponse toResponse (Word word){
        return WordResponse.builder()
        .word(word.getWord())
        .level(word.getLevel())
        .thema(word.getThema())
        .build();
    }
}
