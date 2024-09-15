package com.example.demo.models.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Clase que representa los roles en el sistema.
 * Cada usuario puede tener uno o varios roles, los cuales determinan los permisos y accesos dentro del sistema.
 * Esta clase implementa {@link Serializable} para permitir que los objetos de esta clase se puedan serializar.
 * 
 * Utiliza las anotaciones de Lombok {@link Data} y {@link Builder} para generar automáticamente los métodos
 * de acceso (getters y setters), el patrón builder.
 * 
 * Anotaciones de JPA como {@link Entity} y {@link Table} son utilizadas para definir la persistencia en la base de datos.
 * 
 * @author Laura
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del rol. Este campo es la clave primaria y se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Nombre del rol, como "ADMIN" o "USER", que define los permisos asociados al rol.
     */
    @Column
    private String name;

}
