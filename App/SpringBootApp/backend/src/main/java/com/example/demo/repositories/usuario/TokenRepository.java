package com.example.demo.repositories.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.models.usuario.TokenModel;

/**
 * Repositorio JPA para gestionar operaciones CRUD en la entidad {@link TokenModel}.
 * Proporciona métodos adicionales para verificar la existencia de tokens en la base de datos.
 * 
 * Este repositorio se utiliza principalmente para gestionar los tokens de autenticación o verificación.
 * 
 * @author Laura
 */
@Component
public interface TokenRepository extends JpaRepository<TokenModel, Long> {

    /**
     * Verifica si existe un token específico en la base de datos.
     * 
     * @param token El valor del token que se busca.
     * @return {@code true} si el token existe, {@code false} en caso contrario.
     */
    Boolean existsByToken(String token);

}
