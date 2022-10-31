
package civitas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

enum EstadoJuego { INICIO_TURNO, DESPUES_AVANZAR, DESPUES_COMPRAR, DESPUES_GESTIONAR }

//import javax.lang.model.util.ElementScanner6;


public class CivitasJuego {
    
    private int indiceJugadorActual;

    private ArrayList<Jugador> jugadores;
 
    private EstadoJuego estado;

    private GestorEstados estadojuego;

    private MazoSorpresas mazo;

    private Tablero tablero;

    private GestorEstados gestor;
    
    //Constructor privado

    private CivitasJuego (ArrayList<String> nombres,boolean debug){
        
        jugadores=new ArrayList<Jugador>(4);
       
        for(int i=0;i<nombres.size();i++){
            String nombre_jugador = nombres.get(i);

            Jugador jugador = new Jugador(nombre_jugador);

            jugadores.add(jugador);

        }

        estado=estadojuego.estadoInicial();
        
        Dado.getInstance().setDebug(debug);

        indiceJugadorActual=Dado.getInstance().quienEmpieza(jugadores.size());

        mazo=new MazoSorpresas(debug);

        tablero=new Tablero();

        inicializaMazoSorpresa();

        inicializaTablero(mazo);



    }

    private void inicializaTablero(MazoSorpresas mazo){

        tablero.añadeCasilla(new Casilla("Puro relax"));

    }

    private void inicializaMazoSorpresa(){

        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, "prueba", 100));
    }

    public Jugador getJugadorActual(){

        return jugadores.get(indiceJugadorActual);
    }

    private void pasarTurno(){

        if(indiceJugadorActual==jugadores.size()){

            indiceJugadorActual=0;
        }

        else
            indiceJugadorActual++;

    }

    public void siguientePasoCompletado(OperacionJuego operacion){

        estadojuego.siguienteEstado (jugadores.get(indiceJugadorActual), estado, operacion);

    }

    public boolean construirCasa(int ip){

        return jugadores.get(indiceJugadorActual).construirCasa(ip);

    }

    public boolean construirHotel(int ip){

        return jugadores.get(indiceJugadorActual).construirHotel(ip);

    }

    public boolean finalDelJuego(){
        boolean condicion=false;

        for(int i=0;i<jugadores.size();i++){
            condicion=jugadores.get(i).enBancarrota();
        }
        return condicion;
    }

    private ArrayList<Jugador> ranking(){

        Collections.sort(jugadores,Jugador::compareTo);
        return jugadores;

    }

    private void contabilizarPasosPorSalida(){

        if(tablero.computarPasoPorSalida())
            jugadores.get(indiceJugadorActual).pasaPorSalida();

    }

    boolean comprar(){

        Jugador jugadorActual=jugadores.get(indiceJugadorActual);
        int numCasillaActual=jugadorActual.getCasillaActual();

        Casilla casilla=tablero.getCasilla(numCasillaActual);

        boolean res=jugadorActual.comprar(casilla);

        return res;

    }

    void avanzaJugador(){

        Jugador jugadorActual=getJugadorActual();

        int posicionActual=jugadorActual.getCasillaActual();

        int tirada=Dado.getInstance().tirar();

        int posicionNueva=tablero.nuevaPosicion(posicionActual,tirada);

        Casilla casilla=tablero.getCasilla(posicionNueva);

        this.contabilizarPasosPorSalida();

        jugadorActual.moverACasilla(posicionNueva);

        casilla.recibeJugador(indiceJugadorActual,jugadores);
    }

    public OperacionJuego siguientePaso(){
        Jugador jugadorActual = getJugadorActual();

        OperacionJuego opercion = gestor.siguienteOperacion(jugadorActual, estado);

        if (opercion == OperacionJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompletado(opercion);
        }
        else if(opercion == OperacionJuego.AVANZAR){
            avanzaJugador();
            siguientePasoCompletado(opercion);
        }

        return opercion;
    }
}