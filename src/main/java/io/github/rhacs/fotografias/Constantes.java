package io.github.rhacs.fotografias;

public class Constantes {

    // Tablas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la tabla que contiene la información de las {@link Fotografia}s
     */
    public static final String TABLA_FOTOGRAFIAS = "fotografias";

    // Secuencias
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la secuencia que genera los valores de los identificadores
     * numéricos para la tabla {@value #TABLA_FOTOGRAFIAS}
     */
    public static final String SECUENCIA_FOTOGRAFIAS = TABLA_FOTOGRAFIAS + "_seq";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
