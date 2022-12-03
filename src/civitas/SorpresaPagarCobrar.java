package civitas;
import java.util.ArrayList;


public class SorpresaPagarCobrar extends Sorpresa{

    public SorpresaPagarCobrar(String texto,int valor){
        super(texto, valor);
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador>todos){
        super.informe(actual, todos);
        todos.get(actual).modificarSaldo(valor);
    }
    
    
}
