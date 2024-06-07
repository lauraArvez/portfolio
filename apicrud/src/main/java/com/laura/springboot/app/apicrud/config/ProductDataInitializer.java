package com.laura.springboot.app.apicrud.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.laura.springboot.app.apicrud.entities.ProductEntity;
import com.laura.springboot.app.apicrud.services.ProductService;

import javax.annotation.PostConstruct;

@Component
public class ProductDataInitializer {

    private final ProductService productService;

    @Autowired
    public ProductDataInitializer(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    @PostConstruct
    public void init() {
        // Crea y guarda algunos productos al iniciar la aplicación
        ProductEntity product1 = new ProductEntity();
        product1.setName("Plátano Canraio");
        product1.setDescription("Plátanos Canarios ");
        product1.setPrice(2.39f);
        productService.save(product1);

        ProductEntity product2 = new ProductEntity();
        product2.setName("Mango");
        product2.setDescription("Mango de Brasil");
        product2.setPrice(3.0f);
        productService.save(product2);

    }
}
