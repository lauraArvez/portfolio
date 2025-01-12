package com.lam.word_adventure.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.WordThema;

/**
 * interface repository de WordThema
 *
 * @author Laura Arvez
 */
@Repository
public interface WordThemaRepository extends JpaRepository <WordThema, Long>{
    
    /**
     * Query para obtener por temática
     *
     * @param tematica nombre de la temática
     * @return WordThema
     */
    @Query(value = "SELECT * FROM word_thema WHERE tema = :tema", nativeQuery = true)
    WordThema finByTemaNativeQuery(@Param ("tema") String tematica);

    /**
     * buscar por temática
     *
     * @param thema temática
     * @return WordThema
     */
    WordThema findByThema(String thema);

}
