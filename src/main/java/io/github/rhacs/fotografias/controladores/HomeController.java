package io.github.rhacs.fotografias.controladores;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
