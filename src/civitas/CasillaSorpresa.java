package civitas;

import java.util.ArrayList;

public class CasillaSorpresa extends Casilla{
    
    private MazoSorpresas mazo;

    public CasillaSorpresa(String nombre,MazoSorpresas mazo){
        
        super(nombre);
        this.mazo = mazo;
    }

    public CasillaSorpresa(String nombre){
        
        super(nombre);
    }

    public void recibeJugador(int actual, ArrayList<Jugador> todos){

        Sorpresa sorpresa = mazo.siguiente();
        informe(actual,todos);
        sorpresa.aplicarAJugador(actual,todos);
    }

    public String toString(){

        return nombre;
    }
}
