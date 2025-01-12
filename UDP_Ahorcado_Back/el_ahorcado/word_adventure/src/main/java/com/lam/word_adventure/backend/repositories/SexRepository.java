package com.lam.word_adventure.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.SexModel;

/**
 * interface repository de sexModel
 * 
 * @author Laura Arvez
 */
@Repository
public interface SexRepository extends JpaRepository <SexModel, Long>{
    
    /**
     * obtener por sexo
     * @param sex nombre del sexo a buscar
     * @return el sexo tipo SexModel
     */
    SexModel findBySex(String sex);
}
