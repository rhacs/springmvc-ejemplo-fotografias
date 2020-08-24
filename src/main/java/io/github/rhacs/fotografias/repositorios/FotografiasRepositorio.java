package io.github.rhacs.fotografias.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.fotografias.modelos.Categoria;
import io.github.rhacs.fotografias.modelos.Fotografia;

@Repository
public interface FotografiasRepositorio extends JpaRepository<Fotografia, Long> {

    /**
     * Busca las {@link Fotografia}s que coincidan con la {@link Categoria}
     * proporcionada
     * 
     * @param categoria {@link Categoria} a buscar
     * @return un objeto {@link List} con el resultado
     */
    public List<Fotografia> findByCategoria(Categoria categoria);

    /**
     * Busca las {@link Fotografia}s que coincidan con la {@link Categoria}
     * proporcionada y ordena los resultados
     * 
     * @param categoria {@link Categoria} a buscar
     * @param sort      modo de ordenamiento de los resultados
     * @return un objeto {@link List} con el resultado
     */
    public List<Fotografia> findByCategoria(Categoria categoria, Sort sort);

    /**
     * Busca una {@link Fotografia} en el repositorio basada en la url proporcionada
     * 
     * @param url url de la {@link Fotografia}
     * @return un objeto {@link Optional} que puede o no contener un resultado
     */
    public Optional<Fotografia> findByUrl(String url);

}
