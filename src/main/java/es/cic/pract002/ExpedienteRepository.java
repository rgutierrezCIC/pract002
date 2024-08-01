package es.cic.pract002;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpedienteRepository extends JpaRepository<Expediente, Long>  {

    @Query(value = "select e from Expediente e where e.nombre = :nombre")
    public List<Expediente> findByNombre(String nombre);
}
