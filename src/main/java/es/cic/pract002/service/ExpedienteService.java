package es.cic.pract002.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.pract002.model.Expediente;
import es.cic.pract002.repository.ExpedienteRepository;

@Service
@Transactional
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Transactional(readOnly = true)
    public List<Expediente> listar() {
        return expedienteRepository.findAll();

    }
    
    @Transactional(readOnly = true)
    public Expediente leer(long id){
        return expedienteRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontré nada"));
    }
    
    
    public void actualizar (Expediente expediente){
        expedienteRepository.save(expediente);
    }



}
