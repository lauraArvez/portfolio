package com.example.demo.models.usuario;

/**
 * Clase que representa las credenciales de inicio de sesión (Login) de un usuario.
 * Contiene el nombre de usuario (username) y la contraseña (password).
 * 
 * Esta clase se utiliza típicamente para recibir o enviar las credenciales durante el proceso de autenticación.
 * 
 * @author Laura
 */
public class Login {

    /**
     * El nombre de usuario del usuario que intenta iniciar sesión.
     */
    private String username;

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param username El nombre de usuario que se establecerá.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * La contraseña del usuario que intenta iniciar sesión.
     */
    private String password;

    /**
     * Obtiene la contraseña.
     * 
     * @return La contraseña.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña.
     * 
     * @param password La contraseña que se establecerá.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Constructor por defecto de la clase Login.
     * Crea una instancia vacía de Login.
     */
    public Login() {

    }

    /**
     * Constructor que permite crear una instancia de Login con un nombre de usuario y una contraseña.
     * 
     * @param username El nombre de usuario.
     * @param password La contraseña.
     */
    public Login(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}

