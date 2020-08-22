package io.github.rhacs.fotografias.modelos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "error")
public class ErrorResponse {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Instante de tiempo en que ocurrió el error
     */
    private LocalDateTime timestamp;

    /**
     * Estado HTTP de la respuesta
     */
    private int status;

    /**
     * Detalle del error
     */
    private String message;

    /**
     * URI donde ocurrió la excepción
     */
    private String path;

    /**
     * Método de la solicitud (GET, POST, PUT, DELETE, ...)
     */
    private String requestMethod;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link ErrorResponse}
     */
    public ErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Crea una nueva instancia del objeto {@link ErrorResponse}
     * 
     * @param status        código del estado HTTP
     * @param message       detalles del error
     * @param path          URI donde ocurrió el error
     * @param requestMethod método de la solicitud (GET, POST, PUT, DELETE, ...)
     */
    public ErrorResponse(int status, String message, String path, String requestMethod) {
        this();

        this.status = status;
        this.message = message;
        this.path = path;
        this.requestMethod = requestMethod;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el instante de tiempo que indica cuándo ocurrió el error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return el código de estado HTTP
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return el detalle del error
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return la URI
     */
    public String getPath() {
        return path;
    }

    /**
     * @return el método que se utilizó en la solicitud
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param status el código de estado HTTP a establecer
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param message el detalle del error a establecer
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param path la ruta a establecer
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @param requestMethod el método de la solicitud a establecer
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("ErrorResponse [timestamp=%s, status=%s, message=%s, path=%s, requestMethod=%s]",
                timestamp, status, message, path, requestMethod);
    }

}
