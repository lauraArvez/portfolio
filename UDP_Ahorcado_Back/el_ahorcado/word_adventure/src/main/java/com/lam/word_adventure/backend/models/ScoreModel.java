package com.lam.word_adventure.backend.models;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * clase de puntuaciones
 *
 * @author Laura Arvez
 */

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "scores")
public class ScoreModel {

    /**
     * constructor por defecto
     */
    public ScoreModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // autogenerado

    @Column(nullable = false) 
    @Builder.Default // permite establecer un valor por defecto
    private int score = 0;  // valor por defecto

    private LocalDateTime date;
        
    @ManyToOne //(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    /**
     * construir String en el formato de respuesta (user:score;)
     *
     * @param scores puntuaciones
     * @return String
     */
    public static String buildScoreListString(List<ScoreModel> scores){
        return scores.stream()
                .map(score -> score.getUser().getUsername() + ":" + score.getScore())
                .collect(Collectors.joining(";"));
    }

}
