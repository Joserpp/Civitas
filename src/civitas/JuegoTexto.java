package civitas;

import controladorCivitas.Controlador;
import vistaTextualCivitas.VistaTextual;
import java.util.ArrayList;

public class JuegoTexto {

    public static void main(String[] args) {
        
        ArrayList<String> jugadores = new ArrayList<>();
        jugadores.add("A");
        jugadores.add("B");
        jugadores.add("C");
        jugadores.add("D");
        CivitasJuego juego = new CivitasJuego(jugadores, false);
        
        VistaTextual vista = new VistaTextual(juego);
        
        Controlador controlador = new Controlador(juego, vista);
        


        controlador.juega();
        
    }
    
}