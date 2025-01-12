package com.lam.word_adventure.backend.server;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lam.word_adventure.backend.UDP.utils.TokenProvider;
import com.lam.word_adventure.backend.responses.SendResponsePayload;
//import com.lam.word_adventure.backend.security.EncryptionAes;
import com.lam.word_adventure.backend.services.UserService;

import lombok.AllArgsConstructor;


/**
 * esta clase maneja los mensajes UDP recibidos en el canal de entrada inboundChannel,
 * registra información sobre el contenido del mensaje y envía una respuesta estática
 * a través del canal de salida outboundChannel.
 * @author Laura Arvez
 */
@MessageEndpoint
@AllArgsConstructor
public class UdpInboundMessageHandler {

    // para registrar información y depurar el comportamiento del manejador de mensajes.
    private final static Logger LOGGER = LoggerFactory.getLogger(UdpInboundMessageHandler.class);

    @Autowired
    private SendResponsePayload sendResponsePayload;

    @Autowired
    private MessageChannel outboundChannel;

    @Autowired
    private UserService userService;

    @Autowired
    private UdpWordController udpWordController;
    
    @Autowired
    private UdpUserController udpUserController;

    @Autowired
    private UdpGameController udpGameController;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /*@Autowired
    EncryptionAes encryptionAes;*/

    /**
     * Constructor por defecto de UdpInboundMessageHandler
     */
    public UdpInboundMessageHandler() {
    }


    /**
     * Anotación: marca el método handleMessage() como un activador de servicio que
     * se ejecutará cuando se reciba un mensaje en el canal de entrada inboundChannel.
     * Método: maneja el mensaje recibido en el canal de entrada.
     * @param message El mensaje recibido, que incluye tanto el contenido del mensaje como sus metadatos.
     * @param headerMap Un mapa que contiene los encabezados del mensaje.
     */
    @ServiceActivator(inputChannel = "inboundChannel")
    public void handleMessage(Message<String> message, @Headers Map<String, Object> headerMap) {
    // Registra un mensaje informativo en el registro, mostrando el contenido del mensaje UDP recibido.
        LOGGER.info("Received UDP payload: {}", message.getPayload());
        //System.err.println("mensaje: " + message.getPayload());

        //extrae el contenido del mensaje que se espera esté encriptado
        //String encryptedPayload = message.getPayload();
        //LOGGER.info("encriptado: {}", encryptedPayload);

        //desencripta y asigna el resultado
        //String decryptedPayload = encryptionAes.decrypt(encryptedPayload);
        //LOGGER.info("desencriptado: {}", decryptedPayload);

        //dividir la petición desencriptada en partes
        //String [] msgCli = decryptedPayload.split(",");

        // Sin encriptación, directamente usamos el mensaje recibido
        String data =  message.getPayload();
        String [] msgCli = data.split(",");
        
        String request= msgCli[0].trim();
        String tokenRequest= msgCli[1].trim();

        String payload = "";

        switch (request.toUpperCase()) {
            case "REGISTER":
                if(msgCli.length < 7){
                    payload = "REGISTER,KO,No se puede procesar la petición por falta de argumentos";
                } else {
                    payload = udpUserController.handleRegisterUser(msgCli, tokenRequest);
                }
                break;

            case "LOGIN":
                payload = udpUserController.handleLoginUser(msgCli);
                break;

            case "LOGOUT":
                payload =  userService.logout(tokenRequest);
                break;
            
            case "PWDCHANGE":
                payload = udpUserController.handlePwdChange(msgCli,tokenRequest);
                break;
                
            case "LISTUSERS":
                if (!tokenRequest.isBlank()){
                    payload = udpUserController.handleListUsers(tokenRequest);
                } else {
                    payload = "LISTUSERS,KO,El token está vacío";
                }
                break;

            case "LISTUSERSFILTERED":
                payload = udpUserController.handleListUsersFiltered(msgCli,tokenRequest);
                break;
                
            case "DELETEUSER":
                payload = udpUserController.handleDeleteUser(msgCli,tokenRequest);
                break;

            case "GAMERANKING":
                payload = udpUserController.handleGameRanking(tokenRequest);
                break;
            

//::::::::::::::::::::::::::::::::::::::::::::::: WORDS :::::::::::::::::::::::::::::::::::::::::::::::

            case "REGISTERWORD":
                if(msgCli.length < 5){
                    payload = "REGISTERWORD,KO,No se puede procesar la petición por falta de argumentos";
                } else {
                    payload = udpWordController.handleRegisterWord(msgCli, tokenRequest);
                }
                break;

            case "LISTWORDS":
                payload = udpWordController.handleListWord(tokenRequest);
                break;

            case "LISTWORDSFILTERED":
                payload = udpWordController.handleListWordsFiletered(msgCli,tokenRequest);
                break;
                                
            case "DELETEWORD":
                payload = udpWordController.handleDeleteWord(msgCli,tokenRequest);
                break;

//::::::::::::::::::::::::::::::::::::::::::::::: THE GAME BEGINS :::::::::::::::::::::::::::::::::::::::::::::::

            case "REQUESTWORD":
                payload = udpWordController.handleRequestWord(msgCli,tokenRequest);
                break;

            case "REQUESTWORDDESK":
                payload = udpWordController.handleRequestWordDesk(msgCli,tokenRequest);
                break;

            case "SENDSCORE":
                payload = udpGameController.updateScore(msgCli,tokenRequest);
                break;

            case "LISTRANKING":
                if (!tokenRequest.isBlank()){
                    if(TokenProvider.verifyJws(tokenRequest) != null){
                        payload = udpGameController.handleListRankingUser();
                    } else{
                        payload = "LISTRANKING,KO,El token no es válido";
                    }
                } else {
                    payload = "LISTRANKING,KO,El token está vacío";
                }
                break;
            
            case "RANKINGUSER":
                if (!tokenRequest.isBlank()){
                    if(TokenProvider.verifyJws(tokenRequest) != null){
                        payload = udpGameController.handleListRanking();
                    } else{
                        payload = "RANKINGUSER,KO,El token no es válido";
                    }
                } else {
                    payload = "RANKINGUSER,KO,El token está vacío";
                }
                break;

            case "LISTRANKINGFILTERED":
                if (!tokenRequest.isBlank()){
                    if(TokenProvider.verifyJws(tokenRequest) != null){
                        payload = udpGameController.handleListRankingFiltered(msgCli);
                    } else{
                        payload = "LISTRANKINGFILTERED,KO,El token no es válido";
                    }
                } else {
                    payload = "LISTRANKINGFILTERED,KO,El token está vacío";
                }

                break;
                
//::::::::::::::::::::::::::::::::::::::::::::::::::: END :::::::::::::::::::::::::::::::::::::::::::::::::::::::
            
            default:
            payload = "REQUEST,KO,Peticón no válida: "+ request;
            break;
        }
        // Enviar mensaje de respuesta
        sendResponsePayload.sendResponse(payload, outboundChannel, message);
    }
}

