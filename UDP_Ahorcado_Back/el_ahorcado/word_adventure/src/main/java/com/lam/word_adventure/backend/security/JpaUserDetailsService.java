package com.lam.word_adventure.backend.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.repositories.UserRepository;

/**
 * clase service que permite ir a buscar al usuario cuando realiza el login
 * @author Laura Arvez
 */
@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    
    /**
     * constructor por defecto
     */
     public JpaUserDetailsService() {
    }



    //buscar al usuario por username
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> userOptional = repository.findByUsername(username);
        //validar
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en la base de datos!", username));
        }

        //Si existe, obtenermos la instancia del usuario
        UserModel userModel = userOptional.orElseThrow();

        //obtener los roles
        List<GrantedAuthority> authorities = userModel.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            userModel.getUsername(),
            userModel.getPassword(),
         //   userModel.isEnabled(),
        //    true,
         //   true,
        //    true,
                    authorities);
    }
    
}
