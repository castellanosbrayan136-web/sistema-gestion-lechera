package controlador;

//@autor: Brayan C

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import modelo.Vaca;
import modelo.VacaDAO;
import vista.PanelListaGanado;


public class ControladorListaGanado implements ActionListener {
    private PanelListaGanado panelListaGanado;
    private VacaDAO vacaDAO;
    private Usuario usuario;

    public ControladorListaGanado(PanelListaGanado panelListaGanado, VacaDAO vacaDAO, Usuario usuario) {
        this.panelListaGanado = panelListaGanado;
        this.vacaDAO = vacaDAO;
        this.usuario = usuario;
        llenarTabla();
    }
    
    public void llenarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) panelListaGanado.getTablaGanado().getModel();
        
        modeloTabla.setRowCount(0);
        
        Object[] fila = new Object[4];
        
         
        
        for (Vaca vaca : vacaDAO.retornarListaVacasPorUsuario(usuario.getNombreDeUsuario())) {
            int edad = LocalDate.now().getYear() - vaca.getFechaNacimiento().getYear();
            String raza = vaca.getRazaMadre() + " " + vaca.getRazaPadre();
            fila[0] = vaca.getNombre();
            fila[1] = edad;
            fila[2] = raza;
            fila[3] = vaca.getEstado();
            modeloTabla.addRow(fila);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    
}
