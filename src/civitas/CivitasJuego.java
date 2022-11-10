
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
    
    //Constructor privado

    public CivitasJuego (ArrayList<String> nombres,boolean debug){
        
        jugadores=new ArrayList<Jugador>(4);
       
        for(int i=0;i<nombres.size();i++){
            String nombre_jugador = nombres.get(i);

            Jugador jugador = new Jugador(nombre_jugador);

            jugadores.add(jugador);

        }
        estadojuego = new GestorEstados();
        estado=estadojuego.estadoInicial();
        
        Dado.getInstance().setDebug(debug);

        indiceJugadorActual=Dado.getInstance().quienEmpieza(jugadores.size());

        mazo=new MazoSorpresas(debug);

        tablero=new Tablero();

        inicializaMazoSorpresa();

        inicializaTablero(mazo);



    }

    private void inicializaTablero(MazoSorpresas mazo){

        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE, "Velázquez", 60, 20, 50));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE, "Salamanca", 80, 30, 100));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Castellana", 100, 40, 200));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Tirso de M.", 120, 50, 300));
        tablero.añadeCasilla(new Casilla(TipoCasilla.SORPRESA,"Sorpresa", mazo));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Lavapiés", 140, 60, 400));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Goya", 150, 70, 500));
        tablero.añadeCasilla(new Casilla(TipoCasilla.SORPRESA,"Sorpresa", mazo));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Mataelpino", 200, 80, 600));
        tablero.añadeCasilla(new Casilla(TipoCasilla.DESCANSO,"Puro relax"));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Leganitos", 220, 90, 700));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Serrano", 240, 100, 800));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"P. del Prado", 260, 110, 900));
        tablero.añadeCasilla(new Casilla(TipoCasilla.SORPRESA,"Sorpresa", mazo));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Valdelatas", 280, 120, 1000));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"Méndez A.", 300, 130, 1100));
        tablero.añadeCasilla(new Casilla(TipoCasilla.SORPRESA,"Sorpresa", mazo));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"O'Donell", 350, 140, 1200));
        tablero.añadeCasilla(new Casilla(TipoCasilla.CALLE,"San Jerónimo", 400, 150, 1300));

    }

    private void inicializaMazoSorpresa(){

        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,"Sorpresa_1", 100));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,"Sorpresa_1", 500));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,"Sorpresa_1", 1000));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,"Sorpresa_1", -100));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,"Sorpresa_1", -500));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR,"Sorpresa_1", -1000));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,"Sorpresa_2", 100));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,"Sorpresa_2", 1000));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,"Sorpresa_2", -100));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,"Sorpresa_2", -1000));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,"Sorpresa_3", 0));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL,"Sorpresa_3", 0));
    }

    public Jugador getJugadorActual(){

        return jugadores.get(indiceJugadorActual);
    }

    public Tablero getTablero(){
        return tablero;
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
        boolean bancarrota=false;

        for(int i=0;i<jugadores.size() && !bancarrota;i++){
            bancarrota=jugadores.get(i).enBancarrota();
        }
        
        return bancarrota;
    }

    public ArrayList<Jugador> ranking(){

        Collections.sort(jugadores,Jugador::compareTo);
        return jugadores;

    }

    private void contabilizarPasosPorSalida(){

        if(tablero.computarPasoPorSalida())
            jugadores.get(indiceJugadorActual).pasaPorSalida();

    }

    public boolean comprar(){

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