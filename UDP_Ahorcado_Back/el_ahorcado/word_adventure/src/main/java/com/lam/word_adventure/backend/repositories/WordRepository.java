package com.lam.word_adventure.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.Word;


/**
 * interface repository de Word
 *
 * @author Laura Arvez
 */
@Repository
public interface WordRepository extends JpaRepository<Word, Long>{

    /**
     * busca por palabra
     * @param word palabra a buscar
     * @return Word 
     */
    @Query(value = "SELECT * FROM words WHERE palabra = :palabra", nativeQuery = true)
    Word findByWord(@Param("palabra")String word);

    /**
     * busca por dificultad
     * @param level dificultad
     * @return List de palabraspor dificultad
     */
    @Query("SELECT w FROM Word w WHERE w.level.level = :level")
    List<Word> findByLevel(@Param ("level")String level);

    /**
     * busca por temática
     * @param thema temática de la palabra
     * @return List de palabras por temática
     */
    @Query("SELECT w FROM Word w WHERE w.thema.thema = :thema")
    List<Word> findByThema(@Param ("thema")String thema);

    /**
     * consulta de palabra aleatoria con la dificultad y la temática especificada en la petición
     * @param levelId identificador de la dificultad
     * @param temaId identificador de la temática
     * @return String palabra aleatoria
     */
    @Query(value =
        "SELECT palabra FROM words WHERE level_id = :levelId AND tema_id = :temaId ORDER BY RAND() LIMIT 1",
        nativeQuery = true)
    String findRandomWord(Long levelId, Long temaId);

    /**
     * consulta, para obtener todas las palabras
     * @return List todas las palabras
     */
    @Query("SELECT w FROM Word w JOIN FETCH w.level JOIN FETCH w.thema")
    List<Word> findAllWithDetails();

    /**
     * lista todas las palabras
     * EntityGraph: personalizando la carga de entidades,
     * evita consultas adicionales
     */
    
    @EntityGraph(attributePaths = {"level", "thema"})
    List<Word> findAll ();
}
