package civitas;

public class JugadorEspeculador extends Jugador {

    private int FactorEspeculador = 2;

    protected JugadorEspeculador(Jugador jugador){

        super(jugador);
    }
    
    protected void actualizaPropiedadesPorConversion(){

        for(Casilla casilla: propiedades){

            ((CasillaCalle)casilla).actualizaPropietarioPorConversion(this);
        }
    }
}
