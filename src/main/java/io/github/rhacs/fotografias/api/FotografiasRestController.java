package io.github.rhacs.fotografias.api;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(code = HttpStatus.OK)
    public List<Fotografia> buscarTodas() {
        // Buscar todos los registros en el repositoro y ordenarlos por id
        List<Fotografia> fotografias = fotografiasRepositorio.findAll(Sort.by("id"));

        // Depuración
        logger.info("[API] Mostrando listado de Fotografias");

        // Devolver objeto
        return fotografias;
    }

    /**
     * Muestra la información de una {@link Fotografia}
     * 
     * @param id identificador numérico de la {@link Fotografia}
     * @return un objeto {@link Fotografia} con la respuesta a la solicitud
     */
    @GetMapping(path = "/{id:^[0-9]+$}")
    @ResponseStatus(code = HttpStatus.OK)
    public Fotografia buscarUna(@PathVariable Long id) {
        // Buscar información de la fotografía
        Optional<Fotografia> fotografia = fotografiasRepositorio.findById(id);

        // Verificar si existe
        if (fotografia.isPresent()) {
            // Depuración
            logger.info("[API] Mostrando información de la Fotografía: {}", fotografia.get());

            // Devolver objeto
            return fotografia.get();
        }

        // Depuración
        logger.warn("[API] La Fotografía con el identificador '{}' no existe", id);

        // Lanzar excepción
        throw new NoSuchElementException(String.format("La Fotografía con el identificador '%s' no existe", id));
    }

}
