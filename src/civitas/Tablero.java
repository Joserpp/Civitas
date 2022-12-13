package civitas;

import java.util.ArrayList;

public class Tablero {

    private ArrayList<Casilla> casillas;
    private boolean porSalida;

    private void init(){

        casillas = new ArrayList<Casilla>(0);
        casillas.add(new Casilla("Salida"));
        porSalida = false;
    }

    public Tablero (){
        init();
    }

    private boolean correcto(int numCasilla){

        return ((numCasilla >= 0) && (numCasilla < casillas.size()));
    }

    boolean computarPasoPorSalida(){

        boolean pasoPorSalida = porSalida;
        
        porSalida = false;
        
        return pasoPorSalida;
        
    }

    void añadeCasilla(Casilla casilla){

        casillas.add(casilla);
    }

    public Casilla getCasilla (int numCasilla){

        if (correcto(numCasilla))
            return casillas.get(numCasilla);

        else return null;
    }

    public ArrayList<Casilla> geCasillas(){

        return casillas;
    }

    int nuevaPosicion (int actual, int tirada){

        int nuevaPos;

        nuevaPos = actual + tirada;

        if (nuevaPos >= casillas.size()){

            nuevaPos %= casillas.size();
            porSalida = true;
        }

        return nuevaPos;
    }
}
