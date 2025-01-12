package com.lam.word_adventure.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.LocationModel;

/**
 * interface repository de location
 *
 * @author Laura Arvez
 */
@Repository
public interface LocationRepository extends JpaRepository <LocationModel, Long>{

    /**
     * buscar por localización
     * @param location nombre de la comunidad autónoma
     * @return locationModel
     */
    LocationModel findByLocation(String location);

    
}
