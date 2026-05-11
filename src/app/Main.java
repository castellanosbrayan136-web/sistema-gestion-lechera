package app;

//@autor Brayan C

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicComboBoxUI;
import vista.PanelCrearCuenta;
import vista.VistaAutenticacion;
import vista.VistaPrincipal;


public class Main {
    public static void main(String[] args) {
        FlatGradiantoDeepOceanIJTheme.setup();
        
        
        VistaAutenticacion vista = new VistaAutenticacion();
        vista.setSize(1440,900);
        vista.setLocationRelativeTo(null);
        vista.setTitle("Sistema de gestión lechera");
        vista.setVisible(true);
        
        PanelCrearCuenta panel = new PanelCrearCuenta();
        
        panel.setSize(881,1128);
        panel.setLocation(0,0);
                
        vista.getPanelIniciarSesion().removeAll();
        vista.getPanelIniciarSesion().add(panel,BorderLayout.CENTER);
        
        vista.revalidate();
        vista.repaint();
        
        
    }

}
