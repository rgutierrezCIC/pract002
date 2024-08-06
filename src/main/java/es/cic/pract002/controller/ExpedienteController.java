package es.cic.pract002.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.pract002.model.Expediente;
import es.cic.pract002.service.ExpedienteService;

@RestController
@RequestMapping("/api/expediente")
public class ExpedienteController {

    @Value("${pract002.expediente.valor}")
    private long valor;
    
    @Autowired
    private ExpedienteService expedienteService;

    @GetMapping("/{id}")
    public Expediente leer(@PathVariable("id") long id)  {
        Expediente res = expedienteService.leer(id);
        return res;
    }

}
