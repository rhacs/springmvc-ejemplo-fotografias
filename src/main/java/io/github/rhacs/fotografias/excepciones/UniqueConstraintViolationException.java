package io.github.rhacs.fotografias.excepciones;

public class UniqueConstraintViolationException extends RuntimeException {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = -1994775886964890850L;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la excepción {@link UniqueConstraintViolationException}
     */
    public UniqueConstraintViolationException() {
        super();
    }

    /**
     * Crea una nueva instancia de la excepción {@link UniqueConstraintViolationException}
     * 
     * @param mensaje detalles del error
     */
    public UniqueConstraintViolationException(String mensaje) {
        super(mensaje);
    }

}
