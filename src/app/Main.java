package app;

//@autor Brayan C

import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import modelo.DepartamentoDAO;
import vista.ScreenManager;

public class Main {
    public static void main(String[] args) {
        FlatGradiantoDeepOceanIJTheme.setup();
        DepartamentoDAO e = new DepartamentoDAO();
        e.imprimir();
        ScreenManager.abrirVistaAutenticacion();
    }

}
