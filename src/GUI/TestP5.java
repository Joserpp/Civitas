package GUI;

import java.util.ArrayList;

import civitas.CivitasJuego;
import controladorCivitas.Controlador;

/**
 *
 * @author joserpp
 */
public class TestP5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CivitasView cv = new CivitasView();
        
        CapturaNombres captura = new CapturaNombres(cv, true);
        
        ArrayList<String> nombres = new ArrayList<>();
        
        nombres = captura.getNombres();
        
        Dado.createInstance(cv);
        
        CivitasJuego cj = new CivitasJuego(nombres, false);
        
        Controlador controlador = new Controlador(cj, cv);
        
        cv.setCivitasJuego(cj);
        
        controlador.juega();
    }
    
}
