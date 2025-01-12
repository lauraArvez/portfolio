package com.lam.word_adventure.backend.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.IpHeaders;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
//import org.springframework.integration.support.MessageBuilder;

//import com.lam.word_adventure.backend.security.EncryptionAes;

/**
 * esta clase maneja los mensajes de salida enviados al canal outboundChannel,
 * extrae la información de dirección IP y puerto de origen del mensaje,
 * y luego utiliza un UnicastSendingMessageHandler para
 * enviar el mensaje UDP al destino correspondiente.
 * @author Laura Arvez
 */
@MessageEndpoint
public class UdpOutBoundMessageHandler {

    /* objeto Logger estático para registrar información y
     * depurar el comportamiento del manejador de mensajes de salida.*/
    private final static Logger LOGGER = LoggerFactory.getLogger(UdpOutBoundMessageHandler.class);

    /*@Autowired
    private EncryptionAes encryptionAes;*/

    
    /**
     * Constructor por defecto de UdpOutBoundMessageHandler
     */
    public UdpOutBoundMessageHandler() {
    }


    /**
     * Método: maneja el mensaje saliente que se envía al canal de salida. Recibe un único parámetro:
     * @param message El mensaje que se va a enviar.
     */
    @ServiceActivator(inputChannel = "outboundChannel")
    public void handleOutboundMessage(Message<String> message) {
        // Se obtienen los encabezados del mensaje para extraer información adicional,
        //como la dirección IP y el puerto de origen.
        MessageHeaders headers = message.getHeaders();

        // Se extrae la dirección IP y el puerto de origen del mensaje
        //utilizando las constantes definidas en IpHeaders.
        String sourceIp = (String) headers.get(IpHeaders.IP_ADDRESS);
        int sourcePort = (Integer) headers.get(IpHeaders.PORT);

        //obtiene el payload del mensaje
        //String payloadToEncrypt = message.getPayload();

        //encripta del payload
        //String encryptedPayload = encryptionAes.encrypt(payloadToEncrypt);


        /*Se registra un mensaje informativo en el registro, mostrando el contenido del mensaje UDP que
        se va a enviar, junto con la dirección IP y el puerto de destino. */
        LOGGER.info("Sending UDP payload: {}, to IP: {}, port: {}", message.getPayload(), sourceIp,
                sourcePort);

        // Se crea un nuevo UnicastSendingMessageHandler con la dirección IP y
        //el puerto de destino obtenidos del mensaje.
        UnicastSendingMessageHandler handler = new UnicastSendingMessageHandler(sourceIp, sourcePort);

        /* Se llama al método handleMessage() del UnicastSendingMessageHandler,
        pasando el mensaje original como argumento. Este método se encargará de
        enviar el mensaje UDP al destino especificado.*/
        handler.handleMessage(message);

        //construir el nuevo mensaje con el payload encriptado
       /* Message<String> encryptedMessage = MessageBuilder.withPayload(encryptedPayload)
        .copyHeaders(message.getHeaders())
        .build();*/

        //llamar al método handleMessage y pasamos el mensaje encriptado
        //handler.handleMessage(encryptedMessage);

    }
}
