package civitas;

import java.util.ArrayList;

public class Tablero {

    private ArrayList<Casilla> casillas;
    private boolean porSalida;

    private void init(){
        casillas = new ArrayList<Casilla>(0);
        porSalida = false;
        //Casilla Salida = new Casilla(TipoCasilla.CALLE, "Salida", 0, 0, 0);
        //casillas.add(Salida);
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
        porSalida = false;
        return porSalida;
        
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

        if (nuevaPos > casillas.size())
            nuevaPos = casillas.size()%nuevaPos;

        return nuevaPos;
    }
}
