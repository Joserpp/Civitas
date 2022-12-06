
package civitas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


//import javax.lang.model.util.ElementScanner6;


public class Testp4 {

    public static void main(String[] args) {

        Jugador jugador= new Jugador("carlos");
        CasillaCalle calle = new CasillaCalle("Calle Reyes", 0, 0, 0);
        jugador.comprar(calle);

        System.out.println(jugador.toString());

        jugador = jugador.convertir();

        System.out.println(jugador.toString());
    }
}