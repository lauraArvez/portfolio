package com.lam.word_adventure.backend.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * clase mixIn que personaliza el constructor de SimpleGrantedAutority para que pueda aceptar
 * el atributo authorities y no role.
 * @author Laura Arvez
 */
public abstract class SimpleGrantedAuthorityJsonCreator {

    /**
     * recibe por parámetro role como en el constructor original
     * pero inyectamos el atributo authorities
     * @param role el nombre del rol
     */
    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator (@JsonProperty("authority") String role){

    }

    
}
