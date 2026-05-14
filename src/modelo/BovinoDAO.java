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


public class BovinoDAO {
    private final Gson gson;
    private final String ruta;
    private final List<Bovino> ganado;
    private static final List<Bovino> hembras = new ArrayList<>();
    private static final List<Bovino> machos = new ArrayList<>();

    public BovinoDAO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.ruta = "ganado.json";
        this.ganado = cargarDatos();
        cargarListasMachosYHembras();
    }
    
    private void guardarDatos() {
        try (Writer writer = new FileWriter(ruta)) {
            gson.toJson(ganado,writer);
        } catch (IOException ex) {
            System.err.println("Error al guardar datos: " + ex.getMessage());
        }
    }
    
    private List<Bovino> cargarDatos() {
        File archivo = new File(ruta);
        
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        
        try (Reader reader = new FileReader(archivo)) {
            Type tipoLista = new TypeToken<List<Bovino>>(){}.getType();
            List<Bovino> lista = gson.fromJson(reader, tipoLista);
            
            return (lista != null) ? lista : new ArrayList<>();
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }
    
    private String generarCodigoInterno() {
        int codigoCorrespondiente = ganado.size() + 1;
        
        if (codigoCorrespondiente < 10) {
            return "00" + String.valueOf(codigoCorrespondiente);
        } else if (codigoCorrespondiente < 100) {
            return "0" + String.valueOf(codigoCorrespondiente);
        } else {
            return String.valueOf(codigoCorrespondiente);
        }
    }
    
    public boolean registrar(Bovino bovino) {
        if (bovino == null) {
            return false;
        }
        
        bovino.setCodigoInterno(generarCodigoInterno());
        
        if(verificarSexo(bovino)) {
            ganado.add(bovino);
            machos.add(bovino);
            guardarDatos();
            return true;
        } else {
            ganado.add(bovino);
            hembras.add(bovino);
            guardarDatos();
            return true;
        }
    }
    
    private void cargarListasMachosYHembras() {
        machos.clear();
        hembras.clear();
        
        for (Bovino bovino: ganado) {
            if (verificarSexo(bovino)) {
                machos.add(bovino);
            } else {
                hembras.add(bovino);
            }
        }
    }
    
    private boolean verificarSexo(Bovino bovino) {
        return bovino.getSexo().equals("macho");
    }
    
    public List<Bovino> retornarGanado() {
        return ganado;
    }
    
    public List<Bovino> retornarMachos() {
        return machos;
    }
    
    public List<Bovino> retornarHembras() {
        return hembras;
    }
}
