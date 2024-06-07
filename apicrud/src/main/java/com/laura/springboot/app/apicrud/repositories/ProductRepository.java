package com.laura.springboot.app.apicrud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.laura.springboot.app.apicrud.entities.ProductEntity;

/**
 * Interfaz de repositorio para la entidad ProductEntity.
 * Esta interfaz extiende {@link org.springframework.data.repository.CrudRepository} para obtener funcionalidades CRUD básicas.
 * Está marcada con la anotación {@link org.springframework.stereotype.Repository}, lo que la identifica como un componente de repositorio de Spring.
 *
 * @param <ProductEntity> el tipo de la entidad gestionada por este repositorio.
 * @param <Long> el tipo de datos del identificador de la entidad.
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    
}
