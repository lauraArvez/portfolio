package com.example.demo.repositories.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.models.usuario.RoleModel;

/**
 * Repositorio JPA para gestionar operaciones CRUD en la entidad {@link RoleModel}.
 * Proporciona métodos para buscar roles por su identificador o nombre.
 * 
 * Este repositorio se utiliza para interactuar con la base de datos y acceder a los roles definidos en el sistema.
 * 
 * @author Laura
 */
@Component
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

   /**
     * Busca un rol por su identificador único (ID).
     * 
     * @param id El identificador del rol.
     * @return El rol que corresponde al identificador proporcionado.
     */
    RoleModel getRoleById(long id);

    /**
     * Busca un rol por su nombre.
     * 
     * @param name El nombre del rol.
     * @return El rol que corresponde al nombre proporcionado.
     */
    RoleModel findByName(String name);
}