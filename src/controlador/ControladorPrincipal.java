package controlador;

//@autor: Brayan C

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import vista.ScreenManager;
import vista.VistaPrincipal;


public class ControladorPrincipal implements ActionListener {
    VistaPrincipal vistaPrincipal;

    public ControladorPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        eventoBotonX();
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
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
