package io.github.rhacs.fotografias.controladores;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.rhacs.fotografias.modelos.Categoria;
import io.github.rhacs.fotografias.modelos.Fotografia;
import io.github.rhacs.fotografias.repositorios.CategoriasRepositorio;
import io.github.rhacs.fotografias.repositorios.FotografiasRepositorio;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link Logger} que contiene los métodos de depuración
     */
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link FotografiasRepositorio} que contiene los métodos de
     * manipulación y consulta del repositorio de {@link Fotografia}s
     */
    @Autowired
    private FotografiasRepositorio fotografiasRepositorio;

    /**
     * Objeto {@link CategoriasRepositorio} que contiene los métodos de manipulación
     * y consulta del repositorio de {@link Categoria}s
     */
    @Autowired
    private CategoriasRepositorio categoriasRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Fotografia}s
     * 
     * @param modelo objeto {@link Model} que contiene el modelo de la vista
     * @return un objeto {@link String} con la respuesta a la solicitud
     */
    @GetMapping
    public String mostrarListado(Model modelo) {
        // Obtener listado de todas las fotografias
        List<Fotografia> fotografias = fotografiasRepositorio.findAll(Sort.by(Direction.DESC, "fechaCreacion"));

        // Obtener listado de Categorías
        List<Categoria> categorias = categoriasRepositorio.findAll(Sort.by("nombre"));

        // Agregar objetos al modelo
        modelo.addAttribute("fotografias", fotografias);
        modelo.addAttribute("categorias", categorias);

        // Depuración
        logger.info("[WEB] Mostrando listado de todas las fotografías del repositorio");

        // Devolver vista
        return "home";
    }

    /**
     * Muestra el listado de {@link Fotografia}s pertenecientes a la
     * {@link Categoria} seleccionada
     * 
     * @param id     identificador numérico de la {@link Categoria}
     * @param modelo objeto {@link model} que contiene el modelo de la vista
     * @return un objeto {@link String} que contiene el nombre de la vista
     */
    @GetMapping(path = "/categoria/{id:^[0-9]+$}")
    public String filtrarPorCategoria(@PathVariable Long id, Model modelo) {
        // Buscar información de la categoría
        Optional<Categoria> categoria = categoriasRepositorio.findById(id);

        // Verificar si existe
        if (categoria.isPresent()) {
            // Buscar todas las fotografías de la categoría
            List<Fotografia> fotografias = fotografiasRepositorio.findByCategoria(categoria.get());

            // Agregar listado al modelo
            modelo.addAttribute("fotografias", fotografias);

            // Devolver vista
            return "home";
        }

        // Redireccionar
        return "redirect:/?nid=" + id;
    }

}
