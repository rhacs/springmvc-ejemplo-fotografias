package io.github.rhacs.fotografias.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rhacs.fotografias.modelos.Categoria;

@Repository
public interface CategoriasRepositorio extends JpaRepository<Categoria, Long> {

}
