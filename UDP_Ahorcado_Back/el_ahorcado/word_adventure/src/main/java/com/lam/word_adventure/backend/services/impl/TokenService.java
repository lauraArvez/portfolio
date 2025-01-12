/*package com.lam.word_adventure.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lam.word_adventure.backend.repositories.TokenRepository;

/**
 * clase implementación de service
 * @author Laura Arvez
 */
/*@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    /**
     * busca un token utilizado
     * @param token
     * @return boolean
     */
   /* public Boolean estaElTokenEnLaListaNegra(String token) {
        return tokenRepository.existsByToken(token);
    }

    @SuppressWarnings("null")
    public void guardarToken(String token) {
        String tokenWithoutHeader = token.replace("Bearer ",""); //EXTRAE Bearer
        if (!token.isEmpty()){
            if(!tokenRepository.existsByToken(tokenWithoutHeader)) {
                tokenRepository.save(com.lam.word_adventure.backend.models.TokenModel
                .builder()
                .token(tokenWithoutHeader)
                .build());
            }
        }
    }
}
*/