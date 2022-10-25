package civitas;

import java.util.ArrayList;

public class Sorpresa {
    private String texto;
    private int valor;
    private TipoSorpresa tipo;

    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        if (tipo == TipoSorpresa.PAGARCOBRAR)
            aplicarAJugador_pagarCobrar(actual, todos);
        else 
            aplicarAJugador_porCasaHotel(actual, todos);
    }

    Sorpresa (TipoSorpresa tipo,String texto, int valor){
        this.texto=texto;
        this.valor=valor;
        this.tipo=tipo;

    }

    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){

        informe(actual, todos);
        todos.get(actual).modificarSaldo(this.valor);
    }

    void informe (int actual, ArrayList<Jugador> todos){

        Diario.getInstance().ocurreEvento("A " + todos.get(actual).getNombre() + " se le esta aplicando una sorpresa");

    }

    public String toString(){
        
        return texto;
    }

    private void aplicarAJugador_porCasaHotel(int actual,ArrayList<Jugador>todos){

        informe(actual, todos);
        int factor = todos.get(actual).cantidadCasasHoteles();
        todos.get(actual).modificarSaldo(this.valor*factor);
    }
}
