package modelo;

//@autor: Brayan C

public class Ubicacion {
    private final String departamento;
    private final String municipio;
    private final String vereda;

    public Ubicacion(String departamento, String municipio, String vereda) {
        this.departamento = departamento;
        this.municipio = municipio;
        this.vereda = vereda;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getVereda() {
        return vereda;
    }
}
