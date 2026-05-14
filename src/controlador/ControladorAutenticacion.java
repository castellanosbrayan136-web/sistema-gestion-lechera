package controlador;

//@autor: Brayan C

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.ScreenManager;
import vista.VistaAutenticacion;


public class ControladorAutenticacion implements ActionListener{
    private VistaAutenticacion vistaAutenticacion;

    public ControladorAutenticacion(VistaAutenticacion vistaAutenticacion) {
        this.vistaAutenticacion = vistaAutenticacion;
        ScreenManager.cambiarAPanelIniciarSesion(vistaAutenticacion);
        activarBotones();
    }
    
    public void activarBotones() {
        vistaAutenticacion.getBtnIniciarSesion().addActionListener(this);
        vistaAutenticacion.getBtnCrearCuenta().addActionListener(this);
        vistaAutenticacion.getBtnSalir().addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaAutenticacion.getBtnIniciarSesion()) {
            ScreenManager.cambiarAPanelIniciarSesion(vistaAutenticacion);
        } else if (e.getSource() == vistaAutenticacion.getBtnCrearCuenta()) {
            ScreenManager.cambiarAPanelCrearCuenta(vistaAutenticacion);
        } else if (e.getSource() == vistaAutenticacion.getBtnSalir()) {
            System.exit(0);
        }
    }
}
