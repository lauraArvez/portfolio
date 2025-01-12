package com.lam.word_adventure.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.mapper.RankingMapper;
import com.lam.word_adventure.backend.models.LocationModel;
import com.lam.word_adventure.backend.models.ScoreModel;
import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.repositories.ScoreRepository;
import com.lam.word_adventure.backend.repositories.UserRepository;
import com.lam.word_adventure.backend.responses.RankingResponse;
import com.lam.word_adventure.backend.responses.ResponseScore;
import com.lam.word_adventure.backend.services.LocationService;
import com.lam.word_adventure.backend.services.ScoreService;
import com.lam.word_adventure.backend.services.UserService;

/**
 * clase implementación de service
 * 
 * @author Laura Arvez
 */
@Service
public class ScoreServiceImpl implements ScoreService{

    
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;
    
    
    /**
     * constructor por defecto de ScoreServiceImpl
     */
    public ScoreServiceImpl() {
    }

    @Override
    public ScoreModel getScoreByUsername(String username) {
        return scoreRepository.findByUserUsername(username);
    }

    @Override
    public List<ScoreModel> listgetAllScores() {
        return scoreRepository.findAll();
    }


    //::::::::::::::::::::::::::::::::  LIST (USER + SCORE)  :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public String formatRankingList(List<ResponseScore> rankingList) {
            return rankingList.stream()
                    .map(this::formatResponseScore)
                    .collect(Collectors.joining(";"));
    }

    /**
     * formatea user + score ("usuario:puntuación")
     *
     * @param responseScore el objeto ResponseScore a formatear
     * @return un String  formateado con el nombre de usuario y la puntuación separados por ":"
     */
    private String formatResponseScore(ResponseScore responseScore) {
        return responseScore.getUser()
        + ":" + responseScore.getScore();
    }
    
    //::::::::::::::::::::::: FILTERED  ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public String formatRankingUserList(List<RankingResponse> rankingResponses) {
        StringBuilder userListString = new StringBuilder();

        for (RankingResponse user : rankingResponses) {
            userListString.append(formatRanking(user)).append(";");
        }

        // Eliminar el último ':' si hay usuarios en la lista
        if (rankingResponses.size() > 0) {
            userListString.deleteCharAt(userListString.length() - 1);
        }

        return userListString.toString();
        
    }

    /**
     * formatea usuarios para listar rankings
     *
     * @param user el objeto RankingResponse a formatear
     * @return un String formateado que representa el ranking del usuario
     */
    private String formatRanking(RankingResponse user) {
        StringBuilder userString = new StringBuilder();

        // Agregar cada campo del usuario separado por ':'
        userString.append(user.getUsername()).append(":");
        userString.append(userService.formatLocation(user.getLocation())).append(":");
        userString.append(user.getAge()).append(":");
        userString.append(userService.formatSex(user.getSex())).append(":");
        userString.append(user.getTotalScore());
        return userString.toString();
    }


    @Override
    public List<RankingResponse> getUsersByAgeRange(int minAge, int maxAge) {
        try {
            // obtener usuarios por rango
            List<UserModel> users = userRepository.findByAgeBetween(minAge, maxAge);

            // transformar y ordenar usuarios por puntuación total
            List<RankingResponse> userList = users.stream()
                    .map(RankingMapper::toResponse)
                    .collect(Collectors.toList());
            return userList;
        } catch (Exception e) {
            System.err.println("Error al buscar usuarios por rango de edad " + e.getMessage());
            return List.of(); // devuelve una lista vacía en caso de error
        }
    }

    @Override
    public List<RankingResponse> getUsersByLocation(String location) {
        try {
            // obtener la localización desde location
            LocationModel getLocation = locationService.getLocationModel(location);

            if (getLocation == null) {
                throw new IllegalArgumentException("Localización no encontrada: " + location);
            }

            // obtener location
            List<UserModel> users = userRepository.findByLocation(getLocation.getLocation());

            // mapear respuestas
            List<RankingResponse> userList = users.stream()
                    .map(RankingMapper::toResponse)
                    .collect(Collectors.toList());

            return userList;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Localización no válida: " + location);
        } catch (Exception e) {
            System.err.println("Error buscando usuario con esa localización: " + e.getMessage());
            return List.of(); // devuelve una lista vacía en caso de error
        }
    }
}