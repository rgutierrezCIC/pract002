package es.cic.pract002.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.pract002.model.Documento;
import es.cic.pract002.model.Expediente;
import es.cic.pract002.repository.ExpedienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
@AutoConfigureMockMvc
class ExpedienteControllerIntregrationTest {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Expediente expediente;

    @BeforeEach
    public void setUp() {
        expediente = new Expediente();
        expediente = expedienteRepository.save(expediente);

        Documento documento = new Documento();
        documento.setDescripcion("Hola");
        documento.setExpediente(expediente);

        expediente.getDocumentos().add(documento);
        expedienteRepository.save(expediente);
        expedienteRepository.flush();
    }

    @AfterEach
    public void tearDown() {
        // entityManager.remove(saludo);
    }

    @Test
    public void testLeer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/expediente/{1}", expediente.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expediente.getId()))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        Expediente expedienteResultado = objectMapper.readValue(body, Expediente.class);

        assertTrue(expedienteResultado.getDocumentos().size() >= 1);
    }

    @Test
    public void testLeer_no_encontrado() throws Exception {
        mockMvc.perform(get("/api/expediente/{1}", expediente.getId()*5)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
