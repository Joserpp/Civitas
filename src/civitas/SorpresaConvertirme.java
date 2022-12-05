package civitas;
import java.util.ArrayList;


public class SorpresaConvertirme extends Sorpresa{
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        todos.set(actual, todos.get(actual).convertir());
    }

    public SorpresaConvertirme(String texto, int valor){

        super(texto, valor);
    }
}