package com.example.demo.services.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.usuario.TokenModel;
import com.example.demo.repositories.usuario.TokenRepository;
/**
 * Servicio para gestionar tokens de autenticación, incluyendo su almacenamiento y verificación.
 * Este servicio permite verificar si un token ha sido marcado como inválido o está en una "lista negra".
 * 
 * @author Laura
 */
@Service
public class TokenService {

    /**
     * Repositorio para acceder y gestionar los tokens en la base de datos.
     */
    @Autowired
    TokenRepository tokenRepository;

    /**
     * Verifica si el token proporcionado está en la lista negra.
     * 
     * @param token El token que se desea verificar.
     * @return true si el token está en la lista negra, false en caso contrario.
     */
    public Boolean estaElTokenEnLaListaNegra(String token) {
        return tokenRepository.existsByToken(token);
    }

    /**
     * Guarda un nuevo token en la base de datos.
     * 
     * @param token El token que se desea guardar.
     */
    public void guardarToken(String token) {
        tokenRepository.save(TokenModel.builder().token(token).build());
    }
}