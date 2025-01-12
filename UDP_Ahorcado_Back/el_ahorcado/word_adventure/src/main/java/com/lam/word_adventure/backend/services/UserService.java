package com.lam.word_adventure.backend.services;

import java.util.List;

import com.lam.word_adventure.backend.models.LocationModel;
import com.lam.word_adventure.backend.models.SexModel;
import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.request.RegisterRequest;
import com.lam.word_adventure.backend.responses.UserResponse;

/**
 * Service de UserModel
 * @author Laura Arvez
 */
public interface UserService {

    
    /**
     * guarda usuario a la base de datos
     * @param registerUser el userModel que se guadará
     * @return userModel guardado en la bbdd
     */
    UserModel save (UserModel registerUser);

    /**
     * crea usuario - register basado en RegisterRequest
     * @param registerRequest  contiene la información para crear el userModel
     * @return userModel creado a partir del RegisterRequest
     */
    UserModel createUserModel(RegisterRequest registerRequest);

    /**
     * valida token
     * @param token token proporcionado
     * @return boolean true si es válido, false si no lo es
     */
    boolean isValidToken(String token);

    /**
     * verifica si existe un usuario por username
     * @param username nombre del usuario a verificar
     * @return true si existe un usuario y false si no existe
     */
    boolean existsByUsername(String username);

    /**
     * eliminar usuario por username
     * @param user nombre del usuario a eliminar
     * @return true si se elimina o false si no se elimina
     */
    boolean deleteUser (String user);
    
    /**
     * autentica a un usuario mediante un intento de inicio de sesión.
     * @param login contiene los datos para hacer login
     * @return String con el resultado del inicio de sesión
     */
    String login (UserModel login);
    
    /**
     * cierra sesión del usuario si el token es correcto
     * @param tokenAuth token de autenticación
     * @return String resultado del cierre de sesión
     */
    String logout (String tokenAuth);

    /**
     * Update de password del usuario 
     * @param username nombre del usuario
     * @param newPassword nuevo password
     * @return String resultado de la actualización
     */
    String updatePasswordChange(String username, String newPassword);

    /**
     * Obtiene usuarios por username
     * @param username nombre de usuario a buscar
     * @return String informacón del usuario encontrado
     */
    String searchByUsername(String username);

    /**
     * convierte una lista a string
     * @param userList list a convertir
     * @return StringBuilder contiene el string
     */
    StringBuilder convertListToString(List<Object> userList);

    /**
     * formatea una lista de usuarios para generar respuesta
     * @param userList lista de usuarios a formatear
     * @return String de usuarios formateada
     */
    String formatUserList(List<UserResponse> userList);

    /**
     * formatea localización de un usuario para listar
     * @param location localización 
     * @return String de localización formateada
     */
    String formatLocation(LocationModel location);

    /**
     * formatea sexo de usuario para listar
     * @param sex nombre de sexo
     * @return String de sexo formateado
     */
    String formatSex(SexModel sex);

    /**
     * Obtiene todos los usuarios
     * @return List de usuarios
     */
    List<UserModel> findAll();

    /**
     * Obtiene usuarios por sexo
     * @param sex sexo
     * @return List de usuario por sexo
     */
    List <UserModel> getUsersBySex(String sex);

    /**
     * obtiene usuarios por roles
     * @param role roles del usuario
     * @return List de usuario por roles
     */
    List<UserModel> getUsersByRole(String role);

    /**
     * obtiene usuarios por rango de edades
     *
     * @param minAge edad mínima
     * @param maxAge edad máxima
     * @return List de usuario por rango de edades
     */
    List <UserResponse> getUsersByAgeRange(int minAge, int maxAge);

    /**
     * obtiene usuarios por localización
     *
     * @param location localización
     * @return List de usuarios por localización
     */
    List <UserResponse> getUsersByLocation(String location);


    /**
     * obtiene usuario por su identificador
     *
     * @param id identificador del usuario
     * @return UserModel con el id proporcionado
     */
    UserModel getUserById(Long id);

    /**
     * obtiene el ranking de usuarios por partidas jugadas
     * @param user nombre de usuario
     * @return ranking de juego del usuario
     */
    Integer getGameRanking(String user);

  }