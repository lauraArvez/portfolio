package com.example.demo.models.usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Entidad que representa un token en la base de datos.
 * Se utiliza para almacenar tokens de autenticación o verificación de usuarios.
 * 
 * Utiliza las anotaciones de Lombok para generar automáticamente los métodos de acceso, constructores y el patrón Builder.
 * 
 * @author Laura
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class TokenModel {

    /**
     * Constructor por defecto de la clase TokenModel.
     * Es necesario para que JPA pueda crear instancias de la entidad.
     */
    public TokenModel() {

    }

    /**
     * Identificador único del token en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * El valor del token. Puede ser un token de autenticación, verificación u otro tipo.
     */
    @Column
    private String token;
}
