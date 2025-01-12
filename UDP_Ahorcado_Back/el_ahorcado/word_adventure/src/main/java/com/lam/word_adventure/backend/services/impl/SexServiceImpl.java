package com.lam.word_adventure.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.models.SexModel;
import com.lam.word_adventure.backend.repositories.SexRepository;
import com.lam.word_adventure.backend.services.SexService;

/**
 * clase implementación de service
 * 
 * @author Laura Arvez
 */
@Service
public class SexServiceImpl implements SexService{

    @Autowired
    SexRepository sexRepository;

	/**
	 * constructor por defecto
	 */
	public SexServiceImpl() {
	}

	@Override
	public SexModel getSex(String sex) {
		return sexRepository.findBySex(sex);
	}
    
}
