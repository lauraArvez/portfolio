package com.example.demo.services.usuario;

import java.util.Set;

import com.example.demo.models.usuario.RoleModel;
import com.example.demo.models.usuario.UsuarioModel;

/**
 * Interfaz para el servicio que gestiona las operaciones relacionadas con los roles de los usuarios.
 * Proporciona funcionalidades para actualizar los roles de un usuario y obtener roles por su nombre.
 * 
 * Esta interfaz sigue el Principio de Responsabilidad Única (SRP), centrando su funcionalidad en la gestión de roles.
 * 
 * @author Laura
 */
public interface RolesService {

    /**
     * Actualiza los roles de un usuario.
     * 
     * @param usuario El usuario cuyos roles serán actualizados.
     * @param roles El conjunto de roles que se asignarán al usuario.
     */
    public void actualizarRolesDeUsuario(UsuarioModel usuario, Set<RoleModel> roles);
    
    /**
     * Obtiene un rol por su nombre.
     * 
     * @param name El nombre del rol que se busca.
     * @return El objeto RoleModel correspondiente al nombre dado, o null si no se encuentra.
     */
    public RoleModel obtenerRolePorName(String name);

    /**
     * Elimina uno o varios roles de un usuario específico.
     * 
     * @param usuario El usuario cuyos roles serán eliminados.
     * @param roles Los roles que serán eliminados del usuario.
     */
    public void eliminarRolesDeUsuario(UsuarioModel usuario, Set<RoleModel> roles);
}
