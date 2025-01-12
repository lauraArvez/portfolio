package com.lam.word_adventure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal de la aplicación Word Adventure
 * que estiende de SpringBootServletInitializer
 *
 * @author Laura Arvez
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lam.word_adventure.backend"})
public class WordAdventureApplication extends SpringBootServletInitializer {


    /**
     * Constructor por defecto de la clase WordAdventureApplication.
     * Este constructor no lleva parámetros y es utilizado por el sistema de Spring Boot para inicializar la aplicación.
     */
    public WordAdventureApplication() {
        // Constructor por defecto, no realiza ninguna operación específica.
    }

    /**
     * Configuración para el despliegue en un contenedor servlet externo.
     * Este método configura la aplicación Spring Boot para el despliegue en un entorno de contenedor servlet externo.
     *
     * @param application El constructor de SpringApplicationBuilder utilizado para configurar la aplicación.
     * @return SpringApplicationBuilder configurado para la aplicación Word Adventure.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WordAdventureApplication.class).properties("spring.mandatoryFileEncoding=UTF-8");
    }

    /**
     * Método principal que inicia la aplicación Spring Boot.
     * Este método inicia la aplicación Spring Boot para la aplicación Word Adventure.
     *
     * @param args Argumentos de línea de comandos pasados al iniciar la aplicación.
     */
	public static void main(String[] args) {
		SpringApplication.run(WordAdventureApplication.class, args);
	}

}
