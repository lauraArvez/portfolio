package com.example.demo.models.usuario;

import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa a los usuarios en el sistema.
 * Contiene atributos como nombre de usuario, email, contraseña, roles, datos personales,
 * y el estado de activación del usuario.
 * 
 * Esta clase utiliza anotaciones de JPA para definir su mapeo a la base de datos,
 * y anotaciones de validación para asegurar la integridad de los datos del usuario.
 * 
 * @author Laura
 */
@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioModel {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario único y obligatorio.
     */
    @Column(unique = true, nullable = false)
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;
    
    /**
     * Dirección de correo electrónico del usuario. Debe ser un email válido.
     */
    @Column(nullable = false)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo electrónico es incorrecto")
    private String email;
    
    /**
     * Contraseña del usuario, debe tener al menos 8 caracteres.
     */
    @Column(nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    

    /**
     * Roles asignados al usuario. Se mapea en una relación de muchos a muchos.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID") })
    private Set<RoleModel> roles;
    
    
    /**
     * Estado del usuario (activo o inactivo).
     * El usuario está activo por defecto.
     */
    @Column(nullable = false)
    private boolean activo = true;

    
    /**
     * Constructor que permite crear un usuario con su nombre de usuario, contraseña y opcionalmente email.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param email    Dirección de correo electrónico opcional.
     */
    public UsuarioModel(String username, String password, Optional<String> email) {
        super();
        this.username = username;
        if (email.isPresent()) {
            this.email = email.get();
        }
        this.password = password;
    }

    /**
     * Constructor que permite crear un usuario con roles predefinidos.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param email    Dirección de correo electrónico.
     * @param roles    Conjunto de roles del usuario.
     */
    public UsuarioModel(String username, String password, String email, Set<RoleModel> roles) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}