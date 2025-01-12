package com.lam.word_adventure.backend.UDP.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Clase de proporciona funciones para manipular strings, como
 * normalización y eliminación de diacríticos
 * 
 * @author Laura Arvez
 */
public class StringUtils {

    /**
     * constructor por defecto
     */
    public StringUtils(){

    }

    
    /**
     * Normaliza una cadena de texto, convirtiendo la primera letra en mayúscula y el resto en minúsculas.
     *
     * @param input la cadena de texto a normalizar
     * @return la cadena normalizada
     */
    public static String normalizeString(String input) {
        if (input == null) {
            return null;
        }
        // normalizar y convertir la primera letra en mayúsculas y el resto en minúsculas
        String normalized = removeDiacritics(input);
        return normalized.substring(0, 1).toUpperCase() 
            + normalized.substring(1).toLowerCase();
    }

    /**
     * Normaliza una cadena de texto y elimina los diacríticos.
     * 
     * @param input la cadena de texto a normalizar y procesar
     * @return la cadena normalizada sin diacríticos
     */
    public static String normalizeAndRemoveDiacritics(String input) {
        // Normalizar a minúsculas y eliminar diacríticos
        String normalized = normalizeString(input);
        String withoutDiacritics = removeDiacritics(normalized);
        return withoutDiacritics;
    }

    /**
     * Elimina los diacríticos de una cadena de texto.
     * 
     * @param input la cadena de texto de la cual se quieren eliminar los diacríticos
     * @return la cadena de texto sin diacríticos
     */
    public static String removeDiacritics(String input) {
        if (input == null) {
            return null;
        }

        // Normalizar la cadena para descomponer caracteres diacríticos
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Definir una expresión regular para eliminar diacríticos
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        // Aplicar la expresión regular para eliminar diacríticos
        return pattern.matcher(normalized).replaceAll("");
    }

}


