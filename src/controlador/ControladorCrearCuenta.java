package controlador;

//@autor: Brayan C

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Ubicacion;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.PanelCrearCuenta;


public class ControladorCrearCuenta implements ActionListener {
    private final PanelCrearCuenta panelCrearCuenta;
    private final UsuarioDAO usuarioDAO;

    public ControladorCrearCuenta(PanelCrearCuenta panelCrearCuenta, UsuarioDAO usuarioDAO) {
        this.panelCrearCuenta = panelCrearCuenta;
        this.usuarioDAO = usuarioDAO;
        activarEventos();
    }
    
    public void eventoBotones() {
        panelCrearCuenta.getBtnRegistrar().addActionListener(this);
    }
    
    public void activarEventos() {
        eventoBotones();
        eventoTxtNombres();
        eventoTxtUsuario();
        eventoTxtCorreo();
        eventoTxtNombreFinca();
        eventoTxtVereda();
        eventoTxtContraseña();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == panelCrearCuenta.getBtnRegistrar()) {
            registrar();
        }
    }
    
    public void eventoTxtNombres() {
        panelCrearCuenta.getTxtNombres().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciarCaposDeTexto();
                panelCrearCuenta.setTxtNombres("");
                panelCrearCuenta.getTxtNombres().setForeground(Color.black);
            }
        });
    }
    
    public void eventoTxtCorreo() {
        panelCrearCuenta.getTxtCorreo().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciarCaposDeTexto();
                panelCrearCuenta.setTxtCorreo("");
                panelCrearCuenta.getTxtCorreo().setForeground(Color.black);
            }
        });
    }
    
    public void eventoTxtUsuario() {
        panelCrearCuenta.getTxtUsuario().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciarCaposDeTexto();
                panelCrearCuenta.setTxtUsuario("");
                panelCrearCuenta.getTxtUsuario().setForeground(Color.black);
            }
        });
    }
    
    public void eventoTxtContraseña() {
        panelCrearCuenta.getJpfContraseña().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciarCaposDeTexto();
                panelCrearCuenta.setJpfContraseña("");
                panelCrearCuenta.getJpfContraseña().setForeground(Color.black);
            }
        });
    }
    
    public void eventoTxtVereda() {
        panelCrearCuenta.getTxtVereda().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciarCaposDeTexto();
                panelCrearCuenta.setTxtVereda("");
                panelCrearCuenta.getTxtVereda().setForeground(Color.black);
            }
        });
    }
    
    public void eventoTxtNombreFinca() {
        panelCrearCuenta.getTxtNombreFinca().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciarCaposDeTexto();
                panelCrearCuenta.setTxtNombreFinca("");
                panelCrearCuenta.getTxtNombreFinca().setForeground(Color.black);
            }
        });
    }
    
    public void registrar() {
        Usuario nuevoUsuario = verificarYRetornarDatos();
        
        if (usuarioDAO.registrar(nuevoUsuario)) {
            JOptionPane.showMessageDialog(panelCrearCuenta, "Usuario creado correctamente.");
        }
    }
    
    public void reiniciarCaposDeTexto() {
        Color grisClaro = new Color(204,204,204);
        
        if (panelCrearCuenta.getCorreo() == null) {
            panelCrearCuenta.setTxtCorreo("Correo electrónico");
            panelCrearCuenta.getTxtCorreo().setForeground(grisClaro);
        }
        
        if (panelCrearCuenta.getNombreFinca() == null) {
            panelCrearCuenta.setTxtNombreFinca("Nombre de la finca");
            panelCrearCuenta.getTxtNombreFinca().setForeground(grisClaro);
        }
        
        if (panelCrearCuenta.getNombres() == null) {
            panelCrearCuenta.setTxtNombres("Nombres y apellidos");
            panelCrearCuenta.getTxtNombres().setForeground(grisClaro);
        }
        
        if (panelCrearCuenta.getUsuario() == null) {
            panelCrearCuenta.setTxtUsuario("Nombre de usuario");
            panelCrearCuenta.getTxtUsuario().setForeground(grisClaro);
        }
        
        if (panelCrearCuenta.getVereda() == null) {
            panelCrearCuenta.setTxtVereda("Vereda");
            panelCrearCuenta.getTxtVereda().setForeground(grisClaro);
        }
        
        if (panelCrearCuenta.getContraseña() == null) {
            panelCrearCuenta.setJpfContraseña("**************");
            panelCrearCuenta.getJpfContraseña().setForeground(grisClaro);
        }
    }
    
    public Usuario verificarYRetornarDatos() {
        String nombresYApellidos = panelCrearCuenta.getNombres();
        String nombreDeUsuario = panelCrearCuenta.getUsuario();
        String correoElectronico = panelCrearCuenta.getCorreo();
        String nombreDeLaFinca = panelCrearCuenta.getNombreFinca();
        String departamento = panelCrearCuenta.getJcbDepartamento().getSelectedItem().toString();
        String municipio = panelCrearCuenta.getJcbMunicipio().getSelectedItem().toString();
        String vereda = panelCrearCuenta.getVereda();
        Ubicacion ubicacion = new Ubicacion(departamento, municipio, vereda);
        String contraseña = panelCrearCuenta.getContraseña();
        
        if (nombresYApellidos == null) {
            panelCrearCuenta.setMensajeNombres("Ingresa nombres y apellidos");
            return null;
        } else if (nombreDeUsuario == null) {
            panelCrearCuenta.setMensajeUsuario("Ingresa un usuario");
            return null;
        } else if (correoElectronico == null) {
            panelCrearCuenta.setMensajeCorreo("Ingresa un correo electrónico");
            return null;
        } else if (nombreDeLaFinca == null) {
            panelCrearCuenta.setMensajeNombreDeLaFinca("Ingresa el nombre de la finca");
            return null;
        } else if (vereda == null) {
            panelCrearCuenta.setMensajeUbicacion("Completa la ubicación");
            return null;
        } else if (contraseña == null) {
            panelCrearCuenta.setMensajeContraseña("Ingresa una contraseña");
            return null;
        } else {
            return new Usuario(nombresYApellidos, nombreDeUsuario, correoElectronico, nombreDeLaFinca, ubicacion, contraseña);
        }
    }
    
}
