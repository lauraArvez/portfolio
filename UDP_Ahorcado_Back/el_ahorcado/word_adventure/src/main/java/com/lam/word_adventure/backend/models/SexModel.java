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
import lombok.NoArgsConstructor;

/**
 * Clase de sexo. masculino / femenino / no-binario
 *
 * @author Laura Arvez
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="sex")
public class SexModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String sex;

    /**
     * constructor para crear un objeto sexModel
     * 
     * @param sex el sexo a asignar al objeto
     */
    public SexModel(String sex) {
        this.sex = sex;
    }

}
