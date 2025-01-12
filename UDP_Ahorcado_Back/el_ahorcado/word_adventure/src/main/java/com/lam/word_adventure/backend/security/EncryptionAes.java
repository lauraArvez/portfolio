package com.lam.word_adventure.backend.security;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

/**
 * Clase de encriptado y desencriptado utilizando el algoritmo AES
 * @author Laura Arvez
 */

@Component
public class EncryptionAes {

    private static final String SEC_KEY = "5b08c96a86e5b5fd08c786b9e6b70981";
    private static final String ALGORITHM = "AES";

    /**
     * constructor por defecto
     */
    public EncryptionAes (){
        
    }

    /**
     * cifra una cadena encriptada utilizando el algoritmo AES
     * @param input string de entrada que se va a cifrar
     * @return String
     */
    public String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            Key key = generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // convertir la cadena de entrada a bytes utilizando UTF-8
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

            // cifrado con los bytes convertidos
            byte[] encryptedBytes = cipher.doFinal(inputBytes);
            //byte[] encryptedBytes = cipher.doFinal(input.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * descifra una cadena encriptada utilizando el algoritmo AES
     * @param input string de entrada que se va a descifrar
     * @return String
     */
    public String decrypt(String input) {
        try {

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            Key key = generateKey();
            cipher.init(Cipher.DECRYPT_MODE, key);

            
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(input));

            
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * genera la clave
     * @return SecretKeySpec
     */
    private SecretKeySpec generateKey() {
        return new SecretKeySpec(SEC_KEY.getBytes(), ALGORITHM);
    }

}