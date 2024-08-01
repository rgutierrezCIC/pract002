package es.cic.pract002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Pract002Application {

	public static void main(String[] args) {
		SpringApplication.run(Pract002Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ExpedienteRepository expedienteRepository) {
		return args -> {

			Expediente expediente = new Expediente(); 
			expediente.setNombre("Juan");
			expediente.setDocumentos(null);
			expedienteRepository.save(expediente);

		};
	}
}
