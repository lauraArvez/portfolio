package com.lam.word_adventure.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.models.LocationModel;
import com.lam.word_adventure.backend.repositories.LocationRepository;
import com.lam.word_adventure.backend.services.LocationService;

/**
 * clase implementación de service
 *
 * @author Laura Arvez
 */
@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationRepository locationRepository;
	
	/**
	 * constructor por defecto
	 */
	public LocationServiceImpl() {
	}



	@Override
	public LocationModel getLocationModel(String location) {
		return locationRepository.findByLocation(location);
	}
    
}
