package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicializa la aplicación Spring Boot.
 * Esta clase contiene el método main que se encarga de arrancar la aplicación.
 * 
 * Anotaciones clave:
 * - {@link SpringBootApplication}: Indica que esta es una aplicación Spring Boot.
 * - {@link EnableAutoConfiguration}: Permite la configuración automática de Spring Boot en función de las dependencias del proyecto.
 * 
 * @author Laura
 */
@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication {

     /**
     * Método principal (entry-point) de la aplicación Spring Boot.
     * Se encarga de inicializar el contexto de Spring y ejecutar la aplicación.
     *
     * @param args Argumentos de línea de comando que pueden ser pasados a la aplicación (si es necesario).
     */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

}
