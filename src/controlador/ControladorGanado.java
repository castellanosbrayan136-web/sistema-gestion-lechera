package controlador;

//@autor: Brayan C

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.PanelGanado;
import vista.ScreenManager;


public class ControladorGanado implements ActionListener {
    private PanelGanado panelGanado;

    public ControladorGanado(PanelGanado panelGanado) {
        this.panelGanado = panelGanado;
        activarBotones();
        ScreenManager.cambiarAPanelRegistrarGanado(panelGanado);
        panelGanado.getBtnRegistrarGanado().setBackground(new Color(93, 122, 163));
    }
    
    public void activarBotones() {
        panelGanado.getBtnRegistrarGanado().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Color botonActivo = new Color(93, 122, 163);
        if (e.getSource() == panelGanado.getBtnRegistrarGanado()) {
            reiniciarColoresDeBotones();
            panelGanado.getBtnRegistrarGanado().setBackground(botonActivo);
            ScreenManager.cambiarAPanelRegistrarGanado(panelGanado);
        } 
    }
    
    public void reiniciarColoresDeBotones() {
        Color azulGris = new Color(55, 72, 95);
        panelGanado.getBtnRegistrarGanado().setBackground(azulGris);
    }
    
}
