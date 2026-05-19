package controlador;

//@autor: Brayan C

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import vista.PanelGanado;
import vista.ScreenManager;


public class ControladorGanado implements ActionListener {
    private PanelGanado panelGanado;
    private Usuario usuario;

    public ControladorGanado(PanelGanado panelGanado, Usuario usuario) {
        this.panelGanado = panelGanado;
        this.usuario = usuario;
        activarBotones();
        ScreenManager.cambiarAPanelRegistrarGanado(panelGanado, usuario);
        panelGanado.getBtnRegistrarGanado().setBackground(new Color(93, 122, 163));
    }
    
    public void activarBotones() {
        panelGanado.getBtnRegistrarGanado().addActionListener(this);
        panelGanado.getBtnListaGanado().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Color botonActivo = new Color(93, 122, 163);
        if (e.getSource() == panelGanado.getBtnRegistrarGanado()) {
            reiniciarColoresDeBotones();
            panelGanado.getBtnRegistrarGanado().setBackground(botonActivo);
            ScreenManager.cambiarAPanelRegistrarGanado(panelGanado, usuario);
        } else if (e.getSource() == panelGanado.getBtnListaGanado()) {
            reiniciarColoresDeBotones();
            panelGanado.getBtnListaGanado().setBackground(botonActivo);
            ScreenManager.cambiarAPanelListaGanado(panelGanado, usuario);
        }
    }
    
    public void reiniciarColoresDeBotones() {
        Color azulGris = new Color(55, 72, 95);
        panelGanado.getBtnRegistrarGanado().setBackground(azulGris);
        panelGanado.getBtnListaGanado().setBackground(azulGris);
    }
    
}
