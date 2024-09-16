package com.example.demo;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

/**
 * Configuración de Swagger para la documentación de la API REST.
 * Swagger es una herramienta que permite generar automáticamente la documentación de las APIs
 * y proporciona una interfaz gráfica para interactuar con las mismas.
 * 
 * Esta clase configura los esquemas de seguridad JWT para asegurar la API.
 * 
 * @author Laura
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

        /**
         * Nombre del encabezado HTTP utilizado para la autenticación JWT.
         */
        public static final String AUTHORIZATION_HEADER = "Authorization";

        /**
         * Patrón de URL que incluirá la documentación de Swagger.
         */
        public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

        /**
         * Bean que configura Swagger para generar la documentación de la API REST.
         * Define qué controladores y endpoints se documentarán y configura la seguridad JWT.
         * 
         * @return un objeto {@link Docket} que define la configuración de Swagger.
         */
        @Bean
        public Docket apiDocket() {
                return new Docket(DocumentationType.SWAGGER_2)
                                .securityContexts(Lists.newArrayList(securityContext()))
                                .securitySchemes(Lists.newArrayList(apiKey()))
                                .select()
                                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controllers"))
                                .paths(PathSelectors.any())
                                .build()
                                .apiInfo(
                                        new ApiInfo("Club de Atletismo API",
                                        "API REST para la gestión de un club de atletismo, incluyendo la administración de usuarios, roles, eventos, y más.",
                                        "1.0.0",
                                        "",
                                        new Contact("Laura Arvez","https://lauraarvez.github.io/","arvezlau@hotmail.com"),
                                        "",
                                        "",
                                        Collections.emptyList()
                                        ));
        }

        /**
         * Define el esquema de autenticación basado en JWT.
         * 
         * @return un objeto {@link ApiKey} con la configuración del encabezado de autenticación.
         */
        private ApiKey apiKey() {
                return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
        }

        /**
         * Configura el contexto de seguridad para Swagger, aplicando el esquema JWT a las rutas documentadas.
         * 
         * @return un objeto {@link SecurityContext} que define la seguridad de la API.
         */
        private SecurityContext securityContext() {
                return SecurityContext.builder()
                                .securityReferences(defaultAuth())
                                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                                .build();
        }

        /**
         * Define los esquemas de autorización para las rutas de la API.
         * 
         * @return una lista de {@link SecurityReference} que contiene el esquema de autorización JWT.
         */
        List<SecurityReference> defaultAuth() {
                AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
                AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
                authorizationScopes[0] = authorizationScope;
                return Lists.newArrayList(
                                new SecurityReference("JWT", authorizationScopes));
        }
}