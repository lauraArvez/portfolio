package com.lam.word_adventure.backend.services;

import com.lam.word_adventure.backend.models.SexModel;

/**
 * Service de SexModel
 * @author Laura Arvez
 */
public interface SexService {

    /**
     * obtener sexo
     * @param sex nombre del sexo a obtener
     * @return SexModel
     */
    SexModel getSex (String sex);
}
