package com.lam.word_adventure.backend.services;

import java.util.List;
import java.util.Optional;

import com.lam.word_adventure.backend.models.Word;
import com.lam.word_adventure.backend.request.WordRegisterRequest;
import com.lam.word_adventure.backend.responses.WordResponse;

/**
 * Service de Word
 *
 * @author Laura Arvez
 */
public interface WordService {

    /**
     * obtiene todas las palabras
     *
     * @return List
     */
    List<Word> getAllWords();

    /**
     * obtiene palabra por id
     *
     * @param id identificador de la palabra
     * @return Optional
     */
    Optional<Word> getWordById(Long id);
    
    /**
     * registro de palabra
     *
     * @param word objeto de palabra
     * @return word
     */
    Word registerWord(Word word);

    /**
     * crear palabras
     *
     * @param wordRegister objeto para registrar palabras
     * @return word
     */
    Word createWord(WordRegisterRequest wordRegister);
    
    /**
     * eliminar palabra por id
     *
     * @param id identificador de la palabra
     */
    void deleteWordById(Long id);

    /**
     * formato de listado de palabras
     *
     * @param wordList listado a formatear
     * @return String
     */
    String formatWordList (List<WordResponse> wordList);

    /**
     * petición de palabra para empezar el juego
     *
     * @param level dificultad de la palabra
     * @param thema temática de la palabra
     * @return palabra de String para app móvil son acentos
     */
    String requestWordToPlay(String level, String thema);

    /**
     * petición de palabra para empezar el juego en escritorio
     *
     * @param level dificultad de la palabra
     * @param thema temática de la palabra
     * @return palabra de tipo string para app de escritorio con acentos
     */
    String requestWordForDesktop(String level, String thema);
    
    /**
     * obtiene palabra por dificultad
     *
     * @param level dificultad de palabras
     * @return List de palabras por dificultad
     */
    List<WordResponse> getWordsByLevel(String level);

    /**
     * obtiene palabra por temática
     *
     * @param thema nombre de la temática
     * @return List de palabras por temática
     */
    List<WordResponse> getWordsByThema(String thema);

    //List<WordResponse>getWordsDiacritics(String filter);

    /**
     * elimina palabra
     *
     * @param word palabra a eliminar
     * @return boolean
     */
    boolean deleteWord(String word);

    
    
}
