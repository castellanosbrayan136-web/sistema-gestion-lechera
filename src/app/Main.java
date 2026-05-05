package app;

//@autor Brayan C

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import javax.swing.JFrame;
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
    }

}
