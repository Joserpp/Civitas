package civitas;

import java.util.ArrayList;

public class Tablero {

    private ArrayList<Casilla> casillas;
    private boolean porSalida;

    private void init(){
        casillas = new ArrayList<Casilla>(0);
        porSalida = false;
        
    }

    public Tablero (){
        init();
    }

    private boolean correcto(int numCasilla){
        boolean valido = false;
        if (numCasilla >= 0 && numCasilla < casillas.size())
            valido = true;

        return valido;
    }

    boolean computarPasoPorSalida(){
        boolean condicion=porSalida;
        
        porSalida = false;
        
        return condicion;
        
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

        if (nuevaPos > casillas.size()){

            nuevaPos = nuevaPos % casillas.size();
            porSalida = true;
        }

        return nuevaPos;
    }
}
