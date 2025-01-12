package com.lam.word_adventure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * clase de configuración
 * @author Laura Arvez
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:messages.properties") //configura los mensajes de errores personalizados
public class AppConfig {

    /**
     * constructor por defecto
     */
    public AppConfig(){

    }

    
}

