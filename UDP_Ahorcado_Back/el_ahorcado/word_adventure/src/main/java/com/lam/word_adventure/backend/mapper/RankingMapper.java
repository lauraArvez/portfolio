package com.lam.word_adventure.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.responses.RankingResponse;

/**
 * clase mapper Ranking, responde sin: id, password, roles de usuarios
 * @author Laura Arvez
 */
public class RankingMapper {
    
    private RankingMapper(){
    }

    /**
     * convierte un objeto UserModel a RankingResponse
     *
     * @param user objeto userModel para convertir
     * @return RankingUserResponse construido a partir de UserModel
     */
    public static RankingResponse toResponse (UserModel user){
        return RankingResponse.builder()
        .username(user.getUsername())
        .location(user.getLocation())
        .sex(user.getSex())
        .age(user.getAge())
        .totalScore(user.getTotalScore())
        .build();
    }

    /**
     * convierte una lista de userModel con puntuación a lista de ranking
     * @param userModels objeto userModels para convertir
     * @return List
     */
    public static List<RankingResponse> convertUserListToRankingResponseList(List<UserModel> userModels) {
    return userModels.stream()
        .map(RankingMapper::toResponse) // Mapear cada UserModel a un RankingResponse
        .collect(Collectors.toList()); // Recolectar los resultados en una lista
    }
}
