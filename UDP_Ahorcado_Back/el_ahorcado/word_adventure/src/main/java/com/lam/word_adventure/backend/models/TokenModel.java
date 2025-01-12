package com.lam.word_adventure.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * clase de token
 *
 * @author Laura Arvez
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenModel {

    /**
     * constructor por defecto de TokenModel
     */
    public TokenModel() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String token;
}
