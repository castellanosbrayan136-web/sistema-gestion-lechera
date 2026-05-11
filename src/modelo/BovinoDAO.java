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
    private Gson gson;
    private final String ruta;
    private List<Bovino> ganado;

    public BovinoDAO(List<Bovino> ganado) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.ruta = "ganado.json";
        this.ganado = cargarDatos();
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
            
            return (lista != null) ? lista : new ArrayList<Bovino>();
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }
    
}
