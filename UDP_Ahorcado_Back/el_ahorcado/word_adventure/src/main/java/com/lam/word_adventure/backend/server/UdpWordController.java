package com.lam.word_adventure.backend.server;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lam.word_adventure.backend.UDP.utils.StringUtils;
import com.lam.word_adventure.backend.UDP.utils.TokenProvider;
import com.lam.word_adventure.backend.exceptions.WordServiceException;
import com.lam.word_adventure.backend.mapper.WordMapper;
import com.lam.word_adventure.backend.request.WordRegisterRequest;
import com.lam.word_adventure.backend.responses.WordResponse;
import com.lam.word_adventure.backend.services.WordService;

import io.jsonwebtoken.lang.Collections;

/**
 * Clase para controlar la gestión de palabras
 * @author Laura Arvez
 */
@Component
public class UdpWordController{

    String payload;

    @Autowired
    private WordService wordService;

    
    /**
     * constructor por defecto
     */
    public UdpWordController() {
    }

    /**
     * registra palabra
     * @param msgCli cliente request
     * @param tokenRequest token recibido
     * @return Strign payload "REGISTERWORD,OK" o "REGISTERWORD,KO,motivo"
     */
    public String handleRegisterWord(String []msgCli, String tokenRequest){

            try{
                if (tokenRequest.isEmpty() || TokenProvider.verifyJws(tokenRequest) != null){
                    WordRegisterRequest wordRequest = new WordRegisterRequest();
                    wordRequest.setWord(StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim()));
                    //(StringUtils.normalizeString(msgCli[2].trim()));
                    System.out.println(wordRequest.getWord());
                    wordRequest.setLevel(StringUtils.normalizeAndRemoveDiacritics(msgCli[3].trim()));
                    System.out.println(wordRequest.getLevel());
                    wordRequest.setTematica(StringUtils.normalizeAndRemoveDiacritics(msgCli[4].trim()));
                    System.out.println(wordRequest.getTematica());
                    
                    // registrar palabra
                    if(wordService.createWord(wordRequest)!= null){
                        payload = "REGISTERWORD,OK";
                    } else {
                        payload = "REGISTERWORD,KO,Error al registrar la palabra";
                    }
                } else {
                    //token no válido
                    payload = "REGISTERWORD,KO,Token no válido";
                }
            } catch (IllegalArgumentException e){
                payload = "REGISTERWORD,KO,"+e.getMessage();
            } catch (Exception e){
                payload = "REGISTERWORD,KO,La palabra ya existe en la base de datos.";
            }
        
            return payload;
        
    }

    /**
     * listaPalabras: String palabras separadas con ; y sus campos con :
     * @param tokenRequest token recibido
     * @return String payload: LISTWORDS,OK, listadoDePalabras / LISTWORDS,KO,motivo
     */
    public String handleListWord(String tokenRequest){
        payload = "";
        try{
            //verifica token
            String responseList;
            if(TokenProvider.verifyJws(tokenRequest) != null){
                //obtener la lista de usuarios sin el password
                List <WordResponse> wordList = wordService.getAllWords().stream()
                    .map(WordMapper::toResponse)
                    .collect(Collectors.toList());

                if(!wordList.isEmpty()){
                    //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                    responseList = wordService.formatWordList(wordList);
                    payload = "LISTWORDS,OK,"+ responseList;
                } else{
                    payload = "LISTWORDS,OK,no hay palabras en la base de datos";
                }
            } else{
                payload = "LISTWORDS,KO,El token no es válido";
            }
        } catch (Exception e) {
            //cualquier excepción
            String errorMessage = "Se ha producido un error al procesar la solicitud de LISTWORDS " + e.getMessage();
            payload = "LISTWORDS,KO," + errorMessage;
        }

        return payload;
    }

    /**
     * lista palabras con filtro: filterType puede ser dificultad o tematica
     * data es el dato “tecnologia”, “facil”
     * @param msgCli string de request
     * @param tokenRequest  token recibido
     * @return String payload: LISTWORDSFILTERED,, OK, listaUsuarios  / LISTWORDSFILTERED,KO,motivo
     */
    public String handleListWordsFiletered(String [] msgCli, String tokenRequest){
        payload = "";
        // Verificar token
        if (TokenProvider.verifyJws(tokenRequest) != null) {
            try{
                //Nomarlizar: mayúsculas/minúsculas correctamente aplicadas y sin acentos
                String filterType = StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim());
                String dataFilter = StringUtils.normalizeAndRemoveDiacritics(msgCli[3].trim());
                

                List<WordResponse> wordList = Collections.emptyList();
                String responseList;
                payload ="";
                switch (filterType) {
                    case "Dificultad":
                        wordList = wordService.getWordsByLevel(dataFilter);
                        break;
                    case "Tematica":
                        wordList = wordService.getWordsByThema(dataFilter);
                        break;
                    default:
                        return payload = "LISTWORDSFILTERED,KO,Tipo de filtro no válido: "+ filterType;
                }

                if(!wordList.isEmpty()){
                    //fotmatear la lista para que devuelva: String que separa los usuarios con ; y sus campos con :
                    responseList = wordService.formatWordList(wordList);
                    payload = "LISTWORDSFILTERED,OK," + responseList.toString();
                }else{
                    if("Dificultad".equals(filterType)){
                        payload = "LISTWORDSFILTERED,KO,No se han encontrado palabras para esta dificultad";
                    } else if("Tematica".equals(filterType)){
                        payload = "LISTWORDSFILTERED,KO,No se han encontrado palabras para esta temática";
                    }
                }
            } catch (Exception e){
                payload = "LISTWORDSFILTERED,KO,"+e.getMessage() ;
            }
        } else{
            //token no válido
            payload = "LISTWORDSFILTERED,KO,Token no válido.";
        }
        return payload;
    }
    /**
     * elimina palabra de la base de datos
     * @param msgCli string solicitud de request del cliente
     * @param tokenRequest token recibido
     * @return String de respuesta
     */
    public String handleDeleteWord(String [] msgCli, String tokenRequest){
        String word = StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim());
        System.out.println(word);
        //verificar token
        if(TokenProvider.verifyJws(tokenRequest)!=null){
            try{
                boolean ok = wordService.deleteWord(word);
                if(ok){
                    payload = "DELETEWORD,OK" ;
                } else{
                    payload = "DELETEWORD,KO,Palabra proporcionada no existe en la base de datos" ;
                }
            } catch (Exception e){
                // cualquier otra excepción
                payload = "DELETEWORD,KO,"+e.getMessage();
            }
        } else{
            //token no válido
            payload = "DELETEWORD,KO,Token no válido" ;
        }

        return payload;
            
    }

    //:::::::::::::::::::::::::::::::::::::::::::::  THE GAME BEGINS  :::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * recibe dificultad y verifica si existe
     * recibe tema y verifica si existe
     * busca palabra aleatoriamente en ese rango de dificultad y tema
     * @param msgCli solicitud recibida del cliente
     * @param tokenRequest token recibido del cliente
     * @return String con la respuesta
     */
    public String handleRequestWord(String []msgCli, String tokenRequest){

        if (TokenProvider.verifyJws(tokenRequest) != null) {
            try{
                //Nomarlizar: mayúsculas/minúsculas correctamente aplicadas y sin acentos
                String level = StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim());
                System.out.println(level);
                String thema = StringUtils.normalizeAndRemoveDiacritics(msgCli[3].trim());
                System.out.println(thema);
                
                //response palabra aleatoria
                payload = wordService.requestWordToPlay(level,thema);
            } catch (WordServiceException.ThemeNotFoundException we){
                payload = "REQUESTWORD,KO,"+ we.getMessage();
            } catch (WordServiceException.DifficultyNotFoundException we){
                payload = "REQUESTWORD,KO,"+ we.getMessage();
            } catch (Exception e){
                payload = "REQUESTWORD,KO,Error inesperado: "+e.getMessage();
            }
        } else {
            //token no válido
            payload = "REQUESTWORD,KO,Token no válido.";
        }
        return payload;
    }

    /**
     * recibe dificultad y verifica si existe
     * recibe tema y verifica si existe
     * busca palabra aleatoriamente en ese rango de dificultad y tema
     * para app de escritorio
     * @param msgCli solicitud recibida del cliente
     * @param tokenRequest token del cliente
     * @return String de respuesta
     */
    public String handleRequestWordDesk(String []msgCli, String tokenRequest){

        if (TokenProvider.verifyJws(tokenRequest) != null) {
            try{
                //Nomarlizar: mayúsculas/minúsculas correctamente aplicadas y sin acentos
                String level = StringUtils.normalizeAndRemoveDiacritics(msgCli[2].trim());
                System.out.println(level);
                String thema = StringUtils.normalizeAndRemoveDiacritics(msgCli[3].trim());
                System.out.println(thema);
                
                //response palabra aleatoria
                payload = wordService.requestWordForDesktop(level,thema);
            } catch (WordServiceException.ThemeNotFoundException we){
                payload = "REQUESTWORDDESK,KO,"+ we.getMessage();
            } catch (WordServiceException.DifficultyNotFoundException we){
                payload = "REQUESTWORDDESK,KO,"+ we.getMessage();
            } catch (Exception e){
                payload = "REQUESTWORDDESK,KO,Error inesperado: "+e.getMessage();
            }
        } else {
            //token no válido
            payload = "REQUESTWORDDESK,KO,Token no válido.";
        }
        return payload;
    }
}