package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase `ServletInitializer` que permite desplegar la aplicación Spring Boot como un archivo WAR en un servidor externo.
 * Extiende de {@link SpringBootServletInitializer} y personaliza el método {@link #configure}.
 * 
 * Esto es útil cuando la aplicación debe ser desplegada en un servidor de aplicaciones como Tomcat, JBoss, o WebLogic.
 * 
 * @author Laura
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
     * Configura la aplicación para ejecutarse en un contenedor de servlets tradicional.
     * 
     * Este método se utiliza para asociar la clase principal de la aplicación {@link DemoApplication}
     * con el contenedor de servlets. Permite que la aplicación se despliegue como un archivo WAR.
     *
     * @param application El builder de la aplicación Spring.
     * @return El builder configurado con la clase principal de la aplicación.
     */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}
}
