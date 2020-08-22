package io.github.rhacs.fotografias.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class CategoriasRestControllerTest {

    private MockMvc mvc;

    @BeforeEach
    void setUp(WebApplicationContext wac) throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void obtenerTodasShouldReturnAList() throws Exception {
        mvc
                // Simular petición GET a la API
                .perform(get("/api/categorias"))
                // Esperar a que el estado de la respuesta sea HttpStatus.OK
                .andExpect(status().isOk())
                // Esperar a que el tipo de contenido de la respuesta sea un application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que el listado tenga un objeto con el atributo "nombre" y su valor
                // sea "Familia"
                .andExpect(jsonPath("$[0].nombre").value("Familia"));
    }

    @Test
    void obtenerUnaShouldReturnAnObject() throws Exception {
        int id = 2;

        mvc
                // Simular petición GET a la API
                .perform(get("/api/categorias/{id}", id))
                // Esperar a que el estado de la respuesta sea HttpStatus.OK
                .andExpect(status().isOk())
                // Esperar a que el tipo de contenido sea application/json
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Esperar a que la respuesta tenga un atributo "id" con el valor especificado
                .andExpect(jsonPath("$.id").value(id))
                // Esperar a que la respuesta tenga un atributo "nombre" con el valor "Viajes"
                .andExpect(jsonPath("$.nombre").value("Viajes"));
    }

    @Test
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

}
