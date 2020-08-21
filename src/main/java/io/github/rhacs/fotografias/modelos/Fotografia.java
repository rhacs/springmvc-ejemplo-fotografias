package io.github.rhacs.fotografias.modelos;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.rhacs.fotografias.Constantes;

@Entity
@Table(name = Constantes.TABLA_FOTOGRAFIAS)
public class Fotografia {

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Identificador numérico de la {@link Fotografia}
     */
    @Id
    @Column(name = Constantes.FOTOGRAFIAS_ID, nullable = false, unique = true, updatable = false)
    private Long id;

    /**
     * Descripción de la {@link Fotografia}
     */
    @NotEmpty
    @Size(min = 5, max = 2000)
    private String descripcion;

    /**
     * URL de la {@link Fotografia}
     */
    @NotEmpty
    @URL(protocol = "http")
    private String url;

    /**
     * Cantidad de vistas que ha tenido la {@link Fotografia}
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long vistas;

    /**
     * {@link Categoria} a la que pertenece la {@link Fotografia}
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = Constantes.CATEGORIAS_ID, referencedColumnName = Constantes.CATEGORIAS_ID)
    private Categoria categoria;

    /**
     * Instante de tiempo que indica cuándo se agregó la {@link Fotografia} a la
     * tabla
     */
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = Constantes.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    /**
     * Instante de tiempo que indica cuándo se actualizó la información de la
     * {@link Fotografia} por última vez
     */
    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = Constantes.UPDATED_AT, nullable = false)
    private LocalDateTime fechaActualizacion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia vacía de la entidad {@link Fotografia}
     */
    public Fotografia() {
        // Constructor vacío
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return el identificador numérico
     */
    public Long getId() {
        return id;
    }

    /**
     * @return la descripción
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return la url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return la cantidad de visitas
     */
    public Long getVistas() {
        return vistas;
    }

    /**
     * @return la {@link Categoria}
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @return la fecha de creación
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @return la fecha de actualización
     */
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    // Setters
    // -----------------------------------------------------------------------------------------

    /**
     * @param id el identificador numérico a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param descripcion la descripción a establecer
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param url la url a establecer
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param vistas la cantidad de visitas a establecer
     */
    public void setVistas(Long vistas) {
        this.vistas = vistas;
    }

    /**
     * @param categoria la {@link Categoria} a establecer
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @param fechaCreacion la fecha de creación a establecer
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @param fechaActualizacion la fecha de actualización a establecer
     */
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    // Herencias (Object)
    // -----------------------------------------------------------------------------------------

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Fotografia other = (Fotografia) obj;

        return Objects.equals(id, other.id) || Objects.equals(url, other.url);
    }

    @Override
    public String toString() {
        return String.format(
                "Fotografia [id=%s, descripcion=%s, url=%s, vistas=%s, categoria=%s, fechaCreacion=%s, fechaActualizacion=%s]",
                id, descripcion, url, vistas, categoria, fechaCreacion, fechaActualizacion);
    }

}
