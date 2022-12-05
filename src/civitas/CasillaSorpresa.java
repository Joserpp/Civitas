package civitas;
import java.util.ArrayList;

public class CasillaSorpresa extends Casilla{
    
    private MazoSorpresas mazo;
    
    private Sorpresa sorpresa;

    public CasillaSorpresa(String nombre,MazoSorpresas mazo){
        super(nombre);
        this.mazo=mazo;
    }

    public CasillaSorpresa(String nombre){
        super(nombre);
    }

    
    public String toString(){
        return nombre;
    }

    
    public void recibeJugador(int actual, ArrayList<Jugador> todos)
    {
        // 1
        Sorpresa sorpresa = mazo.siguiente();
        // 2
        super.informe(actual,todos);
        // 3
        sorpresa.aplicarAJugador(actual,todos);
        
    }
}
