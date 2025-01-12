package com.lam.word_adventure.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de palagras
 *
 * @author Laura Arvez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // autogenerado

    @Column(name = "palabra", unique = true)
    @NotBlank
    private String word;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id")
    private WordLevel level ;
    
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "tema_id")
    private WordThema thema ;


    /**
     * crea un nuevo objeto Word con la palabra + dificultad + temática
     *
     * @param string palabra (no nula ni vacía).
     * @param levelWord dificultad de la palabra
     * @param themaWord temática al que se asocia
     */
    public Word(@NotBlank String string, WordLevel levelWord, WordThema themaWord) {
        this.word = string;
        this.level = levelWord;
        this.thema = themaWord;
    }

    /**
     * crea un nuevo objeto Word
     *
     * @param word palabra (no nula ni vacía)
     */
    public Word(@NotBlank String word) {
        this.word = word;
    }
}



