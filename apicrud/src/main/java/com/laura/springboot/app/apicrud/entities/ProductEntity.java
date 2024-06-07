package com.laura.springboot.app.apicrud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Clase entidad que representa un producto en el sistema.
 * 
 * <p>Esta clase está mapeada a la tabla "products" en la base de datos. Cada instancia de {@code ProductEntity} 
 * representa una fila en esa tabla.
 * 
 * <p>Campos:
 * <ul>
 *   <li>{@code id} - El identificador único del producto. Es la clave primaria y se genera automáticamente.
 *   <li>{@code name} - El nombre del producto.
 *   <li>{@code price} - El precio del producto.
 *   <li>{@code description} - Una breve descripción del producto.
 * </ul>
 * @see javax.persistence.Entity
 * @see javax.persistence.Table
 * @see javax.persistence.Id
 * @see javax.persistence.GeneratedValue
 * @see javax.persistence.GenerationType
 */
@Entity
@Table(name = "products")
public class ProductEntity {
    
    /**
     * Identificador único para el producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nombre del producto.
     * Debe tener entre 3 y 20 caracteres.
     */
    @NotEmpty(message = "{NotEmpty.product.name}")
    @Size(min = 3, max = 20)
    private String name;
    
    /**
     * Precio del producto.
     * Debe ser al menos 0.
     */
    @Min(value = 0, message = "{Min.product.price}")
    @NotNull(message = "{NotNull.product.price}")
    private Float price;
    
    /**
     * Descripción del producto.
     * No puede estar en blanco.
     */
    @NotBlank(message = "{NotBlank.product.description}")
    private String description;
    
    /**
     * Obtiene el ID del producto.
     * 
     * @return el ID del producto
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Establece el ID del producto.
     * 
     * @param id el ID del producto
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene el nombre del producto.
     * 
     * @return el nombre del producto
     */
    public String getName() {
        return name;
    }
    
    /**
     * Establece el nombre del producto.
     * 
     * @param name el nombre del producto
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Obtiene el precio del producto.
     * 
     * @return el precio del producto
     */
    public Float getPrice() {
        return price;
    }
    
    /**
     * Establece el precio del producto.
     * 
     * @param price el precio del producto
     */
    public void setPrice(Float price) {
        this.price = price;
    }
    
    /**
     * Obtiene la descripción del producto.
     * 
     * @return la descripción del producto
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Establece la descripción del producto.
     * 
     * @param description la descripción del producto
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
