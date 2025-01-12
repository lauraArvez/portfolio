package com.lam.word_adventure.backend.responses;
import java.io.Serializable;

import com.lam.word_adventure.backend.models.WordLevel;
import com.lam.word_adventure.backend.models.WordThema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Clase de respuesta de word
 *
 * @author Laura Arvez
 */
@Data
@AllArgsConstructor
@Builder
public class WordResponse  implements Serializable{

    /**
     * contructor por defecto
     */
    public WordResponse() {
    }

    /**
     * palabra asociada a la respuesta
     */
    private String word;

    /**
     * dificultada asociada a la palabra
     */

    private WordLevel level;

    /**
     * tematica de la palabra
     */

    private WordThema thema;
}
