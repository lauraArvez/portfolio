package com.lam.word_adventure.backend.services.impl;

import static com.lam.word_adventure.backend.security.TokenJwtConfig.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lam.word_adventure.backend.UDP.utils.TokenProvider;
import com.lam.word_adventure.backend.UDP.utils.TokenProviderWrapper;
import com.lam.word_adventure.backend.exceptions.AuthenticationException;
import com.lam.word_adventure.backend.mapper.UserMapper;
import com.lam.word_adventure.backend.models.LocationModel;
import com.lam.word_adventure.backend.models.RoleModel;
import com.lam.word_adventure.backend.models.SexModel;
import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.repositories.SexRepository;
import com.lam.word_adventure.backend.repositories.UserRepository;
import com.lam.word_adventure.backend.request.RegisterRequest;
import com.lam.word_adventure.backend.responses.UserResponse;
import com.lam.word_adventure.backend.services.LocationService;
import com.lam.word_adventure.backend.services.RoleService;
import com.lam.word_adventure.backend.services.SexService;
import com.lam.word_adventure.backend.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.transaction.Transactional;

/**
 * clase implementación de service
 *
 * @author Laura Arvez
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private SexService sexService;

    @Autowired
    private SexRepository sexRepository;

    /**
     * constructor por defecto
     */
    public UserServiceImpl() {
    }

    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    // @Transactional (readOnly = true)
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    // @Transactional
    public UserModel save(UserModel registerUser) {
        UserModel userExist = userRepository.findByUsername(registerUser.getUsername()).orElse(null);

        if (userExist != null) {
            return null;
        }

        return userRepository.save(registerUser); // guardamos
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public String login(UserModel userModel) {
        Optional<UserModel> user = userRepository.findByUsername(userModel.getUsername());

        // verifica si el usuario existe en la bbdd
        if (user.isEmpty()) {
            // throw new UsernameNotFoundException(String.format("Username %s no existe en
            // la base de datos!", userModel.getUsername()));
            return ("LOGIN,KO,Username no existe en la base de datos!");
        }

        try {
            // Si existe, obtenermos la instancia del usuario
            UserModel userInstancia = user.get(); // .orElseThrow();

            // verifica password
            if (!passwordEncoder.matches(userModel.getPassword(), userInstancia.getPassword())) {
                throw new AuthenticationException.AmbiguousCredentialsException(
                        "ERROR en la autenticación, username o password incorrecto");
            }

            // Generar el token de autenticación
            String token = TokenProvider.generateToken(userModel.getUsername());

            // Obtener los roles del usuario
            List<RoleModel> userRoles = new ArrayList<>(userInstancia.getRoles());
            String rolesAsString = userRoles.stream()
                    .map(RoleModel::getRole)
                    .collect(Collectors.joining(","));

            return String.format("LOGIN,OK,%s,%s", token, rolesAsString);

        } catch (AuthenticationException e) {
            // Error de autenticación
            return "LOGIN,KO," + e.getMessage();

        } catch (Exception e) {
            // cualquier otra excepción inesperada
            return "LOGIN,KO,Error inesperado en la autenticación";
        }
    }

    /**
     * obtener roles por username
     * @param username nombre de usuario
     * @return List de roles del usuario
     */
    public List<String> getRolesByUsername(String username) {
        UserModel user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            Set<RoleModel> roles = user.getRoles();
            System.out.println("Número de roles: " + roles.size());
            roles.forEach(role -> System.out.println("Nombre del rol: " + role.getRole()));
            // Convertimos el conjunto de RoleModel a una lista de nombres de roles
            // (Strings)
            List<String> roleNames = roles.stream()
                    .map(RoleModel::getRole) // Obtiene el nombre de cada RoleModel
                    .collect(Collectors.toList()); // Recolecta en una lista

            return roleNames; // Devuelve la lista de nombres de roles
        }
        return Collections.emptyList();
    }

    // :::::::::::::::::::::::::::: LOGOUT
    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public String logout(String tokenRequest) {
        try {
            if (!tokenRequest.isEmpty()) {
                String token = tokenRequest.replace("Bearer ", "");
                // tokenService.guardarToken(token);

                TokenProviderWrapper tokenProviderWrapper = new TokenProviderWrapper(); // cambio hecho para el test
                Claims payload = tokenProviderWrapper.verifyJws(token);
                // payload.getSubject();
                return "LOGOUT,OK,Usuario desconectado";
            }
            return "LOGOUT,KO,Error con el Token";

        } catch (JwtException e) {
            System.out.println(e.getMessage());
            return "LOGOUT,KO,";
        } catch (Exception e) {
            System.out.println("Error inesperado durante el proceso de logout: " + e.getMessage());
            return "LOGOUT,KO";
        }

    }

    // :::::::::::::::::::::::::::: UPDATE PASSWORD
    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    @Override
    public String updatePasswordChange(String username, String newPassword) {
        try {
            // verifica si el user existe en la bbdd
            Optional<UserModel> existUser = userRepository.findByUsername(username);
            if (!existUser.isPresent()) {
                throw new UsernameNotFoundException(String
                        .format("Username %s no existe en la base de datos!", username));
            }
            UserModel userToUpdate = existUser.get();

            // verifica si la contaseñana anterior es diferente a la actual
            if (passwordEncoder.matches(newPassword, userToUpdate.getPassword())) {
                throw new AuthenticationException.SamePasswordException(
                        "La nueva contraseña no puede ser la misma que la anterior");
            }

            // actualiza password
            userToUpdate.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userToUpdate); // guardamos cambios

            return "PWDCHANGE,OK,Cambio de contraseña OK";

        } catch (UsernameNotFoundException e) {
            // excepción de usuario no encontrado
            return "PWDCHANGE,KO," + e.getMessage();
        } catch (AuthenticationException.SamePasswordException e) {
            // excepción de contraseña igual a la actual
            return "PWDCHANGE,KO," + e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException("PWDCHANGE,KO,no se ha podido cambiar la contraseña");
        }

    }

    // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    @Override
    public String searchByUsername(String username) {
        Optional<UserModel> getUser = userRepository.findByUsername(username);
        if (!getUser.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username %s no existe en la base de datos!", username));
        }
        try {
            userRepository.findByUsernameIgnoreCase(username);
            UserModel perfilUser = new UserModel();
            // obetener roles
            List<GrantedAuthority> authorities = perfilUser.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRole()))
                    .collect(Collectors.toList());
            return perfilUser.getUsername() + "," + perfilUser.getId() + "," + perfilUser.getPassword() + ","
                    + authorities;

        } catch (Exception e) {
            return ("Username %s no existe en la base de datos!");
        }
    }

    /**
     * covierte lista en string
     *
     * @param list lista de objetos a convertir a string
     * @return StringBuilder que contiene la lista como string separada por ":"
     */
    public StringBuilder convertListToString(List<Object> list) {
        StringBuilder userListString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            userListString.append(list.get(i));
            if (i < list.size() - 1) {
                userListString.append(":");
            }
        }
        return userListString;
    }

    @Override
    public boolean isValidToken(String token) {
        return StringUtils.hasText(token) && token.equals(SECRET_KEY);
    }

    @Override
    public UserModel createUserModel(RegisterRequest registerRequest) {
        Set<RoleModel> roles = new HashSet<>();

        // Obtener la lista de nombres de roles desde registerRequest
        List<String> roleNames = registerRequest.getRole();
        for (String roleName : roleNames) {
            try {
                // obtener el rol por su nombre
                RoleModel roleModel = roleService.getRole(roleName);// ojo
                if (roleModel != null) {
                    roles.add(roleModel);
                } else {
                    // cuando el rol no existe en la base de datos
                    throw new IllegalArgumentException(
                            "El rol proporcionado no existe en la base de datos: " + roleName);
                }
            } catch (Exception e) {
                // cualquier excepción al obtener el rol
                throw new IllegalArgumentException("Error obteniendo el rol: " + roleName, e);
            }
        }
        LocationModel locationRegister = locationService.getLocationModel(registerRequest.getLocation());
        if (locationRegister == null) {
            // cuando la ubicación no existe en la base de datos
            throw new IllegalArgumentException(
                    "La ubicación especificada no existe en la base de datos: " + registerRequest.getLocation());
        }
        SexModel sexRegister = sexService.getSex(registerRequest.getSex());
        if (sexRegister == null) {
            // cuando el sexo no existe en la base de datos
            throw new IllegalArgumentException(
                    "Esta opción de sexo no existe en la base de datos: " + registerRequest.getSex());
        }

        return new UserModel(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                locationRegister,
                registerRequest.getAge(),
                sexRegister,
                roles);
    }

    @Override
    public String formatUserList(List<UserResponse> userList) {
        StringBuilder userListString = new StringBuilder();

        for (UserResponse user : userList) {
            userListString.append(formatUser(user)).append(";");
        }

        // Eliminar el último ':' si hay usuarios en la lista
        if (userList.size() > 0) {
            userListString.deleteCharAt(userListString.length() - 1);
        }

        return userListString.toString();
    }

    /**
     * formatea los datos de un usuario en una cadena delimitada por ':'
     *
     * @param user objeto UserResponse a formatear
     * @return string formateado con los datos del usuario
     */
    private String formatUser(UserResponse user) {
        StringBuilder userString = new StringBuilder();

        // Agregar cada campo del usuario separado por ':'
        userString.append(user.getUsername()).append(":");
        userString.append(formatLocation(user.getLocation())).append(":");
        userString.append(user.getAge()).append(":");
        userString.append(formatSex(user.getSex())).append(":");
        userString.append(formatRoles(user.getRoles())); // Formatear los roles

        return userString.toString();
    }

    @Override
    public String formatLocation(LocationModel location) {
        return (location != null) ? location.getLocation() : "";
    }
    
    @Override
    public String formatSex(SexModel sex) {
        return (sex != null) ? sex.getSex() : "";
    }

    /**
     * formatea los roles
     *
     * @param roles roles del usuario
     * @return roles en formato string
     */
    private String formatRoles(Set<RoleModel> roles) {
        StringBuilder rolesString = new StringBuilder();

        // Concatenar los nombres de los roles separados por ','
        for (RoleModel role : roles) {
            rolesString.append(role.getRole()).append(",");
        }

        // Eliminar el último ',' si hay roles en la lista
        if (rolesString.length() > 0) {
            rolesString.deleteCharAt(rolesString.length() - 1);
        }

        return rolesString.toString();
    }

    @Override
    public List<UserModel> getUsersBySex(String sex) {
        try {
            SexModel userSex = sexRepository.findBySex(sex);
            if (userSex == null) {
                throw new IllegalArgumentException("Sexo no encontrado en la base de datos: " + sex);
            }
            return userRepository.findAllBySexOrderByAge(userSex);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sexo no encontrado en la base de datos: " + sex);
        } catch (Exception e) {
            System.err.println("Error al buscar usuarios por sexo: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<UserModel> getUsersByRole(String role) {
        try {
            return userRepository.findAllByRolesRole(role);
        } catch (Exception e) {
            System.err.println("Error al buscar usuarios por rol: " + e.getMessage());
            return Collections.emptyList(); // lista vacía
        }
    }

    @Override
    public List<UserResponse> getUsersByAgeRange(int minAge, int maxAge) {
        try {
            // obtener usuarios por rango
            List<UserModel> users = userRepository.findByAgeBetween(minAge, maxAge);
            List<UserResponse> userList = users.stream()
                    .map(UserMapper::toResponse)
                    .collect(Collectors.toList());
            return userList;
        } catch (Exception e) {
            System.err.println("Error al buscar usuarios por rango de edad: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public List<UserResponse> getUsersByLocation(String location) {
        try {
            // obtener la localización desde location
            LocationModel getLocation = locationService.getLocationModel(location);

            if (getLocation == null) {
                throw new IllegalArgumentException("Localización no encontrada: " + location);
            }

            // obtener location
            List<UserModel> users = userRepository.findByLocation(getLocation.getLocation()); // .findByLocation(getLocation.getLocation());
            // mapear respuestas
            List<UserResponse> userList = users.stream()
                    .map(UserMapper::toResponse)
                    .collect(Collectors.toList());
            return userList;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Localización no válida: " + location);
        } catch (Exception e) {
            System.err.println("Error buscando usuario con esa localización: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(String user) {
        logger.debug("Iniciando deleteUser para el usuario: {}", user);

        UserModel userExist = userRepository.findByUsernameIgnoreCase(user);
        if (userExist != null) {
            userExist.getRoles().clear(); // eliminar roles del usuario
            userExist.setLocation(null); // ubicación del usuario como nula
            userExist.setSex(null); // sexo del usuario como nulo
            userRepository.deleteByUsernameQuery(userExist.getUsername());

            return true; // Devuelve true si el usuario fue eliminado correctamente
        }
        return false;
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Integer getGameRanking(String user) {
        return userRepository.findRankingByGamesPlayed(user);
    }
}
