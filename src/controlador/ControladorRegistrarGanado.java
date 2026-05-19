package controlador;

//@autor: Brayan C

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.Vaca;
import modelo.VacaDAO;
import vista.PanelRegistrarGanado;


public class ControladorRegistrarGanado implements ActionListener {
    private Usuario usuario;
    private PanelRegistrarGanado panelRegistrarGanado;
    private VacaDAO vacaDAO;

    public ControladorRegistrarGanado(PanelRegistrarGanado panelRegistrarGanado, VacaDAO vacaDAO, Usuario usuario) {
        this.panelRegistrarGanado = panelRegistrarGanado;
        this.vacaDAO = vacaDAO;
        this.usuario = usuario;
        llenarJlables();
        activarEventos();
    }
    
    public void llenarJlables() {
        llenarJlabelRazas();
        llenarJlabeslsFechas();
        
    }
    
    public void activarEventos() {
        panelRegistrarGanado.getJcbAño().addActionListener(this);
        panelRegistrarGanado.getJcbMes().addActionListener(this);
        panelRegistrarGanado.getBtnRegistrar().addActionListener(this);
    }
    
    public void llenarJlabelRazas() {
        panelRegistrarGanado.getJcbRazaMadre().removeAllItems();
        panelRegistrarGanado.getJcbRazaPadre().removeAllItems();
        panelRegistrarGanado.getJcbRazaMadre().addItem("Raza madre");
        panelRegistrarGanado.getJcbRazaPadre().addItem("Raza padre");
        for (String raza : vacaDAO.retornarListadoDeRazas()) {
            panelRegistrarGanado.getJcbRazaMadre().addItem(raza);
            panelRegistrarGanado.getJcbRazaPadre().addItem(raza);
        }
    }
    
    public void llenarJlabeslsFechas() {
        for (Month mes : Month.values()) {
            String mesEspañol = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            panelRegistrarGanado.getJcbMes().addItem(mesEspañol);
        }
        
        int añoActual = LocalDate.now().getYear();
        
        for (int i = añoActual ; i >= 1950 ; i--) {
            panelRegistrarGanado.getJcbAño().addItem(String.valueOf(i));
        }
    }
    
    public void llenarDiasDelMes() {
        panelRegistrarGanado.getJcbDia().removeAllItems();
        panelRegistrarGanado.getJcbDia().addItem("Día");
        
        int año;
        
        try {
            año = Integer.parseInt(String.valueOf(panelRegistrarGanado.getJcbAño().getSelectedItem()));
        } catch (NumberFormatException e) {
            return;
        }
        int mes = panelRegistrarGanado.getJcbMes().getSelectedIndex();
        
        if (mes == 0) {
            panelRegistrarGanado.getJcbDia().removeAllItems();
            panelRegistrarGanado.getJcbDia().addItem("Día");
            return;
        }
        
        YearMonth yearMonth = YearMonth.of(año, mes);
        
        int cantidadDias = yearMonth.lengthOfMonth();
        
        for (int i = 1; i <= cantidadDias; i++) {
            panelRegistrarGanado.getJcbDia().addItem(String.valueOf(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panelRegistrarGanado.getJcbAño()) {
            llenarDiasDelMes();
        } else if (e.getSource() == panelRegistrarGanado.getJcbMes()) {
            llenarDiasDelMes();
        } else if (e.getSource() == panelRegistrarGanado.getBtnRegistrar()) {
            registrar();
        }
    }
    
    public void registrar() {
        if (vacaDAO.registrar(leerDatos())) {
            JOptionPane.showMessageDialog(panelRegistrarGanado, "Registro completado.");
        } else {
            JOptionPane.showMessageDialog(panelRegistrarGanado, "Error al registrar, completa los datos.");
        }
        
    }
    
    public Vaca leerDatos() {
        int year = Integer.parseInt(panelRegistrarGanado.getJcbAño().getSelectedItem().toString());
        int mes = panelRegistrarGanado.getJcbMes().getSelectedIndex();
        int dia = Integer.parseInt(panelRegistrarGanado.getJcbDia().getSelectedItem().toString());
        
        LocalDate fecha = LocalDate.of(year, mes, dia);
        
        String descripcion = panelRegistrarGanado.getDescripcion();
        String numeroIdentificador = panelRegistrarGanado.getNumeroIdentificacion();
        Double peso = panelRegistrarGanado.getPeso();
        
        if (descripcion == null) {
            descripcion = "No hay descripcion.";
        }
        
        if (numeroIdentificador == null) {
            numeroIdentificador = "No se registro numero identificador.";
        }
        
        return new Vaca(panelRegistrarGanado.getNombre() ,fecha , panelRegistrarGanado.getJcbRazaPadre().getSelectedItem().toString(), panelRegistrarGanado.getJcbRazaMadre().getSelectedItem().toString(), panelRegistrarGanado.getJcbEstado().getSelectedItem().toString(), descripcion, numeroIdentificador, peso, usuario.getNombreDeUsuario());
    }
}
