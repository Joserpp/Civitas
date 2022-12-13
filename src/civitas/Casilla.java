package civitas;

import java.util.ArrayList;

public class Casilla {

    protected String nombre;

    Casilla(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    void informe (int actual, ArrayList<Jugador>todos){

        Diario.getInstance().ocurreEvento(" El jugador: " + todos.get(actual).getNombre() + 
                                          ", ha caido en la casilla: " + toString());
    }
    void recibeJugador(int actual, ArrayList<Jugador>todos){
        informe(actual, todos);
    }

    public String toString(){

        return nombre;
    }
} 

