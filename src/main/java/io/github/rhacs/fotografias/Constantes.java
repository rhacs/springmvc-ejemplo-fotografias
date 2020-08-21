package io.github.rhacs.fotografias;

public class Constantes {

    // Tablas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la tabla que contiene la información de las {@link Fotografia}s
     */
    public static final String TABLA_FOTOGRAFIAS = "fotografias";

    /**
     * Nombre de la tabla que contiene la información de las {@link Categoria}s
     */
    public static final String TABLA_CATEGORIAS = "categorias";

    // Columnas
    // -----------------------------------------------------------------------------------------

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_FOTOGRAFIAS}
     */
    public static final String FOTOGRAFIAS_ID = "fotografia_id";

    /**
     * Nombre de la llave primaria para la tabla {@value #TABLA_CATEGORIAS}
     */
    public static final String CATEGORIAS_ID = "categoria_id";

    /**
     * Nombre de la columna que guardará el valor del instante exacto de cuándo se
     * creó el registro en la tabla
     */
    public static final String CREATED_AT = "created_at";

    /**
     * Nombre de la columna que guardará el valor del instante exacto de cuándo se
     * actualizó el registro por última vez
     */
    public static final String UPDATED_AT = "updated_at";

    // Constructores
    // -----------------------------------------------------------------------------------------

    private Constantes() {
        // Constructor privado para esconder el constructor público implícito
    }

}
