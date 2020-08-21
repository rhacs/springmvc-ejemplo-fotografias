package io.github.rhacs.fotografias.excepciones;

public class IdentifierMismatchException extends RuntimeException {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = 851066337236643835L;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la excepción
     * {@link IdentifierMismatchException}
     */
    public IdentifierMismatchException() {
        super();
    }

    /**
     * Crea una nueva instancia de la excepción {@link IdentifierMismatchException}
     * 
     * @param message detalle del error
     */
    public IdentifierMismatchException(String message) {
        super(message);
    }

}
