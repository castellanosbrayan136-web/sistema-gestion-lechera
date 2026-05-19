package controlador;

//@autor: Brayan C

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import modelo.Usuario;
import modelo.Vaca;
import modelo.VacaDAO;
import vista.PanelListaGanado;


public class ControladorListaGanado implements KeyListener {
    private PanelListaGanado panelListaGanado;
    private VacaDAO vacaDAO;
    private Usuario usuario;

    public ControladorListaGanado(PanelListaGanado panelListaGanado, VacaDAO vacaDAO, Usuario usuario) {
        this.panelListaGanado = panelListaGanado;
        this.vacaDAO = vacaDAO;
        this.usuario = usuario;
        configurarTabla();
        llenarTabla();
        activarEventos();
    }
    
    public void activarEventos() {
        panelListaGanado.getTxtFiltroPorNombre().addKeyListener(this);
        eventoTabla();
    }
    
    public void configurarTabla() {

        JTable tabla = panelListaGanado.getTablaGanado();

        // Altura de filas
        tabla.setRowHeight(32);

        // Seleccionar fila completa
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);

        // NO permitir editar
        tabla.setDefaultEditor(Object.class, null);

        // Diseño limpio
        tabla.setShowVerticalLines(true);
        tabla.setShowHorizontalLines(true);

        // Espaciado elegante
        tabla.setIntercellSpacing(new Dimension(0, 1));

        // Que ocupe todo el ancho automaticamente
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Distribucion proporcional columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);   // Codigo
        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);  // Nombre
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);   // Edad
        tabla.getColumnModel().getColumn(3).setPreferredWidth(260);  // Raza
        tabla.getColumnModel().getColumn(4).setPreferredWidth(140);  // Estado

        // Fuente
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Header
        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setReorderingAllowed(false);

        // Renderer centrado
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(SwingConstants.CENTER);

        // Centrar columnas
        tabla.getColumnModel().getColumn(0).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(3).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(4).setCellRenderer(centro);

        // Centrar encabezados
        DefaultTableCellRenderer headerRenderer =
                (DefaultTableCellRenderer) header.getDefaultRenderer();

        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Cursor tipo mano
        tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Quitar borde feo de focus
        tabla.setFocusable(false);
    }
    
    public void llenarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) panelListaGanado.getTablaGanado().getModel();
        
        modeloTabla.setRowCount(0);
        
        Object[] fila = new Object[5];
        
        for (Vaca vaca : vacaDAO.retornarListaVacasPorUsuario(usuario.getNombreDeUsuario())) {
            int edad = LocalDate.now().getYear() - vaca.getFechaNacimiento().getYear();
            String raza = vaca.getRazaMadre() + " " + vaca.getRazaPadre();
            fila[0] = vaca.getCodigoInterno();
            fila[1] = vaca.getNombre();
            fila[2] = edad;
            fila[3] = raza;
            fila[4] = vaca.getEstado();
            modeloTabla.addRow(fila);
        }
    }
    
    public void filtrarTablaPorNombreVaca() {
        String nombreVaca = panelListaGanado.getTxtFiltroPorNombre().getText();
        
        if (nombreVaca == null || nombreVaca.trim().isEmpty()) {
            llenarTabla();
            return;
        }
        
        DefaultTableModel modeloTabla = (DefaultTableModel) panelListaGanado.getTablaGanado().getModel();
        
        modeloTabla.setRowCount(0);
        
        Object[] fila = new Object[5];
        
        for (Vaca vaca : vacaDAO.filtrarVacasPorCoincidencia(usuario.getNombreDeUsuario(), nombreVaca)) {
            int edad = LocalDate.now().getYear() - vaca.getFechaNacimiento().getYear();
            String raza = vaca.getRazaMadre() + " " + vaca.getRazaPadre();
            fila[0] = vaca.getCodigoInterno();
            fila[1] = vaca.getNombre();
            fila[2] = edad;
            fila[3] = raza;
            fila[4] = vaca.getEstado();
            modeloTabla.addRow(fila);
        }
    }
    
    public void eventoTabla() {
        panelListaGanado.getTablaGanado().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    mostrarInformacion();
                }
            }
        });
    }
    
    public void mostrarInformacion() {
        JOptionPane.showMessageDialog(null, llenarInformacion());
    }
    
    public String llenarInformacion() {
        DefaultTableModel modeloTabla = (DefaultTableModel) panelListaGanado.getTablaGanado().getModel();
        
        int row = panelListaGanado.getTablaGanado().getSelectedRow();
        
        if (row == -1) {
            return "No hay fila seleccionada";
        }
        
        String codigoVaca = modeloTabla.getValueAt(row, 0).toString();
        
        Vaca vaca = vacaDAO.buscarVacaPorCodigo(codigoVaca);
        
        String informacion = "=======INFORMACIÓN==========";
        
        informacion += "\nCodigo: " + vaca.getCodigoInterno() + 
                "\nNombre: " + vaca.getNombre() +
                "\nFecha de nacimiento: " +
                vaca.getFechaNacimiento().getDayOfMonth() + 
                "/" + vaca.getFechaNacimiento().getMonthValue() +
                "/" + vaca.getFechaNacimiento().getYear() +
                "\nRaza: " + vaca.getRazaMadre() + " x " + vaca.getRazaPadre() + 
                "\nEstado: " + vaca.getEstado() +
                "\nNumero de indentificacion: " + vaca.getNumeroIdentificador() +
                "\nPeso: " + vaca.getPeso() +
                "\nDescripcion: " + vaca.getDescripcion();
        return informacion;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            configurarTabla();
            filtrarTablaPorNombreVaca();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
