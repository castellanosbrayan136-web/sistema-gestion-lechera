package vista;

//@autor: Brayan C

import controlador.ControladorAutenticacion;
import controlador.ControladorCrearCuenta;
import controlador.ControladorIniciarSesion;
import controlador.ControladorPrincipal;
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
    
    public static void cerrarVistaAutenticacion(VistaAutenticacion vistaAutenticacion) {
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
        
        new ControladorIniciarSesion(usuarioDAO, panelIniciarSesion, vistaAutenticacion);
        
        panelIniciarSesion.setSize(881,1128);
        vistaAutenticacion.getPanelIntercambiable().removeAll();
        vistaAutenticacion.getPanelIntercambiable().add(panelIniciarSesion, BorderLayout.CENTER);
        vistaAutenticacion.revalidate();
        vistaAutenticacion.repaint();
    }
    
    public static void abrirVistaPrincipal(Usuario usuario, VistaAutenticacion vistaAutenticacion) {
        cerrarVistaAutenticacion(vistaAutenticacion);
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        
        new ControladorPrincipal(vistaPrincipal);
        
        vistaPrincipal.setVisible(true);
    }
    
    public static void cerrarVistaPrincipal(VistaPrincipal vistaPrincipal) {
        vistaPrincipal.dispose();
    }
}
