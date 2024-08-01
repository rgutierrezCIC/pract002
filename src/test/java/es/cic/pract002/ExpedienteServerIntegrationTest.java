package es.cic.pract002;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExpedienteServiceIntregrationTest {

	@Autowired
	private ExpedienteRepository expedienteRepository;


	@Autowired
	private ExpedienteService expedienteService;

    private Expediente expediente;

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
	void testListar() throws Exception {
		List<Expediente> res = expedienteService.listar();

		assertTrue(res.size() >= 1, "No existe al menos el registro que yo quería");
	}



	@Test
	void testActualizar() throws Exception {
        Expediente expedienteleido = expedienteService.leer(expediente.getId());
	}
}
