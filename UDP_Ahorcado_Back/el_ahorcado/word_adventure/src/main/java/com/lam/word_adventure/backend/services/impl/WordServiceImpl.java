package com.lam.word_adventure.backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.UDP.utils.StringUtils;
import com.lam.word_adventure.backend.exceptions.WordServiceException;
import com.lam.word_adventure.backend.mapper.WordMapper;
import com.lam.word_adventure.backend.models.Word;
import com.lam.word_adventure.backend.models.WordLevel;
import com.lam.word_adventure.backend.models.WordThema;
import com.lam.word_adventure.backend.repositories.WordRepository;
import com.lam.word_adventure.backend.request.WordRegisterRequest;
import com.lam.word_adventure.backend.responses.WordResponse;
import com.lam.word_adventure.backend.services.WordLevelService;
import com.lam.word_adventure.backend.services.WordService;
import com.lam.word_adventure.backend.services.WordThemaService;

import jakarta.transaction.Transactional;

/**
 * clase implementación de service
 * @author Laura Arvez
 */
@Service
public class WordServiceImpl implements WordService{

    private static final Logger logger = LoggerFactory.getLogger(WordService.class);

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private WordLevelService wordLevelService;

    @Autowired
    private WordThemaService wordThemaService;

    /**
     * constructor por defecto
     */
    public WordServiceImpl() {
    }

    @Override
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    @Override
    public Optional<Word> getWordById(Long id) {
        return wordRepository.findById(id);
    }

    @Override
    public Word createWord(WordRegisterRequest wordRegister) {
        
        WordLevel levelWord = wordLevelService.getLevel(wordRegister.getLevel());
        if(levelWord == null){
            throw new WordServiceException("La dificultad "+ wordRegister.getLevel()+" no existe en la base de datos");
        }

        WordThema themaWord =  wordThemaService.getThema(wordRegister.getTematica());
        if(themaWord == null){
            throw new WordServiceException("La temática "+ wordRegister.getTematica()+" no existe en la base de datos");
        }

        return registerWord(new Word(
            wordRegister.getWord(),
            levelWord,
            themaWord));
    }
    
    @Override
    public Word registerWord(Word word) {
        Word wordExits = wordRepository.findByWord(word.getWord());
        if(wordExits != null && wordExits.getWord().equals(word.getWord())){
            //throw new IllegalArgumentException("La palabra: "+word+"ya existe en la base de datos.");
            throw new WordServiceException.WordAlreadyExistsException ("La palabra: "+word+" ya existe en la base de datos.");
        }

        try{
            return wordRepository.save(word);
        }catch(Exception e){
            //throw new RuntimeException("Error al registrar la palabra: "+word.getWord(), e);
            throw new WordServiceException("Error al registrar la palabra: "+word.getWord(), e);
        }
    }

    @Override
    public String formatWordList (List<WordResponse> wordList){
        StringBuilder wordListString = new StringBuilder();

        for (WordResponse word : wordList) {
            wordListString.append(formatWord(word)).append(";");
        }

        // Eliminar el último ':' si hay usuarios en la lista
        if (wordList.size() > 0) {
            wordListString.deleteCharAt(wordListString.length() - 1);
        }
    
        return wordListString.toString();
    }

    private String formatWord(WordResponse wordR) {
        StringBuilder wordString = new StringBuilder();

        // Agregar cada campo de palabra separado por ':'
        wordString.append(wordR.getWord()).append(":");
        wordString.append(formatLevel(wordR.getLevel())).append(":");
        wordString.append(formatThema(wordR.getThema()));

        return wordString.toString();
    }

    private String formatLevel(WordLevel level) {
        return (level != null) ? level.getLevel()  :"";
    }
    private String formatThema(WordThema thema) {
        return (thema != null) ? thema.getThema() : "";
    }

    @Override
    public List<WordResponse> getWordsByLevel(String level) {
        try{
            //obtener la dificultad desde wordLevel
            WordLevel getLevel = wordLevelService.getLevel(level);

            if(getLevel == null){
                throw new WordServiceException.DifficultyNotFoundException("Dificultad no encontrada: " + level);
            }

            //obtener dificultad
            List <Word> words = wordRepository.findByLevel(getLevel.getLevel());
            //mapear respuestas
            List <WordResponse> wordList = words.stream()
                                                .map(WordMapper::toResponse)
                                                .collect(Collectors.toList());
            return wordList;
        } catch (Exception e){
            logger.error("Error al buscar palabras por dificultad: " + e.getMessage(), e);
            throw new WordServiceException("Dificultad no encontrada: "+level, e);
            //return List.of();

        }
    }

    @Override
    public List<WordResponse> getWordsByThema(String thema) {
        try{
            //obtener temática desde wordThema
            WordThema getThema = wordThemaService.getThema(thema);

            if(getThema ==null){
                throw new WordServiceException.ThemeNotFoundException("Temática no encontrada: " + thema);
            }

            //obtener temática
            List <Word> words = wordRepository.findByThema(getThema.getThema());

            //mapear respuestas
            List <WordResponse> wordList = words.stream()
                                                .map(WordMapper::toResponse)
                                                .collect(Collectors.toList());
            return wordList;

        } catch (Exception e){
          //  logger.error(thema, e);
            throw new WordServiceException("Temática no encontrada: " + thema, e);
            //return List.of();
        }
    }

    @Override
    @Transactional
    public boolean deleteWord(String word) {
        logger.debug("Iniciando deleteWord para la palabra: {}", word);
        try {
            Word wordExist= wordRepository.findByWord(word);
            if(wordExist != null){
                wordExist.setLevel(null);
                wordExist.setThema(null);

                wordRepository.delete(wordExist);
                logger.debug("Palabra eliminada correctamente: {}", word);

                return true; // Devuelve true si la palabra fue eliminada correctamente
            } else {
                logger.debug("La palabra no existe en la base de datos: {}", word);
                return false;
            }
        } catch (Exception e) {
            // Captura cualquier excepción no controlada y registra el error
            logger.error("Error al eliminar la palabra: " + word, e);
            return false; // Devuelve false en caso de error
        }
    }

    @Override
    public String requestWordToPlay(String level, String thema) {
        try {
            //buscar si existe la dificultad solicitada
            WordLevel levelExist = wordLevelService.getLevel(level);
            if (levelExist == null){
                throw new WordServiceException.DifficultyNotFoundException(
                    "Esta dificultad no existe en la base de datos: "+level);
            }

            //buscar si existe la temática solicitada
            WordThema themaExist = wordThemaService.getThema(thema);
            if (themaExist == null){
                throw new WordServiceException.ThemeNotFoundException(
                    "Esta temática no existe en la base de datos: "+thema);
            }

            //si exite level y thema obtener la palabra aleatoria
            String wordRandom = wordRepository.findRandomWord(levelExist.getId(), themaExist.getId());
            if(wordRandom == null){
                throw new WordServiceException.WordNotFoundException(
                    "No se pudo obtener la palabra");
            }
            // normalizar palabra aleatoria
            wordRandom = StringUtils.normalizeAndRemoveDiacritics(wordRandom);
            System.err.println(wordRandom);

            return "REQUESTWORD,OK,"+ wordRandom;
        } catch (WordServiceException we){
            we.printStackTrace();
            return "REQUESTWORD,KO,"+ we.getMessage();
        } catch (Exception e){
            e.printStackTrace();
            return "REQUESTWORD,KO,Error al procesar la solicitud";
        }
    }

    @Override
    public String requestWordForDesktop(String level, String thema){
        try {
            //buscar si existe la dificultad solicitada
            WordLevel levelExist = wordLevelService.getLevel(level);
            if (levelExist == null){
                throw new WordServiceException.DifficultyNotFoundException(
                    "Esta dificultad no existe en la base de datos: "+level);
            }

            //buscar si existe la temática solicitada
            WordThema themaExist = wordThemaService.getThema(thema);
            if (themaExist == null){
                throw new WordServiceException.ThemeNotFoundException(
                    "Esta temática no existe en la base de datos: "+thema);
            }

            //si exite level y thema obtener la palabra aleatoria
            String wordRandom = wordRepository.findRandomWord(levelExist.getId(), themaExist.getId());
            if(wordRandom == null){
                throw new WordServiceException.WordNotFoundException(
                    "No se puedo obtener la palabra");
            }
            System.err.println(wordRandom);

            return "REQUESTWORDDESK,OK,"+ wordRandom;
        } catch (WordServiceException we){
            we.printStackTrace();
            return "REQUESTWORDDESK,KO,"+ we.getMessage();
        } catch (Exception e){
            e.printStackTrace();
            return "REQUESTWORDDESK,KO,Error al procesar la solicitud";
        }
    }
    public void deleteWordById(Long id) {
        wordRepository.deleteById(id);
    }
}
