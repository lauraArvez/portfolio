package com.laura.springboot.app.apicrud.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laura.springboot.app.apicrud.entities.ProductEntity;
import com.laura.springboot.app.apicrud.repositories.ProductRepository;
import com.laura.springboot.app.apicrud.services.ProductService;

/**
 * Implementación del servicio para manejar la lógica de negocio relacionada con los objetos {@code ProductEntity}.
 * 
 * <p>Esta clase proporciona la implementación de los métodos definidos en la interfaz {@code ProductService}.
 * Utiliza un repositorio para realizar operaciones de persistencia.
 * </p>
 * 
 * @see ProductService
 * @see ProductRepository
 * @see ProductEntity
 * 
 * @author Laura Arvez
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    /**
     * Obtiene todas las instancias de {@code ProductEntity}.
     * 
     * <p>Este método es de solo lectura y está anotado con {@code @Transactional(readOnly = true)},
     * lo que significa que no realizará ninguna operación de escritura en la base de datos.
     * Utiliza el repositorio para realizar la búsqueda.
     * 
     * @return una lista de todas las entidades.
     */
    @Transactional(readOnly = true)
    @Override
    public List<ProductEntity> findAll() {
        return (List<ProductEntity>) repository.findAll();
    }

    /**
     * Busca una instancia de {@code ProductEntity} por su identificador.
     * 
     * <p>Este método es de solo lectura y está anotado con {@code @Transactional(readOnly = true)},
     * lo que significa que no realizará ninguna operación de escritura en la base de datos.
     * Utiliza el repositorio para realizar la búsqueda.
     * 
     * @param id el identificador de la entidad a buscar.
     * @return un {@link Optional} que contiene la entidad si se encuentra, o 
     * {@link Optional#empty()} si no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProductEntity> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Guarda una instancia de {@code ProductEntity}.
     * 
     * <p>Este método persiste la entidad proporcionada en la base de datos utilizando el repositorio.
     * Si la entidad ya existe, se actualiza; de lo contrario, se crea una nueva instancia en la base de datos.
     * </p>
     * 
     * @param product la entidad {@code ProductEntity} a guardar.
     * @return la entidad guardada, incluida cualquier información generada por la base de datos (como el ID).
     * @see ProductEntity
     * @see ProductRepository
     */
    @Override
    @Transactional
    public ProductEntity save(ProductEntity product) {
        return repository.save(product);
    }

    /**
     * Actualiza una instancia de {@code ProductEntity} con los nuevos valores proporcionados.
     * 
     * <p>Este método busca la entidad por su identificador. Si la entidad existe, sus campos se actualizan con los valores 
     * del objeto proporcionado y la entidad se guarda en la base de datos. Si la entidad no existe, se retorna un 
     * {@link Optional} vacío.
     * </p>
     * 
     * @param id el identificador de la entidad a actualizar.
     * @param product el objeto {@code ProductEntity} que contiene los nuevos valores.
     * @return un {@link Optional} que contiene la entidad actualizada si se encuentra y actualiza, o
     * {@link Optional#empty()} si no se encuentra.
     * @see ProductEntity
     * @see ProductRepository
     */
    @Override
    @Transactional
    public Optional<ProductEntity> update(Long id, ProductEntity product) {
        Optional<ProductEntity> productOptional = repository.findById(id);
        if (productOptional.isPresent()) {
            ProductEntity productDb = productOptional.orElseThrow();
            
            productDb.setName(product.getName());
            productDb.setDescription(product.getDescription());
            productDb.setPrice(product.getPrice());
            return Optional.of(repository.save(productDb)); // convierte el objeto producto a optional
            
        }
        return productOptional;
    }

    /**
     * Elimina una instancia de {@code ProductEntity} por su identificador.
     * 
     * <p>Este método busca la entidad por su identificador. Si la entidad existe, se elimina de la base de datos. 
     * Si la entidad no existe, se retorna un {@link Optional} vacío.
     * </p>
     * 
     * @param id el identificador de la entidad a eliminar.
     * @return un {@link Optional} que contiene la entidad eliminada si se encuentra y elimina, o
     * {@link Optional#empty()} si no se encuentra.
     * @see ProductEntity
     * @see ProductRepository
     */
    @Transactional
    @Override
    public Optional<ProductEntity> delete(Long id) {
        Optional<ProductEntity> productOptional = repository.findById(id);
        productOptional.ifPresent(productDb -> {
            repository.delete(productDb);
        });
        return productOptional;
    }


}
