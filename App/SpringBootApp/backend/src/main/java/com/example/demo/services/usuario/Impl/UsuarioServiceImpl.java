package com.example.demo.services.usuario.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.repositories.usuario.UsuarioRepository;
import com.example.demo.requests.usuario.UserUpdateAdmin;
import com.example.demo.services.usuario.PasswordService;
import com.example.demo.services.usuario.RolesService;
import com.example.demo.services.usuario.UsuarioService;

/**
 * Implementación del servicio para gestionar usuarios en el sistema.
 * Proporciona las funcionalidades CRUD y otros servicios relacionados con la gestión de usuarios.
 * Implementa la interfaz {@link UsuarioService}.
 * 
 * También delega operaciones relacionadas con roles y contraseñas a otros servicios.
 * 
 * @author Laura
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordService passwordService;
    
    @Autowired
    RolesService rolesService;


    /**
     * Obtiene todos los usuarios, tanto activos como inactivos, con paginación.
     * 
     * @param pageable Parámetro que define la paginación de resultados.
     * @return Página de usuarios.
     */
    @Transactional(readOnly = true)
    @Override
    public Page<UsuarioModel> obtenerTodosLosUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    /**
     * Obtiene todos los usuarios activos.
     * 
     * @return Lista de usuarios activos.
     */
    @Transactional(readOnly = true)
    @Override
    public List<UsuarioModel> obtenerTodosLosUsuariosActivos() {
        return usuarioRepository.findByActivoTrue();
    }

    /**
     * Busca un usuario por su nombre de usuario (username).
     * 
     * @param username Nombre de usuario.
     * @return El usuario si es encontrado, null si no.
     */
    @Transactional(readOnly = true)
    @Override
    public UsuarioModel obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsernameContainingIgnoreCase(username).orElse(null);
    }

    /**
     * Busca un usuario por su ID.
     * 
     * @param id ID del usuario.
     * @return Un Optional con el usuario si es encontrado.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<UsuarioModel> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Guarda un usuario en la base de datos.
     * 
     * @param usuario El usuario a guardar.
     * @return El usuario guardado.
     */
    @Override
    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Registra un nuevo usuario en el sistema, verificando que no exista ya un usuario con el mismo nombre de usuario.
     * También valida que la contraseña cumpla con los requisitos antes de encriptarla.
     * 
     * @param usuario El usuario a registrar.
     * @return El usuario registrado o null si ya existe uno con el mismo username.
     */
    @Transactional
    @Override
    public UsuarioModel registrarUsuario(UsuarioModel usuario) {
        usuario.setActivo(true);  // Establece el usuario como activo

        // Verificar si ya existe un usuario con el mismo nombre de usuario
        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioExistente.isPresent()) {
            return null;
        }

         // Validar la longitud de la contraseña antes de proceder
        if (usuario.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        // Encriptar la contraseña
        usuario.setPassword(passwordService.encriptarPassword(usuario.getPassword()));


        // Si no existe un usuario con el mismo nombre de usuario, procede a guardar el nuevo usuario
        return usuarioRepository.save(usuario);
    }

    /**
     * Inhabilita un usuario por su ID (cambia el estado a inactivo).
     * 
     * @param id ID del usuario a inhabilitar.
     * @return true si se inhabilitó correctamente, false en caso de error.
     */
    @Transactional
    @Override
    public boolean eliminarUsuario(Long id) {
        try {
            UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                    "El usuario con id " + id + " no existe en la base de datos"));

            // Cambiar el estado del usuario a inactivo
            usuario.setActivo(false);
            usuarioRepository.save(usuario);  // Guardar los cambios
            
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    /**
     * Actualiza el email de un usuario identificado por su username.
     * 
     * @param username Nombre de usuario del cual se actualizará el email.
     * @param email Nuevo email a asignar al usuario.
     */
    @Transactional
    @Override
    public void actualizarRegistroEmailDeUsuarioPorUsername(String username, String email) {
        UsuarioModel usuarioBd = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalStateException("Usuario con username " + username + " no encontrado"));
        
        // Verificamos que el email no esté vacío o nulo
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El campo email no puede estar vacío");
        }
        
        usuarioBd.setEmail(email);
        usuarioRepository.save(usuarioBd);
    }

    /**
     * Admin actualiza los datos de registro de un usuario.
     * 
     * @param username Nombre del usuario a actualizar.
     * @param updateAdmin Datos que el administrador desea actualizar: username, email, roles.
     */
    @Transactional
    @Override
    public void adminActualizaRegistroDeUsuario(String username, UserUpdateAdmin updateAdmin) {
        UsuarioModel usuarioBd = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalStateException("Usuario con username " + username + " no encontrado"));

        if (!updateAdmin.getUsername().isEmpty()) {
            usuarioBd.setUsername(updateAdmin.getUsername());
        }
        if (!updateAdmin.getEmail().isEmpty()) {
            usuarioBd.setEmail(updateAdmin.getEmail());
        }
        if (!updateAdmin.getRoles().isEmpty()) {
            rolesService.actualizarRolesDeUsuario(usuarioBd, updateAdmin.getRoles());
        }
        usuarioRepository.save(usuarioBd);
    }

    /**
     * Verifica si un usuario con un nombre de usuario específico existe.
     * 
     * @param username Nombre de usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    @Override
    public boolean existeUsuario(String username) {
        return usuarioRepository.findByUsername(username).isPresent();
    }

    /**
     * Cambia la contraseña de un usuario.
     * Valida la longitud de la nueva contraseña antes de encriptarla.
     * 
     * @param user Usuario al que se le cambiará la contraseña.
     * @param newPassword Nueva contraseña.
     */
    @Transactional
    @Override
    public void cambiarUsuarioPassword(UsuarioModel user, String newPassword) {
        passwordService.cambiarUsuarioPassword(user, newPassword); 
    }
}
