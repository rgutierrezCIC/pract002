package es.cic.pract002;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Documento {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name= "expediente_id")
    private Expediente expediente;


    // getters y setters
    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String tituloString) {
        this.descripcion = tituloString;
    }


    public Expediente getExpediente() {
        return expediente;
    }


    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
 
}
