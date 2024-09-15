package com.example.demo.services.usuario.Impl;

import java.util.Set;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.excepciones.CustomRoleNotFoundException;
import com.example.demo.models.usuario.RoleModel;
import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.repositories.usuario.RoleRepository;
import com.example.demo.repositories.usuario.UsuarioRepository;
import com.example.demo.services.usuario.RolesService;

/**
 * Implementación del servicio para gestionar las operaciones relacionadas con los roles de los usuarios.
 * Implementa la interfaz {@link RolesService} y proporciona la funcionalidad para actualizar roles y obtener roles por nombre.
 * 
 * Utiliza los repositorios {@link RoleRepository} y {@link UsuarioRepository} para interactuar con la base de datos.
 * 
 * @author Laura
 */
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * Actualiza los roles de un usuario.
     * 
     * Asigna un nuevo conjunto de roles al usuario y guarda los cambios en la base de datos de forma transaccional.
     * Si este método es parte de una operación más grande que involucra otros cambios al usuario, 
     * la anotación @Transactional garantiza que todo se guarde de forma atómica.
     * 
     * @param usuario El usuario cuyos roles serán actualizados.
     * @param roles El nuevo conjunto de roles a asignar al usuario.
     */
    @Transactional
    @Override
    public void actualizarRolesDeUsuario(UsuarioModel usuario, Set<RoleModel> roles) {
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);  // Guardar el usuario con los nuevos roles asignados
    }


    /**
     * Obtiene un rol por su nombre.
     * 
     * Busca y devuelve un objeto RoleModel correspondiente al nombre del rol proporcionado.
     * Si el rol no se encuentra, lanza una excepción específica {@link RoleNotFoundException}.
     * 
     * @param name El nombre del rol que se desea buscar.
     * @return El rol encontrado.
     * @throws RoleNotFoundException si no se encuentra el rol con el nombre dado.
     */
    @Override
    public RoleModel obtenerRolePorName(String name) {
        RoleModel role = roleRepository.findByName(name);

        // Validación explícita para verificar si el rol es null
        if (role == null) {
            throw new CustomRoleNotFoundException("Rol con nombre '" + name + "' no encontrado");
        }

        return role;
    }

    /**
     * Elimina uno o varios roles de un usuario específico.
     * 
     * Remueve los roles especificados de la lista de roles del usuario y guarda los cambios.
     * 
     * @param usuario El usuario cuyos roles serán eliminados.
     * @param roles Los roles que serán eliminados del usuario.
     */
    @Transactional
    @Override
    public void eliminarRolesDeUsuario(UsuarioModel usuario, Set<RoleModel> roles) {
        // Remover los roles que están presentes en el conjunto "roles"
        usuario.getRoles().removeAll(roles);
        usuarioRepository.save(usuario);  // Guardar el usuario con los roles actualizados
    }
}
