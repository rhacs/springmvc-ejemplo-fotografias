package io.github.rhacs.fotografias.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rhacs.fotografias.modelos.Fotografia;
import io.github.rhacs.fotografias.repositorios.FotografiasRepositorio;

@RestController
@RequestMapping(path = "/api/fotografias", produces = MediaType.APPLICATION_JSON_VALUE)
public class FotografiasRestController {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link Logger} que contiene los métodos de depuración
     */
    private static final Logger logger = LogManager.getLogger(FotografiasRestController.class);

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link FotografiasRepositorio} que contiene los métodos de
     * manipulación y consulta para el repositorio de las {@link Fotografia}s
     */
    @Autowired
    private FotografiasRepositorio fotografiasRepositorio;

    // Solicitudes GET
    // -----------------------------------------------------------------------------------------

    /**
     * Muestra el listado de {@link Fotografia}s almacenadas en el repositorio
     * 
     * @return un objeto {@link List} con la respuesta a la solicitud
     */
    @GetMapping
    public List<Fotografia> buscarTodas() {
        // Buscar todos los registros en el repositoro y ordenarlos por id
        List<Fotografia> fotografias = fotografiasRepositorio.findAll(Sort.by("id"));

        // Depuración
        logger.info("[API] Mostrando listado de Fotografias");

        // Devolver objeto
        return fotografias;
    }

}
