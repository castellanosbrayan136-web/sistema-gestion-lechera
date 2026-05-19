package modelo;

//@autor: Brayan C

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class VacaDAO {
    private final Gson gson;
    private final String ruta;
    private final List<Vaca> listaVacas;
    private List<String> listaRazas;
    private String rutaRazas;

    public VacaDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.ruta = "ganado.json";
        this.listaVacas = cargarDatos();
        this.rutaRazas = "razas.json";
        this.listaRazas = cargarListadoDeRazas();
    }
    
    private void guardarDatos() {
        try (Writer writer = new FileWriter(ruta)) {
            gson.toJson(listaVacas,writer);
        } catch (IOException ex) {
            System.err.println("Error al guardar datos: " + ex.getMessage());
        }
    }
    
    private List<Vaca> cargarDatos() {
        File archivo = new File(ruta);
        
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        
        try (Reader reader = new FileReader(archivo)) {
            Type tipoLista = new TypeToken<List<Vaca>>(){}.getType();
            List<Vaca> lista = gson.fromJson(reader, tipoLista);
            
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }
    
    public List<String> cargarListadoDeRazas() {
        File archivo = new File(rutaRazas);
        
        try (Reader lector = new FileReader(archivo)) {
            Type tipo = new TypeToken<List<String>>(){}.getType();
            List<String> lista = gson.fromJson(lector, tipo);
            return lista;
        } catch (IOException ex) {
            System.err.println("Error al cargar listado de razas: " + ex.getMessage());
            return new ArrayList<>();
        }
    }
    
    private String generarCodigoInterno() {
        int codigoCorrespondiente = listaVacas.size() + 1;
        
        if (codigoCorrespondiente < 10) {
            return "00" + String.valueOf(codigoCorrespondiente);
        } else if (codigoCorrespondiente < 100) {
            return "0" + String.valueOf(codigoCorrespondiente);
        } else {
            return String.valueOf(codigoCorrespondiente);
        }
    }
    
    public boolean registrar(Vaca bovino) {
        if (bovino == null) {
            return false;
        }
        
        bovino.setCodigoInterno(generarCodigoInterno());
        
        listaVacas.add(bovino);
        guardarDatos();
        return true;
    }
    
    public List<Vaca> retornarListaVacasPorUsuario(String usuario) {
        List<Vaca> lista = new ArrayList<>();
        for (Vaca vaca : listaVacas) {
            if (vaca.getDueño().equals(usuario)) {
                lista.add(vaca);
            }
        }
        return lista;
    }
    
    public List<String> retornarListadoDeRazas() {
        return listaRazas;
    }
}
