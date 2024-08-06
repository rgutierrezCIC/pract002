package es.cic.pract002.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.pract002.model.Documento;
import es.cic.pract002.model.Expediente;
import es.cic.pract002.repository.ExpedienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SpringBootTest
class ExpedienteServiceIntregrationTest {


	@Autowired
	private ExpedienteRepository expedienteRepository;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ExpedienteService expedienteService;

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
		//entityManager.remove(expediente);
	}


	@Test
	public void testListar() throws Exception {
		List<Expediente> res = expedienteService.listar();

		assertTrue(res.size() >= 1, "No existe ni el registro que yo quería");
	}

	@Test
	public void testActualizarCambioNombreBorraDocumento() throws Exception {
		Expediente expedienteLeido = new Expediente();
		expedienteLeido.setId(expediente.getId());
		expedienteLeido.setNombre("Ninguno");

		expedienteService.actualizar(expedienteLeido);
	}

	@Test
	public void testActualizarActualizarDocumento() throws Exception {
		Expediente expedienteLeido = new Expediente();
		expedienteLeido.setId(expediente.getId());
		expedienteLeido.setNombre("Ninguno");

		expedienteService.actualizar(expedienteLeido);
	}
}
