package com.lam.word_adventure.backend.responses;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
//import com.lam.word_adventure.backend.security.EncryptionAes;

/**
 * clase para enviar mensaje de respuesta y dattos encriptados
 *
 * @author Laura Arvez
 */
@Component
public class SendResponsePayload {

    /*@Autowired
    EncryptionAes encryptionAes;*/

    
    /**
     * constructor por defecto de SendResponsePayload
     */
    public SendResponsePayload() {
    }

    /**
     * Método para enviar el mensaje de respuesta
     *
     * @param payload          el contenido del mensaje
     * @param outboundChannel  el canal de salida donde se enviará el mensaje
     * @param message          el mensaje original del cual se copian las cabeceras
     */
    public void sendResponse(String payload, MessageChannel outboundChannel, Message <?> message) {
        //contrucción original sin encriptar
        Message<?> responseMessage = MessageBuilder.withPayload(payload.getBytes(StandardCharsets.UTF_8))
            .copyHeaders(message.getHeaders())
            .build();

        outboundChannel.send(responseMessage);
    }

    /**
     * Envía datos encriptados
     * @param data    los datos a encriptar y enviar
     * @param socket  el socket UDP a utilizar para enviar los datos
     * @param host    el host de destino al cual enviar los datos
     * @param port    el puerto de destino al cual enviar los datos
     */
   /*  public void sendEncryptedData(String data, DatagramSocket socket, String host, int port) {
        try {
            String encryptedData = encryptionAes.encrypt(data);
            byte[] sendData = encryptedData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(host), port);
            socket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * recibe datos encriptados y los decodifica
     * 
     * @param socket  el socket UDP desde el cual recibir los datos
     * @return        los datos recibidos y decodificados
     */
    /*public String receiveEncryptedData(DatagramSocket socket) {
        try {
            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            return encryptionAes.decrypt(receivedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

}
