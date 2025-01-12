package com.lam.word_adventure.backend.services;

import java.util.List;

import com.lam.word_adventure.backend.models.ScoreModel;
import com.lam.word_adventure.backend.responses.RankingResponse;
import com.lam.word_adventure.backend.responses.ResponseScore;

/**
 * Service de ScoreModel
 * 
 * @author Laura Arvez
 */
public interface ScoreService {

    /**
     * obtiene la puntuación de un usuario específico
     *
     * @param username nombre de usuario del cual se desea obtener la puntuación
     * @return ScoreModel que es la puntuación del usuario
     */
    ScoreModel getScoreByUsername (String username);

    /**
     * obtiene todas las puntaciones
     *
     * @return lista de puntuaciones
     */
    List<ScoreModel> listgetAllScores();

    
    /**
     * formatea la lista de usuarios con sus rankings
     * @param user lista de usuario a formatear
     * @return un String que es la lista de usuarios con rankings formateados
     */
    String formatRankingUserList (List<RankingResponse> user);
    
    /**
     * formatear ranking de usuario (user:score;)
     *
     * @param rankingList lista de puntuaciones a formatear
     * @return String de las puntuaciones formateadas
     */
    String formatRankingList (List<ResponseScore> rankingList);

    /**
     * obtiene usuarios por rango de edades para listar rankings
     *
     * @param minAge edad mínima del rango
     * @param maxAge edad máxima del rango
     * @return List de los usuarios dentro del rango solicitado
     */
    //List<UserModel> getUsersByAgeRange(int minAge, int maxAge);
    List<RankingResponse> getUsersByAgeRange(int minAge, int maxAge);
    
    /**
     * obtiene usuarios por localización para listar rankings
     *
     * @param location de los usuarios
     * @return lista de los usuarios con la localización solicitada
     */
    List<RankingResponse> getUsersByLocation(String location) ;
    
}
