package civitas;

import java.util.ArrayList;

public class Sorpresa {
    private String texto;
    private int valor;
    private TipoSorpresa tipo;

    void aplicarAJugador(int actual,Jugador todos[]){
        

    }

    Sorpresa (TipoSorpresa tipo,String texto, int valor){
        this.texto=texto;
        this.valor=valor;
        this.tipo=tipo;

    }


    private 

    private void aplicarAJugador_pagarCobrar(int actual,ArrayList<Jugador> todos){

        todos.get(actual).modificaSaldo(this.valor);
    }

    void informe (int actual, ArrayList<Jugador> todos){

        Diario.getInstance().ocurreEvento(todos.get(actual) + this.toString());

    }

    public String toString(){
        
        return texto;
    }

    private void aplicarAJugador_porCasaHotel(int actual,ArrayList<>)
}
