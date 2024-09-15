package com.example.demo.services.usuario.Impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.repositories.usuario.UsuarioRepository;
import com.example.demo.services.usuario.PasswordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio que gestiona las operaciones relacionadas con las contraseñas de los usuarios.
 * Proporciona la lógica para validar y encriptar contraseñas, así como para cambiarlas.
 * 
 * Implementa la interfaz {@link PasswordService}.
 * 
 * @author Laura
 */
@Service
public class PasswordServiceImpl implements PasswordService {

    @Value ("${admin.password:admin123}")
    private String adminPassword;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    @Transactional
    public void inicializarAdmin() {
        // Verificar si el usuario 'admin' ya existe
        if (!usuarioRepository.existsByUsername("admin")) {
            UsuarioModel admin = new UsuarioModel();
            admin.setUsername("admin");
            admin.setEmail("admin@demo.com");

            // Establecer usuario como activo
            admin.setActivo(true);

            // Encripta siempre la contraseña al crear el usuario
            admin.setPassword(passwordEncoder.encode(adminPassword));

            // Guardar el usuario administrador en la base de datos
            usuarioRepository.save(admin);
        }
    }

    /**
     * Cambia la contraseña de un usuario.
     * 
     * @param usuario El usuario cuya contraseña se cambiará.
     * @param newPassword La nueva contraseña.
     * @throws IllegalArgumentException si la contraseña no cumple con la longitud mínima.
     */
    @Override
    public void cambiarUsuarioPassword(UsuarioModel usuario, String newPassword) {
        // Validar que la nueva contraseña tenga al menos 8 caracteres
        if (newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        usuario.setPassword(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
    }

    /**
     * Encripta una contraseña y valida que cumpla con los requisitos mínimos de longitud.
     * 
     * @param rawPassword La contraseña en texto plano.
     * @return La contraseña encriptada.
     * @throws IllegalArgumentException si la contraseña no cumple con los requisitos mínimos.
     */
    public String encriptarPassword(String rawPassword){ // Validar la longitud de la contraseña
    if (rawPassword == null || rawPassword.length() < 8) {
        throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
    }
    // Encriptar la contraseña
    return passwordEncoder.encode(rawPassword);
    }
}
