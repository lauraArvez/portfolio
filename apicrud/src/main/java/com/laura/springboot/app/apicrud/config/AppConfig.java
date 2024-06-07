package com.laura.springboot.app.apicrud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Clase de configuración de la aplicación.
 * 
 * Esta clase está marcada con la anotación {@link Configuration}, lo que la identifica como una clase de configuración de Spring.
 * También utiliza la anotación {@link PropertySource} para especificar la ubicación del archivo de propiedades de mensajes.
 * 
 * @author Laura Arvez
 */
@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {
    
}
