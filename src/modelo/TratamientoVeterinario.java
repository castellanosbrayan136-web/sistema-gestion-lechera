package modelo;

//@autor: Brayan C

import java.time.LocalDate;


public class TratamientoVeterinario {
    private Vaca vaca;
    private String tipo;
    private String medicamento;
    private String dosis;
    private LocalDate fecha;
    private String observaciones;

    public TratamientoVeterinario(Vaca vaca, String tipo, String medicamento, String dosis, LocalDate fecha, String observaciones) {
        this.tipo = tipo;
        this.medicamento = medicamento;
        this.dosis = dosis;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }
    
    public String getTipo() {
        return tipo;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public String getDosis() {
        return dosis;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Vaca getVaca() {
        return vaca;
    }
}
