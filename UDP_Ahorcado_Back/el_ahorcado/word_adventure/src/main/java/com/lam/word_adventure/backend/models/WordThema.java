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
 * Clase de temática de palagras
 *
 * @author Laura Arvez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="word_thema")
public class WordThema {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tema", unique = true)
    private String thema;

//    @OneToMany(mappedBy = "thema")
    @OneToMany(mappedBy = "thema", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <Word> words;

    /**
     * crea una nueva temática
     *
     * @param thema temática de la palabra
     */
    public WordThema(String thema) {
        this.thema = thema;
    }
}
