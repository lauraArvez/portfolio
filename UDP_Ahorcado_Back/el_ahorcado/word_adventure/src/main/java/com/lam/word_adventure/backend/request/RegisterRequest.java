package com.lam.word_adventure.backend.request;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Clase del request para el registro
 * 
 * @author Laura Arvez
 */

@Data
@AllArgsConstructor
@Builder
public class RegisterRequest  implements Serializable{
    
    /**
     * nombre de usuario
     */
    private String username;

    /**
     * password para registro de usuario
     */
    private String password;

    /**
     * localización del usuario
     */
    private String location;

    /**
     * edad del usuario
     */
    private int age;

    /**
     * sexo del usuario
     */
    private String sex;

    /**
     * roles del usuario
     */
    private List<String> role;
    

    /**
     * constructor por defecto
     */
    public RegisterRequest(){

    }
    
}
