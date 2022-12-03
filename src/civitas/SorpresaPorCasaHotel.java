package civitas;
import java.util.ArrayList;

public class SorpresaPorCasaHotel extends Sorpresa{
 
    SorpresaPorCasaHotel(String texto,int valor){
        super(texto,valor);
    }

    @Override
    public void aplicarAJugador(int actual,ArrayList<Jugador>todos){
         super.informe(actual, todos);
         int factor=todos.get(actual).cantidadCasasHoteles();
         todos.get(actual).modificarSaldo(valor*factor);   
    }
}
