package com.laura.springboot.app.apicrud.services;

import java.util.List;
import java.util.Optional;
import com.laura.springboot.app.apicrud.entities.ProductEntity;
/**
 * Interfaz que define los métodos para realizar operaciones relacionadas con productos.
 * 
 * @author Laura Arvez
 */
public interface ProductService {
    
    /**
     * Encuentra y devuelve una lista de todos los productos.
     *
     * @return una lista de entidades de productos.
     */
    List<ProductEntity> findAll();

    /**
     * Busca un producto por su ID.
     *
     * @param id el ID del producto a buscar.
     * @return un {@link java.util.Optional} que puede contener la entidad del producto si se encuentra.
     */
    Optional<ProductEntity> findById(Long id);

    /**
     * Guarda un nuevo producto.
     *
     * @param product la entidad del producto a guardar.
     * @return la entidad del producto guardado.
     */
    ProductEntity save(ProductEntity product);
    
    /**
     * Actualiza un producto existente.
     *
     * @param id el ID del producto a actualizar.
     * @param product la entidad del producto con los datos actualizados.
     * @return un {@link java.util.Optional} que puede contener la entidad del producto actualizado si se encuentra.
     */
    Optional<ProductEntity> update(Long id, ProductEntity product);

    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar.
     * @return un {@link java.util.Optional} que puede contener la entidad del producto eliminado si se encuentra.
     */
    Optional<ProductEntity> delete(Long id);
}
