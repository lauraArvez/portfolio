package com.lam.word_adventure.backend.server;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lam.word_adventure.backend.UDP.utils.StringUtils;
import com.lam.word_adventure.backend.UDP.utils.TokenProvider;
import com.lam.word_adventure.backend.mapper.RankingMapper;
import com.lam.word_adventure.backend.models.ScoreModel;
import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.repositories.UserRepository;
import com.lam.word_adventure.backend.responses.RankingResponse;
import com.lam.word_adventure.backend.services.ScoreService;
import com.lam.word_adventure.backend.services.UserService;

/**
 * clase controller de Puntuaciones - Rankings
 * @author Laura Arvez
 */
@Component
public class UdpGameController {
    
    String payload;
    int score;
    String username;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Constructor por defecto de UdpGameController
     */
    public UdpGameController() {
    }

    /**
     * Actualiza la puntuación de un usuario y sus partidas jugadas según la solicitud del cliente.
     *
    * @param msgCli msgCli Arreglo de strings que contiene la información de la solicitud del cliente.
    *              Se espera que msgCli tenga al menos tres elementos:
    *              - msgCli[0]: comando de la solicitud
    *              - msgCli[1]: token de autenticación
    *              - msgCli[2]: puntuación a actualizar (debe ser un número entero)
    * @param tokenRequest Token de autenticación enviado por el cliente.
    * @return Un mensaje indicando el resultado de la operación (éxito o error).
    */
    @Transactional
    public String updateScore (String[] msgCli, String tokenRequest){
        try{
            username = TokenProvider.getUserName(tokenRequest);
            if(username == null){
                payload = "SENDSCORE,KO,Token no válido";
            }
            
            // usuario de tipo model para enviar el id
            UserModel user = userRepository.findByUsernameIgnoreCase(username);
            if(user == null){
                return "SENDSCORE,KO,Usuario no encontrado";
            }

            score = Integer.parseInt(msgCli[2].trim());  // convertir el valor a un entero
                
            // gardar nueva puntuación y actualizar partidas jugadas
            user.updateScoreGame(score);
            userRepository.save(user);

            payload = "SENDSCORE,OK";
        } catch (NumberFormatException e) {
            payload = "SENDSCORE,KO,Error: valor de la puntuación no es válida";
        }catch (Exception e){
            payload = "SENDSCORE,KO,Error inesperado" + e.getMessage();
        }
        return payload;
    }

    /**
     * listado de todos los usuarios con sus respectivos rankings sin el rol
     * @return String
     */
    public String handleListRankingUser(){
        try{
            //obtener la lista de usuarios sin el password
            List<RankingResponse> userList = userService.findAll().stream()
            .map(RankingMapper::toResponse)
            .collect(Collectors.toList());

            //ordenar userlist por total de puntuaciones (de mayor a menor)
            userList.sort(Comparator.comparingInt(RankingResponse::getTotalScore).reversed());

            //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
            String responseList = scoreService.formatRankingUserList(userList);
            payload = "LISTRANKING,OK,"+responseList;

            
        } catch (Exception e) {
            //cualquier excepción
            String errorMessage = "Error ocurrido en el proceso de listar usuarios: " + e.getMessage();
            payload = "LISTRANKING,KO," + errorMessage;
        }
        return payload;
    }

    /**
     * listado de usuario + ranking sin otros campos
     * @return String
     */
    public String handleListRanking (){
        try{
            // obtener la lista de puntuaciones
            List<ScoreModel> scoreList = scoreService.listgetAllScores();
            if(scoreList.isEmpty()){
                payload ="RANKINGUSER,KO,No se ha encontrado puntuaciones para listar";
            } else {
                // ordenar puntuaciones
                List<ScoreModel> scores = scoreList.stream()
                .sorted(Comparator.comparingInt(ScoreModel::getScore).reversed())
                .collect(Collectors.toList());

                String rankingString = ScoreModel.buildScoreListString(scores);
                payload = "RANKINGUSER,OK," + rankingString;
            }
            
        } catch (Exception e) {
            //cualquier excepción
            String errorMessage = "Error ocurrido en el proceso de listar rankings: " + e.getMessage();
            payload = "RANKINGUSER,KO," + errorMessage;
        }
        return payload;
    }

    /**
     * listado de rankings de usuarios por filtros (sexo, localización, edad:rango de edades, rankings)
     * @param msgCli msgCli Arreglo de strings que contiene la información de la solicitud del cliente.
 *              Se espera que msgCli tenga al menos tres elementos:
 *              - msgCli[0]: comando de la solicitud
 *              - msgCli[1]: token de autenticación
 *              - msgCli[2]: filtro por el que listar
     * @return String
     */
    public String handleListRankingFiltered (String [] msgCli){
        String filterType = StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim());
        String dataFilter= StringUtils.normalizeAndRemoveDiacritics(msgCli[3].trim());
        
        try{
            List<RankingResponse> userList;
            String responseList;
            payload ="";
            switch (filterType) {
                case "Sexo":
                    if (dataFilter.equalsIgnoreCase("Masculino") || dataFilter.equalsIgnoreCase("Femenino")
                                || dataFilter.equalsIgnoreCase("No-Binario")){
                        userList = userService.getUsersBySex(dataFilter).stream()
                        .map(RankingMapper::toResponse)
                        .collect(Collectors.toList());

                        //ordenar userlist por total de puntuaciones (de mayor a menor)
                        userList.sort(Comparator.comparingInt(RankingResponse::getTotalScore).reversed());

                        if (!userList.isEmpty()){
                            //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                            responseList =   scoreService.formatRankingUserList(userList);
                            payload = "LISTRANKINGFILTERED,OK," + responseList.toString();
                        } else {
                            payload = "LISTRANKINGFILTERED,KO,No se ha encontrado puntuación de partidas para el sexo especificado: " + dataFilter;
                        }
                    } else {
                        payload = "LISTRANKINGFILTERED,KO,Filtro de sexo no válido";
                    }
                    break;
                
                case "Edad":
                    String [] ageRanges = dataFilter.split("-");
                    if(ageRanges.length !=2){
                        payload = "LISTRANKINGFILTERED,KO,Formato de rango de edad no válido";
                    }else{
                        int minAge = Integer.parseInt(ageRanges[0]);
                        int maxAge = Integer.parseInt(ageRanges[1]);

                        userList = scoreService.getUsersByAgeRange(minAge,maxAge);
                        
                        if(userList != null && !userList.isEmpty()){
                                //ordenar userlist por total de puntuaciones (de mayor a menor)
                            userList.sort(Comparator.comparingInt(RankingResponse::getTotalScore).reversed());

                            //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                            responseList = scoreService.formatRankingUserList(userList);
                            payload = "LISTRANKINGFILTERED,OK," + responseList.toString();
                        }else{
                            payload = "LISTRANKINGFILTERED,OK,No se han encontrado usuarios con puntuación dentro del rango de edad especificado";
                        }
                    }
                    break;

                case "Localizacion":
                    userList = scoreService.getUsersByLocation(dataFilter);
                    if(!userList.isEmpty()){
                            //ordenar userlist por total de puntuaciones (de mayor a menor)
                        userList.sort(Comparator.comparingInt(RankingResponse::getTotalScore).reversed());

                        //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                        responseList = scoreService.formatRankingUserList(userList);
                        payload = "LISTRANKINGFILTERED,OK," + responseList.toString();
                    }else{
                        payload = "LISTRANKINGFILTERED,KO,No se han encontrado usuarios para este filtro";
                    }
                    break;
                default:
                    payload = "LISTRANKINGFILTERED,KO,Tipo de filtro no válido: "+filterType;
                    break;
            }
            return payload;
        } catch (NumberFormatException e) {
            payload = "LISTRANKINGFILTERED,KO,Edad no válido" + e.getMessage();
        } catch(IllegalArgumentException e){
            payload = "LISTRANKINGFILTERED,KO,Filtro de sexo no válido:"+ e.getMessage();
        } catch (Exception e) {
            payload = "LISTRANKINGFILTERED,KO," + e.getMessage();
        }
        return payload;

    }
}
