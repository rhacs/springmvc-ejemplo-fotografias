package io.github.rhacs.fotografias.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.fotografias.modelos.Categoria;
import io.github.rhacs.fotografias.repositorios.CategoriasRepositorio;

@RestController
@RequestMapping(path = "/api/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriasRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link Logger} que contiene los métodos para depurar
     */
    private static final Logger logger = LogManager.getLogger(CategoriasRestController.class);

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link CategoriasRepositorio} que contiene los métodos de manipulación
     * para el repositorio de {@link Categoria}s
     */
    @Autowired
    private CategoriasRepositorio repositorio;

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Depura el método y url de la solicitud
     * 
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le envía el cliente al servlet
     */
    private void depurarSolicitud(HttpServletRequest request) {
        logger.info("[API] Solicitud {}: {}", request.getMethod(), request.getRequestURL());
    }

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el contenido almacenado en el repositorio
     * 
     * @param request objeto {@link HttpServletRequest} que contiene la información
     *                de la solicitud que le envía el cliente al servlet
     * @return un objeto {@link List} con la respuesta a la solicitud
     */
    @GetMapping
    public List<Categoria> obtenerTodas(HttpServletRequest request) {
        // Depuración
        depurarSolicitud(request);

        // Buscar todos los registros en el repositorio
        List<Categoria> categorias = repositorio.findAll();

        // Depuración
        logger.info("[API] Listado de Categorias: {}", categorias);

        // Devolver listado
        return categorias;
    }

}
