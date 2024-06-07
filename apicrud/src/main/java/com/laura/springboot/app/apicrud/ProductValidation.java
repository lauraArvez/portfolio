package com.laura.springboot.app.apicrud;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.laura.springboot.app.apicrud.entities.ProductEntity;

/**
 * Clase encargada de validar la entidad de producto {@link ProductEntity}.
 * 
 * @author Laura Arvez
 */
@Component
public class ProductValidation implements Validator{

    /**
     * Verifica si esta validador es compatible con una clase específica.
     *
     * @param clazz la clase a verificar compatibilidad.
     * @return true si esta clase puede validar objetos de la clase especificada, false de lo contrario.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductEntity.class.isAssignableFrom(clazz);
    }

    /**
     * Valida el objeto de destino y registra errores de validación en el objeto Errors proporcionado.
     *
     * @param target el objeto a validar.
     * @param errors los errores de validación a registrar.
     */
    @Override
    public void validate(Object target, Errors errors) {
        // cast a product
        ProductEntity product = (ProductEntity) target;

        // validar con ValidationUtils
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",  null,"es requerido!");
    
        if (product.getDescription() == null || product.getDescription().isBlank()){
            // errors para validar con if()
            errors.rejectValue("description",  null,"Descripción del producto es requerido.");
        }

        if (product.getPrice() == null){
            errors.rejectValue("price", null, "No puede ser nulo");
        } else if(product.getPrice() < 0){
            errors.rejectValue("price", null,"debe ser valor numérico mayor o igual a 0.");

        }
    }
    
}

