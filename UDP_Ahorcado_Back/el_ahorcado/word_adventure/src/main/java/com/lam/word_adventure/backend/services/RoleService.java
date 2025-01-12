package com.lam.word_adventure.backend.services;

import java.util.List;

import com.lam.word_adventure.backend.models.RoleModel;

/**
 * Service de RoleModel
 *
 * @author Laura Arvez
 */
public interface RoleService {

    /**
     * obtener rol
     * @param name nombre del rol a obtener
     * @return RoleModel
     */
    RoleModel getRole(String name) ; // de prueba

    /**
     * obtener rol por userId
     * @param userId identificador del usuario
     * @return list de roles por usuarios
     */
    List<RoleModel> getRolesByUsername(Long userId);
}
