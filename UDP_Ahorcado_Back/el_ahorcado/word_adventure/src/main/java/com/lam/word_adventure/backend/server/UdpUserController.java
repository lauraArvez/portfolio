package com.lam.word_adventure.backend.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lam.word_adventure.backend.UDP.utils.StringUtils;
import com.lam.word_adventure.backend.UDP.utils.TokenProvider;
import com.lam.word_adventure.backend.mapper.UserMapper;
import com.lam.word_adventure.backend.models.UserModel;
import com.lam.word_adventure.backend.repositories.UserRepository;
import com.lam.word_adventure.backend.request.RegisterRequest;
import com.lam.word_adventure.backend.responses.UserResponse;
import com.lam.word_adventure.backend.services.UserService;

/**
 * clase de gestión de usuarios
 * 
 * @author Laura Arvez
 */
@Component
public class UdpUserController{

    String payload;
    String password;
    String username;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    
    /**
     * constructor por defecto
     */
    public UdpUserController() {
    }

    /**
     * Maneja el registro de un nuevo usuario en el sistema.
     *
     * @param msgCli Arreglo de strings que contiene los datos de registro del usuario.
     *               Se espera que contenga al menos los siguientes elementos:
     *               - msgCli[2]: username
     *               - msgCli[3]: password
     *               - msgCli[4]: localización
     *               - msgCli[5]: edad (representado como string).
     *               - msgCli[6]: sexo
     *               - msgCli[7]: roles separados por comas (por ejemplo, "USER,ADMIN").
     * @param tokenRequest Token de autenticación proporcionado por el cliente.
     * @return String que representa el resultado del registro. Puede ser "REGISTER,OK"
     *      o un mensaje de error en formato "REGISTER,KO,mensaje de error".
     */
    public String handleRegisterUser (String []msgCli, String tokenRequest){
        try{
            if (tokenRequest.isEmpty() || TokenProvider.verifyJws(tokenRequest) != null){
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setUsername(msgCli[2].toUpperCase());
                password = msgCli[3];
                if  (password.length() < 6){//(!(msgCli[3].length() >= 6)){
                    throw new IllegalArgumentException("REGISTER,KO,El password deber contener mínimo 6 caracteres");
                }
                registerRequest.setPassword(bCryptPasswordEncoder.encode(msgCli[3]));
                registerRequest.setLocation(msgCli[4].toUpperCase());
                registerRequest.setAge(Integer.parseInt(msgCli[5]));
                registerRequest.setSex(msgCli[6].toUpperCase());
                //System.out.println(registerRequest);

                String[] rolesArray = msgCli[7].split(","); // Dividir la cadena en roles individuales
                // Crear una lista para almacenar los roles
                List<String> rolesList = new ArrayList<>();
                // Dividir la cadena en roles individuales y agregarlos a la lista
                for (String role : rolesArray) {
                    rolesList.add(role.trim().toUpperCase());
                }
                // Establecer la lista de roles en registerRequest
                registerRequest.setRole(rolesList);
                //crea el userModeel
                UserModel userRegister = userService.createUserModel(registerRequest);

                if (userService.save(userRegister) != null){
                    payload = "REGISTER,OK,User: " + userRegister.getUsername()+ " registrado correctamente";
                }else{
                    payload = "REGISTER,KO,Este username ya existe en la base de datos";
                }
            } else {
                //token no válido
                payload = "REGISTER,KO,Token no válido";
            }
        } catch (NumberFormatException e) {
            //si falla la conversión del número
            payload = "REGISTER,KO,Invalid age format";
        } catch (IllegalArgumentException e) {
            //si la contraseña no cumple con los requisitos
            payload = "REGISTER,KO," + e.getMessage();
        } catch (Exception e) {
            // cualquier otra excepción no esperada
            payload = "REGISTER,KO,Ocurrió algo inesperado";
        }
        return payload;
    }

    /**
     * Maneja el inicio de sesión de un usuario registrado en el sistema.
     *
     * @param msgCli Arreglo de strings que contiene el nombre de usuario y la contraseña.
     *               Se espera que contenga al menos los siguientes elementos:
     *                - msgCli[2]: username
     *                - msgCli[3]: password
     * @return String que representa el resultado del inicio de sesión. Puede ser "LOGIN,OK"
     *          o "LOGIN,KO,mensaje de error" en caso de error.
     */
    public String handleLoginUser (String [] msgCli){
        username = msgCli[1].trim().toUpperCase();
        password = msgCli[2].trim();

        // IMPLEMENTAR: La contraseña proporcionada está vacía
            
        //crea objeto con el user y password
        UserModel  userLogin = new UserModel();
        userLogin.setUsername(username);
        userLogin.setPassword(password);

        // Intentar realizar el proceso de autenticación utilizando el servicio userService
        try {
            payload = userService.login(userLogin);
        } catch (Exception e) {
            // cualquier excepción que pueda ocurrir durante el proceso de autenticación
            payload = "LOGIN,KO,Authentication error";
        }
        return payload;
    }

    /**
     * Maneja el cambio de contraseña de un usuario.
     *
     * @param msgCli Arreglo de strings que contiene la nueva contraseña.
     *               Se espera que contenga al menos el siguiente elemento:
     *               - msgCli[2]: nuevo password
     * @param tokenRequest Token de autenticación proporcionado por el cliente.
     * @return String que indica el resultado del cambio de contraseña. Puede ser "PWDCHANGE,OK"
     *         o "PWDCHANGE,KO,mensaje de error" en caso de error.
     */
    public String handlePwdChange (String []msgCli, String tokenRequest){
            UserModel userModel = new UserModel();
            try{
                String getUser = TokenProvider.getUserName(tokenRequest);
                if(getUser != null ){
                    System.out.println(getUser);
                    password = msgCli[2].trim();
                    if (password.length() < 6){
                            throw new IllegalArgumentException("La contraseña debe contener al menos 6 caracteres");
                    }
                    userModel.setPassword(bCryptPasswordEncoder.encode(password));
                    payload = userService.updatePasswordChange(getUser, password);
                }
            } catch (IllegalArgumentException e) {
                    //si la contraseña no cumple con los requisitos
                    payload = "PWDCHANGE,KO," + e.getMessage();
            }
            return payload;
    }

    /**
     * Maneja la solicitud de listar todos los usuarios (sin mostrar contraseñas) y devuelve un resultado formateado.
     *
     * @param tokenRequest Token de autenticación proporcionado por el cliente para verificar acceso.
     * @return String que representa el resultado de la operación de listar usuarios.
     *         retorna un mensaje con formato "LISTUSERS,OK,lista de usuarios"
     *         lista de usuarios es un String que contiene datos de los usuarios.
     *         En caso de error: "LISTUSERS,KO,mensaje de error".
     */
    public String handleListUsers (String tokenRequest){
        try{
            //verifica token
            if(TokenProvider.verifyJws(tokenRequest) != null){
                //obtener la lista de usuarios sin el password
                List <UserResponse> userList = userService.findAll().stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
                //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                String responseList =userService.formatUserList(userList);
                payload = "LISTUSERS,OK,"+responseList;
            }else{
                payload = "LISTUSERS,KO,El token no es válido";
            }
        } catch (Exception e) {
            //cualquier excepción
            String errorMessage = "Error ocurrido en el proceso de listar usuarios: " + e.getMessage();
            payload = "LISTUSERS,KO," + errorMessage;
        }
        return payload;
    }

    /**
     * Maneja la solicitud de listar usuarios filtrados según un criterio especificado.
     *
     * @param msgCli Arreglo de strings que contiene la información relacionada con el filtro.
     *               Se espera que contenga al menos los siguientes elementos:
     *               - msgCli[2]: Tipo de filtro ("Sexo", "Rol", "Edad" o "Localizacion").
     *               - msgCli[3]: Valor del filtro.
     * @param tokenRequest Token de autenticación proporcionado por el cliente para verificar acceso.
     * @return String que representa el resultado de la operación de listar usuarios filtrados.
     *         Mensaje con formato "LISTUSERSFILTERED,OK,lista de usuarios"
     *         lista de usuarios es un String que contiene datos de los usuarios de la base de datos.
     *         En caso de error: "LISTUSERSFILTERED,KO,mensaje de error".
     */
    public String handleListUsersFiltered (String [] msgCli, String tokenRequest){
        String filterType = StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim()); //.toUpperCase();
        String dataFilter= StringUtils.normalizeAndRemoveDiacritics(msgCli[3].trim());
        
        // Verificar token
        if (TokenProvider.verifyJws(tokenRequest) != null) {
            try{
                List<UserResponse> userList;
                String responseList;
                payload ="";
                switch (filterType) {
                    case "Sexo":
                        if (dataFilter.equals("Masculino") || dataFilter.equals("Femenino") || dataFilter.equalsIgnoreCase("No-Binario")){
                            userList = userService.getUsersBySex(dataFilter).stream()
                                    .map(UserMapper::toResponse)
                                    .collect(Collectors.toList());
                            if (!userList.isEmpty()){
                                //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                                responseList =userService.formatUserList(userList);
                                payload = "LISTUSERSFILTERED,OK," + responseList.toString();
                            } else {
                                payload = "LISTUSERSFILTERED,KO,No se han encontrado usuarios para el sexo especificado: " + dataFilter;
                            }
                        } else {
                            payload = "LISTUSERSFILTERED,KO,Filtro de sexo no válido";
                        }
                        break;

                    case "Rol":
                        if (dataFilter.equalsIgnoreCase("USER") || dataFilter.equalsIgnoreCase("ADMIN")){
                            userList = userService.getUsersByRole(dataFilter).stream()
                                .map(UserMapper::toResponse)
                                .collect(Collectors.toList());
                            if(!userList.isEmpty()){
                                //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                                responseList =userService.formatUserList(userList);
                                payload = "LISTUSERSFILTERED,OK," + responseList.toString();
                            } else {
                                payload = "LISTUSERSFILTERED,KO,No se han encontrado usuarios para el rol: "+dataFilter;
                            }
                        } else {
                            payload = "LISTUSERSFILTERED,KO,Filtro de función no válido: " + dataFilter;
                        }
                        break;

                    case "Edad":
                        String [] ageRanges = dataFilter.split("-");
                        if(ageRanges.length !=2){
                            payload = "LISTUSERSFILTERED,KO,Formato de rango de edad no válido";
                        }else{
                            int minAge = Integer.parseInt(ageRanges[0]);
                            int maxAge = Integer.parseInt(ageRanges[1]);
                                                    
                        userList = userService.getUsersByAgeRange(minAge,maxAge);
                            if(userList != null && !userList.isEmpty()){
                                //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                                responseList = userService.formatUserList(userList);
                                payload = "LISTUSERSFILTERED,OK," + responseList.toString();
                            }else{
                                payload = "LISTUSERSFILTERED,OK,No se han encontrado usuarios dentro del rango de edad especificado";
                            }
                        }
                        break;

                    case "Localizacion":
                        userList = userService.getUsersByLocation(dataFilter);
                        if(!userList.isEmpty()){
                            //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                            responseList =userService.formatUserList(userList);
                            payload = "LISTUSERSFILTERED,OK," + responseList.toString();
                        }else{
                            payload = "LISTUSERSFILTERED,KO,No se han encontrado usuarios para la ubicación";
                        }
                        break;
                    default:
                        payload = "LISTUSERSFILTERED,KO,Tipo de filtro no válido: "+filterType;
                        break;
                }
                return payload;
            } catch (NumberFormatException e) {
                payload = "LISTUSERSFILTERED,KO,Edad no válido" + e.getMessage();
            } catch(IllegalArgumentException e){
                payload = "LISTUSERSFILTERED,KO,Filtro de sexo no válido:"+ e.getMessage();
            } catch (Exception e) {
                payload = "LISTUSERSFILTERED,KO," + e.getMessage();
            }
            return payload;

        } else{
            //token no válido
            payload = "LISTUSERSFILTERED,KO,Token no válido" ;
        }
        return payload;
    }

    /**
     * Maneja la eliminación de un usuario de la base de datos.
     *
     * @param msgCli Arreglo de strings que contiene el nombre de usuario a eliminar.
     *               Se espera que contenga al menos el siguiente elemento:
     *               - msgCli[2]: username a eliminar
     * @param tokenRequest Token de autenticación proporcionado por el cliente.
     * @return String que indica el resultado de la eliminación del usuario. Puede ser "DELETEUSER,OK"
     *         o "DELETEUSER,KO,mensaje de error" en caso de error.
     */
    public String handleDeleteUser (String [] msgCli, String tokenRequest){
        username = StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim()); //.toUpperCase();
        UserModel userDelete = new UserModel();
        userDelete.setUsername(username);
        
        if(TokenProvider.verifyJws(tokenRequest)!=null){
            try{
                boolean ok = userService.deleteUser(username);
                if(ok){
                    payload = "DELETEUSER,OK" ;
                } else{
                    payload = "DELETEUSER,KO,Username proporcionado no existe." ;
                }
            } catch (Exception e){
                // cualquier otra excepción
                payload = "DELETEUSER,KO,"+e.getMessage();
            }
        } else{
            //token no válido
            payload = "DELETEUSER,KO,Token no válido" ;
        }
        // construir mensaje de respuesta
        return payload;
    }

    /**
     * Maneja la solicitud de obtención del ranking de juegos de un usuario.
     *
     * @param tokenRequest Token de autenticación proporcionado por el cliente.
     * @return String que representa el ranking de juegos del usuario. Puede ser "GAMERANKING,OK,ranking"
     *          o "GAMERANKING,KO,mensaje de error" en caso de error.
     */
    public String handleGameRanking(String tokenRequest){
        try{
            username = TokenProvider.getUserName(tokenRequest);
            if(username == null){
                payload = "GAMERANKING,KO,Token no válido";
            }
            // usuario de tipo model para enviar el id
            UserModel user = userRepository.findByUsernameIgnoreCase(username);
            if(user != null){
                Integer userGame= userService.getGameRanking(user.getUsername());
                String userGameAsString = userGame.toString();
                System.out.println(userGameAsString);
                //fotmatear para que devuelva: String que separa los usuarios con ; y sus campos con :
            //   String response =userService.formatUserGame(userGameAsString);
                payload = "GAMERANKING,OK,"+ userGameAsString;
            }
           // payload = "GAMERANKING,KO,El token no es válido";
            
        } catch (Exception e) {
            //cualquier excepción
            String errorMessage = "Error ocurrido en el proceso de listar rankings de partidas jugadas: " + e.getMessage();
            payload = "GAMERANKING,KO," + errorMessage;
        }
        return payload;
    }
}