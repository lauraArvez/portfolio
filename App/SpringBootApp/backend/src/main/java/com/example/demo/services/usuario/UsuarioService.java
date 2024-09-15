package com.example.demo.services.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.requests.usuario.UserUpdateAdmin;

/**
 * Interfaz para el servicio que gestiona las operaciones relacionadas con los usuarios.
 * Proporciona métodos para crear, leer, actualizar y eliminar usuarios, 
 * así como otras operaciones específicas, como cambiar contraseñas o actualizar roles.
 * 
 * @author Laura
 */
public interface UsuarioService {

    /**
     * Verifica si un usuario con un nombre de usuario específico existe.
     * 
     * @param username El nombre de usuario a verificar.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean existeUsuario(String username);

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param usuario El objeto UsuarioModel que contiene los datos del nuevo usuario.
     * @return El objeto UsuarioModel del usuario registrado, o null si el nombre de usuario ya existe.
     */
    public UsuarioModel registrarUsuario(UsuarioModel usuario);

    /**
     * Obtiene todos los usuarios que están activos en el sistema.
     * 
     * @return Una lista de usuarios activos.
     */
    public List<UsuarioModel> obtenerTodosLosUsuariosActivos();

    /**
     * Obtiene todos los usuarios del sistema, tanto activos como inactivos, con paginación.
     * 
     * @param pageable Parámetros de paginación.
     * @return Una página de usuarios.
     */
    public Page<UsuarioModel> obtenerTodosLosUsuarios(Pageable pageable);

    /**
     * Busca un usuario por su ID.
     * 
     * @param id El ID del usuario.
     * @return Un Optional que contiene el usuario si es encontrado, o vacío en caso contrario.
     */
    public Optional<UsuarioModel> obtenerUsuarioPorId(Long id);

    /**
     * Busca un usuario por su nombre de usuario (username).
     * 
     * @param username El nombre de usuario a buscar.
     * @return El objeto UsuarioModel si el usuario es encontrado, o null en caso contrario.
     */
    public UsuarioModel obtenerUsuarioPorUsername(String username);

    /**
     * Cambia la contraseña de un usuario.
     * 
     * @param user El usuario al que se le cambiará la contraseña.
     * @param newPassword La nueva contraseña a asignar al usuario.
     */
    public void cambiarUsuarioPassword(UsuarioModel user, String newPassword);

    /**
     * Actualiza el email de un usuario, identificado por su nombre de usuario.
     * 
     * @param username El nombre de usuario del cual se actualizará el email.
     * @param email El nuevo email a asignar.
     */
    public void actualizarRegistroEmailDeUsuarioPorUsername(String username, String email);

    /**
     * Administra la actualización de los datos de un usuario, como el nombre de usuario, email y roles.
     * 
     * @param username El nombre de usuario del cual se actualizarán los datos.
     * @param updateAdmin Objeto que contiene los datos que se desean actualizar.
     */
    public void adminActualizaRegistroDeUsuario(String username, UserUpdateAdmin updateAdmin);

    /**
     * Guarda un usuario en la base de datos.
     * 
     * @param usuario El objeto UsuarioModel a guardar.
     * @return El objeto UsuarioModel guardado.
     */
    public UsuarioModel guardarUsuario(UsuarioModel usuario);

    /**
     * Inhabilita (o elimina lógicamente) un usuario por su ID, cambiando su estado a inactivo.
     * 
     * @param id El ID del usuario a inhabilitar.
     * @return true si el usuario fue inhabilitado correctamente, false en caso contrario.
     */
    public boolean eliminarUsuario(Long id);
}
