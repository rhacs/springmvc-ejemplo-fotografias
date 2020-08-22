package io.github.rhacs.fotografias.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@WebAppConfiguration
@TestMethodOrder(value = OrderAnnotation.class)
class CategoriasRestControllerTest {

    private MockMvc mvc;

    private Long id;

    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    // obtenerTodas()
    // -----------------------------------------------------------------------------------------

    @Test
    @Order(value = 1)
    void obtenerTodasShouldReturnAList() throws Exception {
        mvc
                // Simular petición GET a la API
                .perform(get("/api/categorias"))
                // Esperar a que el estado de la respuesta sea HttpStatus.OK
                .andExpect(status().isOk())
                // Esperar a que el tipo de contenido de la respuesta sea un application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que el listado tenga un objeto con el atributo "nombre" y su valor
                // sea "Viajes"
                .andExpect(jsonPath("$[0].nombre").value("Viajes"));
    }

    // obtenerUna()
    // -----------------------------------------------------------------------------------------

    @Test
    @Order(value = 2)
    void obtenerUnaShouldReturnAnObject() throws Exception {
        int id = 4;

        mvc
                // Simular petición GET a la API
                .perform(get("/api/categorias/{id}", id))
                // Esperar a que el estado de la respuesta sea HttpStatus.OK
                .andExpect(status().isOk())
                // Esperar a que el tipo de contenido sea application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que la respuesta tenga un atributo "id" con el valor especificado
                .andExpect(jsonPath("$.id").value(id))
                // Esperar a que la respuesta tenga un atributo "nombre" con el valor "Conciertos"
                .andExpect(jsonPath("$.nombre").value("Conciertos"));
    }

    @Test
    @Order(value = 3)
    void obtenerUnaShouldReturnAnException() throws Exception {
        int id = 1000;

        mvc
                // Simular petición GET errónea a la API
                .perform(get("/api/categorias/{id}", id))
                // Esperar que el estado de la respuesta sea HttpStatus.INTERNAL_SERVER_ERROR
                .andExpect(status().isNotFound())
                // Esperar a que la excepción lanzada sea NoSuchElementException
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoSuchElementException))
                // Esperar a que el mensaje de la excepción contenga el identificador
                .andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains(Integer.toString(id))));
    }

    // agregarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    @Order(value = 4)
    void agregarRegistroShouldPersist() throws Exception {
        // Establecer nombre de la nueva categoría
        String nombre = "Pruebita";

        mvc
                // Simular petición POST a la API
                .perform(
                        post("/api/categorias")
                        // Establecer el tipo de contenido
                        .contentType(MediaType.APPLICATION_JSON)
                        // Establecer el juego de caracteres
                        .characterEncoding("utf-8")
                        // Establecer el contenido de la solicitud
                        .content("{\"nombre\": \"" + nombre + "\"}")
                )
                // Esperar que el estado de la respuesta sea HttpStatus.CREATED
                .andExpect(status().isCreated())
                // Esperar a que el tipo de contenido de la respuesta sea application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que el objeto devuelto tenga un atributo "id"
                .andExpect(jsonPath("$.id").exists())
                // Esperar a que el objeto tenga un atributo "nombre" y su valor sea igual al que se le entregó
                .andExpect(jsonPath("$.nombre").value(nombre))
                .andDo(handler -> {
                    // Obtener respuesta
                    String respuesta = handler.getResponse().getContentAsString();

                    // Configurar expresión regular
                    Pattern pattern = Pattern.compile("([0-9]+)");
                    Matcher matcher = pattern.matcher(respuesta);

                    // Extraer identificador
                    id = Long.parseLong(matcher.group());
                });
    }

    @Test
    @Order(value = 5)
    void agregarRegistroShouldThrowConflict() throws Exception {
        // Establecer nombre de la categoria (debe existir)
        String nombre = "Viajes";

        mvc
                // Simular petición POST
                .perform(
                        post("/api/categorias")
                        // Establecer tipo de contenido
                        .contentType(MediaType.APPLICATION_JSON)
                        // Establecer el juego de caracteres
                        .characterEncoding("utf-8")
                        // Establecer contenido
                        .content("{\"nombre\": \"" + nombre + "\"}")
                )
                // Esperar que el estado de la respuesta sea HttpStatus.CONFLICT
                .andExpect(status().isConflict())
                // Esperar que el tipo de contenido sea application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que el objeto devuelto sea un error y contenga un atributo "status" con el valor "409"
                .andExpect(jsonPath("$.error.status").value(409));
    }

    @Test
    @Order(value = 6)
    void agregarRegistroShouldThrowBadRequest() throws Exception {
        mvc
                // Simular petición POST
                .perform(post("/api/categorias"))
                // Esperar que el estado de la respuesta sea HttpStatus.BAD_REQUEST
                .andExpect(status().isBadRequest());
    }

    // editarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    @Order(value = 7)
    void editarRegistroShouldThrowConflict() throws Exception {
        mvc
                .perform(
                        put("/api/categorias/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content("{\"id\": 2, \"nombre\": \"Familia\"}")
                )
                .andExpect(status().isConflict());
    }

    @Test
    @Order(value = 8)
    void editarRegistroShouldSucceed() throws Exception {
        mvc
            .perform(
                    put("/api/categorias/{id}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .content("{\"id\": " + id + ", \"nombre\": \"Pruebosas\"}")
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.nombre").value("Pruebosas"));
    }

    @Test
    @Order(value = 9)
    void editarRegistroShouldThrowNotFound() throws Exception {
        mvc
            .perform(
                    put("/api/categorias/{id}", 100)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .content("{\"id\": 100, \"nombre\": \"Categoria prueba\"}")
            )
            .andExpect(status().isNotFound());
    }

    @Test
    @Order(value = 10)
    void editarRegistroShouldThrowBadRequest() throws Exception {
        mvc
            .perform(
                    put("/api/categorias/{id}", 2)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("utf-8")
                    .content("{\"id\": 3, \"nombre\": \"error\"}")
            )
            .andExpect(status().isBadRequest());
    }

    // eliminarRegistro()
    // -----------------------------------------------------------------------------------------

    @Test
    @Order(value = 11)
    void eliminarRegistroShouldSucceed() throws Exception {
        mvc
            .perform(delete("/api/categorias/{id}", id))
            .andExpect(status().isNoContent());
    }

    @Test
    @Order(value = 12)
    void eliminarRegistroShouldThrowNotFound() throws Exception {
        mvc
            .perform(delete("/api/categorias/{id}", 1000))
            .andExpect(status().isNotFound());
    }

}
