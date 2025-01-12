package com.lam.word_adventure.backend.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase de dificulta de palagras
 *
 * @author Laura Arvez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="word_level")
public class WordLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dificultad", unique = true)
    private String level;

    //@OneToMany(mappedBy = "level")
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Word> words;

    

    /**
     * crea nueva dificultad de palabra
     * @param level nombre de la dificultad
     */
    public WordLevel(String level) {
    }
}
