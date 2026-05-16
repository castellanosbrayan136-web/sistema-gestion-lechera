package controlador;

//@autor: Brayan C

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import vista.ScreenManager;
import vista.VistaPrincipal;


public class ControladorPrincipal implements ActionListener {
    VistaPrincipal vistaPrincipal;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        activarEventos();
    }
    
    public void activarEventos() {
        eventoBotonX();
        activarBotones();
    }
    
    public void eventoBotonX() {
        vistaPrincipal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ScreenManager.cerrarVistaPrincipal(vistaPrincipal);
                ScreenManager.abrirVistaAutenticacion();
            }
        });
    }
    
    public void activarBotones() {
        vistaPrincipal.getBtnInicio().addActionListener(this);
        vistaPrincipal.getBtnGanado().addActionListener(this);
        vistaPrincipal.getBtnProduccion().addActionListener(this);
        vistaPrincipal.getBtnClientes().addActionListener(this);
        vistaPrincipal.getBtnFinanzas().addActionListener(this);
        vistaPrincipal.getBtnReportes().addActionListener(this);
        vistaPrincipal.getBtnSalir().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String mensajeVersion = "Disponible en futuras versiones.";
        if (e.getSource() == vistaPrincipal.getBtnInicio()) {
            ScreenManager.cambiarAPanelInicio(vistaPrincipal);
        } else if (e.getSource() == vistaPrincipal.getBtnGanado()) {
            ScreenManager.cambiarAPanelGanado(vistaPrincipal);
        } else if (e.getSource() == vistaPrincipal.getBtnProduccion()) {
            JOptionPane.showMessageDialog(vistaPrincipal, mensajeVersion);
        } else if (e.getSource() == vistaPrincipal.getBtnFinanzas()) {
            JOptionPane.showMessageDialog(vistaPrincipal, mensajeVersion);
        } else if (e.getSource() == vistaPrincipal.getBtnClientes()) {
            JOptionPane.showMessageDialog(vistaPrincipal, mensajeVersion);
        } else if (e.getSource() == vistaPrincipal.getBtnReportes()) {
            JOptionPane.showMessageDialog(vistaPrincipal, mensajeVersion);
        } else if (e.getSource() == vistaPrincipal.getBtnSalir()) {
            System.exit(0);
        }
    }
    
}
