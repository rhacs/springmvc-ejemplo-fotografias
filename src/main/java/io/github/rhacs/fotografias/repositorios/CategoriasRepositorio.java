package io.github.rhacs.fotografias.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.fotografias.modelos.Categoria;

@Repository
public interface CategoriasRepositorio extends JpaRepository<Categoria, Long> {

    /**
     * Busca una {@link Categoria} en el repositorio a partir del nombre
     * 
     * @param nombre nombre a buscar
     * @return un objeto {@link Optional} con el posible resultado
     */
    public Optional<Categoria> findByNombre(String nombre);

}
