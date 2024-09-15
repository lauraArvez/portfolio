package com.example.demo.excepciones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.demo.responses.exceptionResponses.ApiDemoResponse;

/**
 * Controlador global de excepciones. Proporciona un mecanismo centralizado para manejar excepciones en toda la aplicación.
 * Las excepciones manejadas incluyen errores comunes como 404, 400 y 500.
 * Utiliza la anotación @RestControllerAdvice para aplicar este comportamiento en toda la aplicación.
 * 
 * @author Laura Arvez
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejador global de excepciones genéricas (HTTP 500).
     * 
     * @param exception La excepción capturada.
     * @param webRequest El objeto que contiene la solicitud web.
     * @return ResponseEntity con la respuesta personalizada y el código de estado HTTP 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiDemoResponse> handlerException(Exception exception, WebRequest webRequest) {
        ApiDemoResponse apiResponse = new ApiDemoResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Manejador de excepciones para recursos no encontrados (HTTP 404).
     * 
     * @param exception La excepción capturada.
     * @param webRequest El objeto que contiene la solicitud web.
     * @return ResponseEntity con la respuesta personalizada y el código de estado HTTP 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiDemoResponse> handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ApiDemoResponse apiResponse = new ApiDemoResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Manejador de excepciones de validación de argumentos (HTTP 400).
     * Captura errores relacionados con la validación de campos en las solicitudes.
     * 
     * @param exception La excepción capturada.
     * @param webRequest El objeto que contiene la solicitud web.
     * @return ResponseEntity con los errores de validación y el código de estado HTTP 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        Map<String, String> mapErrores = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
                    String clave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();
                    mapErrores.put(clave, valor);
                }
        );
        ApiDemoResponse apiResponse = new ApiDemoResponse(mapErrores.toString(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejador de excepciones para rutas no encontradas (HTTP 404).
     * 
     * @param exception La excepción capturada.
     * @param webRequest El objeto que contiene la solicitud web.
     * @return ResponseEntity con la respuesta personalizada y el código de estado HTTP 404.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiDemoResponse> handlerNoHandlerFoundException(NoHandlerFoundException exception, WebRequest webRequest) {
        ApiDemoResponse apiResponse = new ApiDemoResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Manejador de excepciones para solicitudes incorrectas (HTTP 400).
     * 
     * @param exception La excepción capturada.
     * @param webRequest El objeto que contiene la solicitud web.
     * @return ResponseEntity con la respuesta personalizada y el código de estado HTTP 400.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiDemoResponse> handlerBadRequestException(BadRequestException exception, WebRequest webRequest) {
        ApiDemoResponse apiResponse = new ApiDemoResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}