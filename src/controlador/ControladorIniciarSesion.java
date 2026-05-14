package controlador;

//@autor: Brayan C

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.PanelIniciarSesion;
import vista.ScreenManager;


public class ControladorIniciarSesion implements ActionListener {
    private UsuarioDAO usuarioDAO;
    private PanelIniciarSesion panelIniciarSesion;

    public ControladorIniciarSesion(UsuarioDAO usuarioDAO, PanelIniciarSesion panelIniciarSesion) {
        this.usuarioDAO = usuarioDAO;
        this.panelIniciarSesion = panelIniciarSesion;
        activarEventos();
    }
    
    public void activarEventos() {
        panelIniciarSesion.getBtnEntrar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panelIniciarSesion.getBtnEntrar()) {
            iniciarSesion();
        }
    }
    
    public void iniciarSesion() {
        String nombreUsuario = leerNombreUsuario();
        String contraseña = leerContraseña();
        
        if (nombreUsuario == null || contraseña == null) {
            return;
        }
        Usuario usuario = usuarioDAO.verificarUsuario(nombreUsuario, contraseña);
        
        if (usuario != null) {
            ScreenManager.abrirVistaPrincipal(usuario);
        }
    }
    
    
    
    public String leerNombreUsuario() {
        String usuario = panelIniciarSesion.getUsuario();
        if (usuario == null) {
            panelIniciarSesion.setMensajeUsuario("Ingresa un usuario");
        }
        return usuario;
    }
    
    public String leerContraseña() {
        String contraseña = panelIniciarSesion.getContraseña();
        if (contraseña == null) {
            panelIniciarSesion.setMensajeContraseña("Ingresa una contraseña");
        }
        return contraseña;
    }
}
