package civitas;

import java.util.ArrayList;

public abstract class Sorpresa {

    String texto;
    int valor;

    Sorpresa (String texto, int valor){

        this.texto = texto;
        this.valor = valor;
    }

    void informe (int actual, ArrayList<Jugador> todos){

        Diario.getInstance().ocurreEvento("Al jugador " + todos.get(actual).getNombre() 
                            + " se le aplica una sorpresa");

    }

    public String toString(){
        
        return texto;
    }

    abstract void aplicarAJugador(int actual,ArrayList<Jugador> todos);

}
