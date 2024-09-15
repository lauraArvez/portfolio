package com.example.demo.controllers.usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.usuarioMapper.UserMapper;
import com.example.demo.models.usuario.RoleModel;
import com.example.demo.models.usuario.UsuarioModel;
import com.example.demo.requests.AuthorizationRequest;
import com.example.demo.requests.usuario.RegistrarRequest;
import com.example.demo.requests.usuario.UserUpdateAdmin;
import com.example.demo.responses.usuarioResponses.UserResponse;
import com.example.demo.services.usuario.PasswordService;
import com.example.demo.services.usuario.RolesService;
import com.example.demo.services.usuario.TokenService;
import com.example.demo.services.usuario.UsuarioService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para gestionar el registro y la actualización de contraseñas de los usuarios.
 * 
 * Proporciona endpoints para registrar un nuevo usuario y para realizar un restablecimiento de contraseña.
 * 
 * @author Laura
 */
@RestController
@RequestMapping("/api/usuario")
@Slf4j
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolesService rolesService;

    @Autowired
    PasswordService passwordService;
    

    @Autowired
    TokenService tokenService;
    
    /**
     * Registra un nuevo usuario, encripta la contraseña y asigna roles.
     * Si el usuario ya existe, retorna un mensaje de error.
     *
     * @param datos Datos del usuario a registrar.
     * @return ResponseEntity con mensaje y estado HTTP.
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@Valid @RequestBody RegistrarRequest datos) {
        if (datos == null || datos.getUsername() == null || datos.getPassword() == null || datos.getEmail() == null) {
            log.warn("Datos incompletos en la solicitud de registro");
            return new ResponseEntity<>("Datos incompletos", HttpStatus.BAD_REQUEST);
        }
        try {
            // Verifica si el nombre de usuario ya existe
            if (usuarioService.existeUsuario(datos.getUsername())) {
                log.info("Intento de registro con un nombre de usuario existente: " + datos.getUsername());
            return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
            }
    
            // Validar y encriptar la contraseña utilizando PasswordService
            datos.setPassword(passwordService.encriptarPassword(datos.getPassword()));
    
            // Asignar roles al usuario
            Set<RoleModel> roles = new HashSet<>();
            datos.getRoles().forEach(role -> roles.add(rolesService.obtenerRolePorName(role)));
    
            // Crear y registrar el usuario
            UsuarioModel usuario = new UsuarioModel(datos.getUsername(), datos.getPassword(), datos.getEmail(), roles);
            usuarioService.registrarUsuario(usuario);
    
            log.info("Usuario registrado con éxito: " + datos.getUsername());
            return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al registrar el usuario", e);
            return new ResponseEntity<>("Error al registrar el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Restablece la contraseña de un usuario existente.
     * 
     * @param request Objeto que contiene el nombre de usuario y la nueva contraseña.
     * @return ResponseEntity con mensaje y estado HTTP.
     */
    @PutMapping("/reset-password")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> resetPassword(@RequestBody AuthorizationRequest request) {
        UsuarioModel user = usuarioService.obtenerUsuarioPorUsername(request.getUsername());
        if (user == null) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        // Validar y cambiar la contraseña usando PasswordService
        usuarioService.cambiarUsuarioPassword(user, request.getPassword());
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }

    /**
     * Cierra la sesión del usuario.
     * 
     * @return ResponseEntity con mensaje de éxito.
     */
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @PostMapping("/cerrar-sesion")
    public ResponseEntity<String> cerrarSesion() {
        log.info("Cerrando sesión del usuario");
        return new ResponseEntity<>("Se cerró la sesión con éxito", HttpStatus.OK);
    }
    
    /**
     * Obtiene todos los usuarios activos de la base de datos.
     * 
     * @return Página de UserUpdateResponse con los datos de los usuarios.
     */
    @Secured({ "ROLE_ADMIN"})
    @GetMapping(path = "/todos")
    public Page<UserResponse> obtenerTodosLosUsuarios(){
        log.info("Obteniendo todos los usuarios");
        Pageable pageable = PageRequest.of(0, 5, Sort.by("username").descending());
        return this.usuarioService.obtenerTodosLosUsuarios(pageable).map(usuario -> UserMapper.toResponse(usuario));
    }
    
    /**
     * Obtiene todos los usuarios activos de la base de datos.
     * 
     * @return Página de UserUpdateResponse con los datos de los usuarios.
     */
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping(path = "/activos")
    public List<UserResponse> obtenerTodosLosUsuariosActivos(){
        log.info("Obteniendo todos los usuarios");
        return this.usuarioService.obtenerTodosLosUsuariosActivos().stream().map(UserMapper::toResponse)
        .collect(Collectors.toList());
    }

    /**
     * Obtiene datos de un usuario por su nombre de usuario, sin incluir la contraseña.
     * Accesible solo para administradores.
     * 
     * @param username Nombre de usuario.
     * @return Optional de UserResponse con los datos del usuario.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/user/{username}")
    public UserResponse obtenerPorUsername(@PathVariable("username") String username) {
        log.info("Obteniendo usuario por nombre de usuario: {}", username);

        // Obtenemos el usuario directamente
        UsuarioModel usuario = this.usuarioService.obtenerUsuarioPorUsername(username);

        // Verificamos si el usuario es null, en cuyo caso lanzamos una excepción o devolvemos un mensaje personalizado
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el username: " + username);
        }

        // Mapeamos el usuario a la respuesta usando el UserMapper
        return UserMapper.toResponse(usuario);
    }
    
    /**
     * Permite actualizar email por username.
     * 
     * @param username Nombre de usuario (username).
     * @param emailData Un mapa que contiene el nuevo email en el campo "email".
     * @return ResponseEntity con mensaje de éxito y estado HTTP.
     */
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @PutMapping("/modificar/email/{username}")
    public ResponseEntity<String> usuarioModificaSusDatosDeRegistro(@PathVariable("username") String username, @RequestBody Map<String, String> emailData) {
        String email = emailData.get("email");
        
        if (email == null || email.isEmpty()) {
            return new ResponseEntity<>("El campo email no puede estar vacío", HttpStatus.BAD_REQUEST);
        }

        log.info("Actualizando email de usuario con username: {}", username);
        usuarioService.actualizarRegistroEmailDeUsuarioPorUsername(username, email);
        return new ResponseEntity<>("Email de usuario actualizado correctamente", HttpStatus.OK);
    }

    /**
     * Permite a un administrador actualizar los datos de registro de un usuario.
     * 
     * @param id Identificador del usuario.
     * @param updateResponse Datos a actualizar.
     * @return ResponseEntity con mensaje de éxito y estado HTTP.
     */
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/modificar/user/{username}")
    public ResponseEntity <String> adminActualizaRegistroDeUsuario(@PathVariable (name="username")String username, @RequestBody UserUpdateAdmin updateResponse){
        log.info("Admin actualizando datos de usuario con ID: {}", username);
        usuarioService.adminActualizaRegistroDeUsuario(username, updateResponse);
        return new ResponseEntity<> ("Datos de usuario actualizado correctamente por Admin", HttpStatus.OK);
    }

    
    /**
     * Inhabilita un usuario por su identificador.
     * 
     * @param id Identificador del usuario.
     * @return Mensaje indicando si el usuario fue inhabilitado o no.
     */
    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping(path = "/delete/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        log.info("Inhabilitando usuario con ID: {}", id);
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se inhabilitó el usuario con id " + id;
        } else {
            return "No pudo inhabilitar al usuario con id " + id;
        }
    }
}