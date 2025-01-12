package com.lam.word_adventure.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lam.word_adventure.backend.models.RoleModel;

/**
 * interface repository de Role
 * 
 * @author Laura Arvez
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long>{
    
    //Optional<RoleModel> findByRole(String role);
    //List <RoleModel> findByRole(String role); //de prueba
    /**
     * buscar por rol
     * @param role nombre del rol
     * @return RoleModel
     */
    RoleModel findByRole(String role);
    /**
     * buscar rol por id
     * @param id identificador del rol
     * @return RoleModel
     */
    RoleModel getRoleById(long id);
    //List <RoleModel> findRolesByUsers_Id(Long userId);

    /**
     * buscar roles por user id
     * @param userId identificador del usuario
     * @return List de roles por usuarios
     */
    @Query("SELECT r FROM RoleModel r JOIN r.users u WHERE u.id = :userId")
    List<RoleModel> findRolesByUserId(@Param("userId") Long userId);
    
    
}

