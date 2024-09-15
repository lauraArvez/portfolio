package com.example.demo.services.usuario;

import com.example.demo.models.usuario.UsuarioModel;
/**
 * Interfaz para el servicio de gestión de contraseñas de usuarios.
 * Define las operaciones relacionadas con la actualización y cambio de contraseñas.
 * 
 * @author Laura
 */
public interface PasswordService {

    /**
     * Cambia la contraseña de un usuario.
     * 
     * @param usuario El usuario cuya contraseña será actualizada.
     * @param newPassword La nueva contraseña que se asignará al usuario.
     */
    public void cambiarUsuarioPassword(UsuarioModel usuario, String newPassword);
    
    /**
     * Encripta una contraseña sin guardarla.
     * 
     * @param rawPassword La contraseña sin encriptar.
     * @return La contraseña encriptada.
     */
    public String encriptarPassword(String rawPassword);

}
