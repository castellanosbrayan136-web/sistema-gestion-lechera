package modelo;

//@autor: Brayan C

import java.time.LocalDate;


public class Bovino {
    private String codigoInterno;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String razaPadre;
    private String razaMadre;
    private String sexo;
    private String estadoProduccion;
    private String numeroIdentificador;
    private Double peso;

    public Bovino(String codigoInterno, String nombre, LocalDate fechaNacimiento, String razaPadre, String razaMadre, String sexo, String estadoProduccion, String numeroIdentificador, Double peso) {
        this.codigoInterno = codigoInterno;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.razaPadre = razaPadre;
        this.razaMadre = razaMadre;
        this.sexo = sexo;
        this.estadoProduccion = estadoProduccion;
        this.numeroIdentificador = numeroIdentificador;
        this.peso = peso;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getRazaPadre() {
        return razaPadre;
    }

    public String getRazaMadre() {
        return razaMadre;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstadoProduccion() {
        return estadoProduccion;
    }

    public String getNumeroIdentificador() {
        return numeroIdentificador;
    }

    public Double getPeso() {
        return peso;
    }
}
