package modelo;

//@autor: Brayan C

import java.time.LocalDate;


public class Bovino {
    private String codigoInterno;
    private final String nombre;
    private final LocalDate fechaNacimiento;
    private final String razaPadre;
    private final String razaMadre;
    private final String sexo;
    private String estado;
    private String descripcion;
    private String numeroIdentificador;
    private Double peso;

    public Bovino(String nombre, LocalDate fechaNacimiento, String razaPadre, String razaMadre, String sexo, String estado,String descripcion, String numeroIdentificador, Double peso) {
        this.codigoInterno = "0";
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.razaPadre = razaPadre;
        this.razaMadre = razaMadre;
        this.sexo = sexo;
        this.estado = estado;
        this.descripcion = descripcion;
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

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNumeroIdentificador() {
        return numeroIdentificador;
    }

    public Double getPeso() {
        return peso;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }
    
    
}
