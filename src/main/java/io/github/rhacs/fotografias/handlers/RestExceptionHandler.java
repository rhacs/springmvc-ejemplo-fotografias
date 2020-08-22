package io.github.rhacs.fotografias.handlers;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.rhacs.fotografias.excepciones.IdentifierMismatchException;
import io.github.rhacs.fotografias.excepciones.UniqueConstraintViolationException;
import io.github.rhacs.fotografias.modelos.ErrorResponse;

@RestControllerAdvice(basePackages = "io.github.rhacs.fotografias.api")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Procesa la información de la {@link Exception} y genera una respuesta
     * 
     * @param exception objeto {@link Exception} que contiene la información del
     *                  error
     * @param request   objeto {@link HttpServletRequest} que contiene la
     *                  información de la solicitud que le envía el cliente al
     *                  servlet
     * @param status    objeto {@link HttpStatus} que contiene el estado HTTP para
     *                  la respuesta
     * @return un objeto {@link ResponseEntity} con la respuesta
     */
    private ResponseEntity<ErrorResponse> handleSimpleException(Exception exception, HttpServletRequest request,
            HttpStatus status) {
        // Obtener el mensaje de error
        String mensaje = exception.getLocalizedMessage() == null ? exception.getMessage()
                : exception.getLocalizedMessage();

        // Crear nueva respuesta
        ErrorResponse respuesta = new ErrorResponse();

        // Poblar respuesta
        respuesta.setMessage(mensaje);
        respuesta.setPath(request.getRequestURI());
        respuesta.setRequestMethod(request.getMethod());
        respuesta.setStatus(status.value());

        // Devolver respuesta
        return ResponseEntity.status(status).body(respuesta);
    }

    // Handlers
    // -----------------------------------------------------------------------------------------

    /**
     * Maneja las excepciones {@link NoSuchElementException}
     * 
     * @param e       objeto {@link NoSuchElementException} que contiene la
     *                información del error
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le envía el cliente al servlet
     * @return un objeto {@link ResponseEntity} con la respuesta
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<ErrorResponse> handleNoSuchElement(NoSuchElementException e, HttpServletRequest request) {
        return handleSimpleException(e, request, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja las excepciones {@link UniqueConstraintViolationException}
     * 
     * @param e       objeto {@link UniqueConstraintViolationException} que contiene
     *                la información del error
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le envía el cliente al servlet
     * @return un objeto {@link ResponseEntity} con la respuesta
     */
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(value = { UniqueConstraintViolationException.class })
    public ResponseEntity<ErrorResponse> handleUniqueConstraintViolation(UniqueConstraintViolationException e,
            HttpServletRequest request) {
        return handleSimpleException(e, request, HttpStatus.CONFLICT);
    }

    /**
     * Menaje las excepciones {@link IdentifierMismatchException}
     * 
     * @param e       objeto {@link IdentifierMismatchException} que contiene la
     *                información del error
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud
     * @return un objeto {@link ResponseEntity} con la respuesta
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IdentifierMismatchException.class })
    public ResponseEntity<ErrorResponse> handleIdentifierMismatch(IdentifierMismatchException e,
            HttpServletRequest request) {
        return handleSimpleException(e, request, HttpStatus.BAD_REQUEST);
    }

}
