package com.lam.word_adventure.backend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.ScoreModel;

/**
 * interface repository de Score
 * @author Laura Arvez
 */
@Repository
public interface ScoreRepository extends JpaRepository <ScoreModel, Long> {

    /**
     * obtener los username y puntuaciones
     * @return List de usuarios con sus puntuaciones
     */
    @Query("SELECT s.user.username, s.score FROM ScoreModel s")
    List<Object[]> findAllUsernamesAndScores();
    
    /**
     * obtener user por username
     * @param username nombre de usuario a buscar
     * @return ScoreModel
     */
    ScoreModel findByUserUsername(String username);
}
