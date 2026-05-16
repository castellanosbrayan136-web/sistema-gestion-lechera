package vista;

//@autor: Brayan C

import controlador.ControladorAutenticacion;
import controlador.ControladorCrearCuenta;
import controlador.ControladorIniciarSesion;
import controlador.ControladorPrincipal;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        
        cambiarPaneles(vistaAutenticacion.getPanelIntercambiable(), panelCrearCuenta, 881, 1128, vistaAutenticacion);
    }
    
    public static void cambiarAPanelIniciarSesion(VistaAutenticacion vistaAutenticacion) {
        PanelIniciarSesion panelIniciarSesion = new PanelIniciarSesion();
        
        new ControladorIniciarSesion(usuarioDAO, panelIniciarSesion, vistaAutenticacion);
        
        cambiarPaneles(vistaAutenticacion.getPanelIntercambiable(), panelIniciarSesion, 881, 1128, vistaAutenticacion);
    }
    
    public static void abrirVistaPrincipal(Usuario usuario, VistaAutenticacion vistaAutenticacion) {
        cerrarVistaAutenticacion(vistaAutenticacion);
        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        
        vistaPrincipal.setSize(1440,900);
        vistaPrincipal.setTitle("Sistema de gestion ganadera");
        vistaPrincipal.setLocationRelativeTo(null);
        
        new ControladorPrincipal(vistaPrincipal);
        
        vistaPrincipal.setVisible(true);
    }
    
    public static void cerrarVistaPrincipal(VistaPrincipal vistaPrincipal) {
        vistaPrincipal.dispose();
    }
    
    public static void cambiarAPanelInicio(VistaPrincipal vistaPrincipal) {
        PanelInicio panelInicio = new PanelInicio();
        
        cambiarPaneles(vistaPrincipal.getPanelVacio(), panelInicio, 1128, 778, vistaPrincipal);
    }
    
    public static void cambiarAPanelGanado(VistaPrincipal vistaPrincipal) {
        PanelGanado panelGanado = new PanelGanado();
        
        cambiarPaneles(vistaPrincipal.getPanelVacio(), panelGanado, 1128, 778, vistaPrincipal);
    }
    
    public static void cambiarPaneles(JPanel panelVacio, JPanel panelAIntercambiar, int ancho, int largo, JFrame vista) {
        panelAIntercambiar.setSize(ancho,largo);
        panelVacio.removeAll();
        panelVacio.add(panelAIntercambiar, BorderLayout.CENTER);
        vista.revalidate();
        vista.repaint();
    }
}
