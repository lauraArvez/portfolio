package com.lam.word_adventure.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.SexModel;
import com.lam.word_adventure.backend.models.UserModel;

import jakarta.transaction.Transactional;

/**
 * Clase repositorio de userModel
 *
 * @author Laura Arvez
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

    /**
     * Consulta usuarios por roles
     * @param username nombre del usuario
     * @return Optional puede contener el usuario encontrado
     */
    @Query("SELECT u FROM UserModel u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<UserModel> findByUsernameWithRoles(@Param("username") String username);
    
    
    /**
     * recuperar todos los usuarios junto con sus ubicaciones asociadas en una sola consulta SQL
     *
     * @return List de usuario por localización
     */
    @Query("SELECT u FROM UserModel u JOIN FETCH u.location")
    List<UserModel> findAllWithLocation();
    
    /**
     * recupera lista de usuarios por localización
     * @param location localización
     * @return List de usuario con la localización proporcionada
     */
    @Query("SELECT u FROM UserModel u WHERE u.location.location = :location")
    List<UserModel> findByLocation(@Param ("location")String location);

    /**
     * obtiene roles por nombre
     * @param roleName nombre del rol
     * @return List de usuario con el rol proporcionado
     */
    List<UserModel> findAllByRolesRole (String roleName);

    /**
     * obtiene usuarios por sexo ordenadopor edad
     *
     * @param sex sexo
     * @return List de usuarios con sexo proporcionado, ordenados por edad
     */
    List<UserModel> findAllBySexOrderByAge(SexModel sex);

    /**
     * obtiene usuarios por sexo
     *
     * @param sex nombre del sexo
     * @return List de usuarios por sexo
     */
    List<UserModel> findBySex(SexModel sex);

    /**
     * busca si existe username para anotación personalizada
     *
     * @param username nombre del usuario
     * @return true si existe el usuario proporcionado
     */
    boolean existsByUsername(String username);

    /**
     * obtiene usuarios por username
     *
     * @param username nombre del usuario
     * @return Optional puede contener el usuario encontrado
     */
    Optional <UserModel> findByUsername(String username);

    /**
     * obtiene usuarios por username ignoreCase
     *
     * @param username nombre de usuario
     * @return UserModel encontrado
     */
    UserModel findByUsernameIgnoreCase(String username);

    /**
     * elimina usuario por username
     *
     * @param username nombre de usuario
     * @return true si se eliminó, false si no
     */
    boolean deleteByUsername(String username);

    /**
     * obtiene usuarios por edad con rangos
     *
     * @param minAge edad mínmia
     * @param maxAge edad máxima
     * @return List de usuario dentro del rango solicitado
     */
    List<UserModel> findByAgeBetween(int minAge, int maxAge);

    /**
     * elimina usuario por username utilizando una consulta personalizada
     *
     * @param username nombre de usuario
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM UserModel u WHERE u.username = :username")
    void deleteByUsernameQuery(String username);

    /**
     * Obtiene el ranking de un usuario en función del número de partidas jugadas.
     *
     * @param username nombre del usuario
     * @return ranking del usuario basado en las partidas jugadas
     */
    @Query("SELECT u.gamesPlayed FROM UserModel u WHERE u.username = :username")
    Integer findRankingByGamesPlayed(@Param ("username") String username);
    
}
