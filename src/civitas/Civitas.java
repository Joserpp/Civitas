package civitas;
import java.util.ArrayList;

public class Civitas {

    public static void main(String[] args) {
        
        ArrayList<String> jugadores = new ArrayList<>();
        jugadores.add("A");
        jugadores.add("B");
        jugadores.add("C");
        jugadores.add("D");
          
        CivitasJuego juego = new CivitasJuego(jugadores, false);
        
    }
    
}