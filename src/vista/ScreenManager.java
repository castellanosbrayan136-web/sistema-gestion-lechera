package vista;

//@autor: Brayan C

import controlador.ControladorAutenticacion;
import controlador.ControladorCrearCuenta;
import controlador.ControladorIniciarSesion;
import java.awt.BorderLayout;
import modelo.Usuario;
import modelo.UsuarioDAO;


public class ScreenManager {
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public static void abrirVistaAutenticacion() {
        VistaAutenticacion vistaAutenticacion = new VistaAutenticacion();
        vistaAutenticacion.setSize(1440,900);
        vistaAutenticacion.setLocationRelativeTo(null);
        new ControladorAutenticacion(vistaAutenticacion);
        
        vistaAutenticacion.setVisible(true);
    }
    
    public void cerrarVistaAutenticacion(VistaAutenticacion vistaAutenticacion) {
        vistaAutenticacion.dispose();
    }
    public static void cambiarAPanelCrearCuenta(VistaAutenticacion vistaAutenticacion) {
        PanelCrearCuenta panelCrearCuenta = new PanelCrearCuenta();
        
        new ControladorCrearCuenta(panelCrearCuenta, usuarioDAO);
        
        panelCrearCuenta.setSize(881,1128);
        vistaAutenticacion.getPanelIntercambiable().removeAll();
        vistaAutenticacion.getPanelIntercambiable().add(panelCrearCuenta, BorderLayout.CENTER);
        vistaAutenticacion.revalidate();
        vistaAutenticacion.repaint();
    }
    
    public static void cambiarAPanelIniciarSesion(VistaAutenticacion vistaAutenticacion) {
        PanelIniciarSesion panelIniciarSesion = new PanelIniciarSesion();
        
        new ControladorIniciarSesion(usuarioDAO, panelIniciarSesion);
        
        panelIniciarSesion.setSize(881,1128);
        vistaAutenticacion.getPanelIntercambiable().removeAll();
        vistaAutenticacion.getPanelIntercambiable().add(panelIniciarSesion, BorderLayout.CENTER);
        vistaAutenticacion.revalidate();
        vistaAutenticacion.repaint();
    }
    
    public static void abrirVistaPrincipal(Usuario usuario) {
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        
        vistaPrincipal.setVisible(true);
    }
}
