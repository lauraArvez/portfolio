package com.laura.springboot.app.apicrud.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laura.springboot.app.apicrud.ProductValidation;
import com.laura.springboot.app.apicrud.entities.ProductEntity;
import com.laura.springboot.app.apicrud.services.ProductService;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar productos.
 *
 * @author Laura Arvez
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    @Autowired
    private ProductService service;

    @Autowired
    private ProductValidation validation;

    /**
     * Lista todos los productos.
     *
     * @return una lista de entidades de productos.
     */
    @GetMapping
    public List<ProductEntity> list() {
        return service.findAll();
    }
    
    /**
     * Obtiene un producto por su ID.
     *
     * @param id el ID del producto.
     * @return la entidad del producto si se encuentra, o una respuesta 404 si no.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<ProductEntity> productOptional = service.findById(id);
        if (productOptional.isPresent()) {
            // Obtiene el objeto product
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        // Devuelve un 404
        return ResponseEntity.notFound().build();
    }
    
    /**
     * Crea un nuevo producto.
     *
     * @param product la entidad del producto a crear.
     * @param result el resultado de la validación del producto.
     * @return la entidad del producto creado con un estado 201, o un mapa de errores de validación con un estado 400.
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductEntity product, BindingResult result) {
        // validación personalizada
        validation.validate(product, result);
        if (result.hasFieldErrors()){
            return validation(result); // devuelve el map con los mensajes de error
        }
        // Devuelve el objeto creado
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id el ID del producto a actualizar.
     * @param product la entidad del producto con los datos actualizados.
     * @param result el resultado de la validación del producto.
     * @return la entidad del producto actualizado con un estado 201 si se actualiza correctamente, 
     *         o una respuesta 404 si el producto no se encuentra, o un mapa de errores de validación con un estado 400.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductEntity product, BindingResult result, @PathVariable Long id) {
        validation.validate(product, result);
        if (result.hasFieldErrors()){ // 
            return validation(result);
        }
        Optional<ProductEntity> productOptional = service.update(id, product);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    
    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar.
     * @return una respuesta 200 si se elimina correctamente, o una respuesta 404 si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<ProductEntity> productOptional = service.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Maneja la validación de los campos del producto.
     *
     * @param result el resultado de la validación de los campos.
     * @return un ResponseEntity con un mapa de errores de validación y un estado 400.
     */
    private ResponseEntity<?> validation(BindingResult result) {
        //define un map la clave: sería el nombre del campo que estamos validando, value: mje de error
        Map<String, String> errors =new HashMap<>(); // guarda los mensajes de error de cada campo

        result.getFieldErrors().forEach(err ->
            errors.put(err.getField(), "El campo "+ err.getField() + " " + err.getDefaultMessage())); // obtiene la lista de errores
        return ResponseEntity.badRequest().body(errors); //pasar en el cuerpo de la respuesta
    }

}
