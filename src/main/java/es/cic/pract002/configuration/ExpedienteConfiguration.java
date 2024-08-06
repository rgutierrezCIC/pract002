package es.cic.pract002.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/los.properties")
public class ExpedienteConfiguration {
    @Value("${pract002.expediente.valor}")
    private long valor;

    public long getValor(){
        return valor;
    }

    public void setValor(long valor){
        this.valor = valor;
    }

}
