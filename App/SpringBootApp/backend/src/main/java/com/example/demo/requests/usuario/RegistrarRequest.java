package com.example.demo.requests.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Clase que representa una solicitud de registro de usuario.
 * Contiene los datos necesarios para registrar un nuevo usuario.
 */
@Builder
@Data
@AllArgsConstructor
public class RegistrarRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Nombre de usuario.
     * No puede estar vacío.
     */
    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    private String username;

    /**
     * Contraseña del usuario.
     * No puede estar vacía.
     */
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    /**
     * Correo electrónico del usuario.
     * No puede estar vacío y debe tener un formato válido.
     */
    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no tiene un formato válido")
    private String email;

    /**
     * Lista de roles del usuario.
     * Puede estar vacía.
     */
    private List<String> roles;

    /**
     * Constructor por defecto.
     */
    public RegistrarRequest() {
    }
}