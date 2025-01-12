/*package com.lam.word_adventure.backend.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lam.word_adventure.backend.models.RoleModel;
import com.lam.word_adventure.backend.models.UserModel;

public class UserDetailsMapper {

    public static UserDetails build(UserModel user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

    private static Set<? extends GrantedAuthority> getAuthorities(UserModel retrievedUser) {
        Set<RoleModel> roles = (Set<RoleModel>) retrievedUser.getRoles();

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole())));

        return authorities;
    }
    
}
*/