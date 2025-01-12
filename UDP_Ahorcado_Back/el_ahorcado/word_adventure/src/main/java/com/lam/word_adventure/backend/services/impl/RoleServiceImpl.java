package com.lam.word_adventure.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.models.RoleModel;
import com.lam.word_adventure.backend.repositories.RoleRepository;
import com.lam.word_adventure.backend.services.RoleService;
/**
 * clase implementación de service
 * 
 * @author Laura Arvez
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    
    /**
     * constructor por defecto de RoleServiceImpl
     */
    public RoleServiceImpl() {
    }

    @Override
    public RoleModel getRole(String name) { // de prueba
        return roleRepository.findByRole(name);
    }

    @Override
    public List<RoleModel> getRolesByUsername(Long userId) {
        return roleRepository.findRolesByUserId(userId);
    }

    
}
