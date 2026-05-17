package controlador;

//@autor: Brayan C

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Departamento;
import modelo.DepartamentoDAO;
import modelo.Ubicacion;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.PanelCrearCuenta;


public class ControladorCrearCuenta implements ActionListener {
    private final PanelCrearCuenta panelCrearCuenta;
    private final UsuarioDAO usuarioDAO;
    private DepartamentoDAO departamentoDAO;

    public ControladorCrearCuenta(PanelCrearCuenta panelCrearCuenta, UsuarioDAO usuarioDAO, DepartamentoDAO departamentoDAO) {
        this.panelCrearCuenta = panelCrearCuenta;
        this.usuarioDAO = usuarioDAO;
        this.departamentoDAO = departamentoDAO;
        activarEventos();
        llenarJcomboBoxDepartamentos();
    }
    
    public void eventoBotones() {
        panelCrearCuenta.getBtnRegistrar().addActionListener(this);
        panelCrearCuenta.getJcbDepartamento().addActionListener(this);
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
        } else if (e.getSource() == panelCrearCuenta.getJcbDepartamento()) {
            llenarJcomboBoxMunicipios();
        }
    }
    
    public void eventoTxtNombres() {
        panelCrearCuenta.getTxtNombres().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                reiniciarCaposDeTexto();
                if (panelCrearCuenta.getNombres() != null) {
                    return;
                }
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
                if (panelCrearCuenta.getCorreo ()!= null) {
                    return;
                }
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
                if (panelCrearCuenta.getUsuario() != null) {
                    return;
                }
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
                if (panelCrearCuenta.getContraseña() != null) {
                    return;
                }
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
                if (panelCrearCuenta.getVereda() != null) {
                    return;
                }
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
                if (panelCrearCuenta.getNombreFinca() != null) {
                    return;
                }
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
    
    public void llenarJcomboBoxDepartamentos() {
        for (Departamento departamento : departamentoDAO.retornarDepartamentos() ) {
            panelCrearCuenta.getJcbDepartamento().addItem(departamento.getDepartamento());
        }
        llenarJcomboBoxMunicipios();
    }
    
    public void llenarJcomboBoxMunicipios() {
        String departamentoSeleccionado = panelCrearCuenta.getJcbDepartamento().getSelectedItem().toString();
        
        for (Departamento departamento : departamentoDAO.retornarDepartamentos()) {
            if (departamento.getDepartamento().equals(departamentoSeleccionado)) {
                panelCrearCuenta.getJcbMunicipio().removeAllItems();
                panelCrearCuenta.getJcbMunicipio().addItem("Municipio");
                for (String municipio : departamento.getMunicipios()) {
                    panelCrearCuenta.getJcbMunicipio().addItem(municipio);
                }
            }
        }
    }
    
    //Murio aprox 5:10pm - 5:20pm del dia jueves 14 de mayo del año 2026
}
