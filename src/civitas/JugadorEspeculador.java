package civitas;

public class JugadorEspeculador extends Jugador {

    private int FactorEspeculador = 2;

    protected JugadorEspeculador(Jugador jugador){

        super(jugador);

        actualizaPropiedadesPorConversion();
    }
    
    protected void actualizaPropiedadesPorConversion(){

        for(Casilla casilla: propiedades){

            ((CasillaCalle)casilla).actualizaPropietarioPorConversion(this);
        }
    }

    public String toString(){

        String text;

        text = super.toString();

        text += " y es un jugador expeculador";

        return text;
    }

    int getCasasMax(){

        return (CasasMax*FactorEspeculador);
    }

    int gethotelesMax(){

        return (HotelesMax*FactorEspeculador);
    }
}
