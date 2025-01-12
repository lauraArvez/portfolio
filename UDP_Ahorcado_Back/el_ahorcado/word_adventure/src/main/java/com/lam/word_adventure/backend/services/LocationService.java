package com.lam.word_adventure.backend.services;

import com.lam.word_adventure.backend.models.LocationModel;

/**
 * Service de location
 *
 * @author Laura Arvez
 */
public interface LocationService {
    
    /**
     * obtener por localización
     * @param location nombre de la comunidad autónmoma como localización
     * @return LocationModel
     */
    LocationModel getLocationModel(String location);
    
    
}
