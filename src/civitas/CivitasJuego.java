
package civitas;

import GUI.Dado;
import java.util.ArrayList;
import java.util.Collections;

import GUI.Dado;

public class CivitasJuego {

    private ArrayList<Jugador> jugadores;
 
    private EstadoJuego estado;

    private GestorEstados estadojuego;

    private MazoSorpresas mazo;

    private Tablero tablero;

    private int indiceJugadorActual;

    public CivitasJuego (ArrayList<String> nombres, boolean debug){
        
        jugadores = new ArrayList<Jugador>();
       
        for(int i = 0; i < nombres.size(); i++){

            Jugador jugador = new Jugador(nombres.get(i));

            jugadores.add(jugador);
        }

        estadojuego = new GestorEstados();

        estado = estadojuego.estadoInicial();
        
        Dado dado = Dado.getInstance(); 

        dado.setDebug(debug);

        indiceJugadorActual=dado.quienEmpieza(4);

        mazo = new MazoSorpresas(debug);

        tablero = new Tablero();

        inicializaMazoSorpresa();

        inicializaTablero(mazo);
    }

    private void inicializaTablero(MazoSorpresas mazo){

        tablero.añadeCasilla(new CasillaCalle("Velázquez", 60, 20, 50));
        tablero.añadeCasilla(new CasillaCalle( "Salamanca", 80, 30, 100));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        tablero.añadeCasilla(new CasillaCalle("Castellana", 100, 40, 200));
        tablero.añadeCasilla(new CasillaCalle("Tirso de M.", 120, 50, 300));
        tablero.añadeCasilla(new CasillaCalle("Lavapiés", 140, 60, 400));
        tablero.añadeCasilla(new CasillaCalle("Goya", 150, 70, 500));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        tablero.añadeCasilla(new CasillaCalle("Mataelpino", 200, 80, 600));
        tablero.añadeCasilla(new Casilla("Parking Público"));
        tablero.añadeCasilla(new CasillaCalle("Leganitos", 220, 90, 700));
        tablero.añadeCasilla(new CasillaCalle("Serrano", 240, 100, 800));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        tablero.añadeCasilla(new CasillaCalle("P. del Prado", 260, 110, 900));
        tablero.añadeCasilla(new CasillaCalle("Valdelatas", 280, 120, 1000));
        tablero.añadeCasilla(new CasillaCalle("Méndez A.", 300, 130, 1100));
        tablero.añadeCasilla(new CasillaSorpresa("Sorpresa", mazo));
        tablero.añadeCasilla(new CasillaCalle("O'Donell", 350, 140, 1200));
        tablero.añadeCasilla(new CasillaCalle("San Jerónimo", 400, 150, 1300));

    }

    private void inicializaMazoSorpresa(){

        mazo.alMazo(new SorpresaPagarCobrar("Sorpresa_1", 100));
        mazo.alMazo(new SorpresaPagarCobrar("Sorpresa_1", 500));
        mazo.alMazo(new SorpresaPagarCobrar("Sorpresa_1", 1000));
        mazo.alMazo(new SorpresaPagarCobrar("Sorpresa_1", -100));
        mazo.alMazo(new SorpresaPagarCobrar("Sorpresa_1", -500));
        mazo.alMazo(new SorpresaPagarCobrar("Sorpresa_1", -1000));
        mazo.alMazo(new SorpresaPorCasaHotel("Sorpresa_2", 100));
        mazo.alMazo(new SorpresaPorCasaHotel("Sorpresa_2", 1000));
        mazo.alMazo(new SorpresaPorCasaHotel("Sorpresa_2", -100));
        mazo.alMazo(new SorpresaPorCasaHotel("Sorpresa_2", -1000));
        mazo.alMazo(new SorpresaPorCasaHotel("Sorpresa_3", 0));
        mazo.alMazo(new SorpresaPorCasaHotel("Sorpresa_3", 0));
    }

    public Jugador getJugadorActual(){

        return jugadores.get(indiceJugadorActual);
    }

    public Tablero getTablero(){
        
        return tablero;
    }

    private void pasarTurno(){

        indiceJugadorActual = (indiceJugadorActual+1) % 4;

    }

    public void siguientePasoCompletado(OperacionJuego operacion){

        estado = estadojuego.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);

    }

    public boolean construirCasa(int ip){

        return jugadores.get(indiceJugadorActual).construirCasa(ip);

    }

    public boolean construirHotel(int ip){

        return jugadores.get(indiceJugadorActual).construirHotel(ip);

    }

    public boolean finalDelJuego(){

        boolean finalJuego = false;

        for(Jugador jugador : jugadores){

            if(jugador.enBancarrota())
                finalJuego = true;
        }
        
        return finalJuego;
    }

    public ArrayList<Jugador> ranking(){

        Collections.sort(jugadores, Jugador::compareTo);

        return jugadores;
    }

    private void contabilizarPasosPorSalida(){

        if(tablero.computarPasoPorSalida())
            jugadores.get(indiceJugadorActual).pasaPorSalida();

    }

    public boolean comprar(){

        Jugador jugadorActual = getJugadorActual();

        int numCasillaActual = jugadorActual.getCasillaActual();

        Casilla casilla = tablero.getCasilla(numCasillaActual);

        boolean puede = jugadorActual.comprar(casilla);

        return puede;
    }

    void avanzaJugador(){

        Jugador jugadorActual = getJugadorActual();

        int posicionActual = jugadorActual.getCasillaActual();

        int tirada = Dado.getInstance().tirar();

        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);

        Casilla casilla = tablero.getCasilla(posicionNueva);

        contabilizarPasosPorSalida();

        jugadorActual.moverACasilla(posicionNueva);

        casilla.recibeJugador(indiceJugadorActual, jugadores);
    }

    public OperacionJuego siguientePaso(){

        Jugador jugadorActual = getJugadorActual();

        OperacionJuego operacion = estadojuego.siguienteOperacion(jugadorActual, estado);

        if (operacion == OperacionJuego.PASAR_TURNO){

            pasarTurno();
            siguientePasoCompletado(operacion);
        }
        else if(operacion == OperacionJuego.AVANZAR){

            avanzaJugador();
            siguientePasoCompletado(operacion);
        }

        return operacion;
    }
}