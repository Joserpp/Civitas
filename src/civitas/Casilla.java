package civitas;
import java.util.ArrayList;

enum TipoCasilla { CALLE, SORPRESA, DESCANSO }

enum TipoSorpresa {PAGARCOBRAR,PORCASAHOTEL}

public class Casilla {
    private TipoCasilla casilla;
    private String nombre;
    private float precioCompra;
    private float precioEdificar;
    private float precioBaseAlquiler;
    private int numCasas;
    private int numHoteles;
    private Jugador propietario;
    private static float FACTORALQUILERCALLE = 1.0f;
    private static float FACTORALQUILERCASA = 1.0f;
    private static float FACTORALQUILERHOTEL = 4.0f;
    private MazoSorpresas mazo;

    //Constructores de visibilidad de paquete
/* 
    public Casilla(String n){
        init();
        casilla = TipoCasilla.DESCANSO;
        nombre = n;
    }

    public Casilla (String titulo, float pC, float pE, float pBA){
        init();
        casilla = TipoCasilla.CALLE;
        nombre = titulo;
        precioCompra = pC;
        precioEdificar = pE;
        precioBaseAlquiler = pBA;
    }

    public Casilla (String n, MazoSorpresas mazo){
        init();
        casilla = TipoCasilla.SORPRESA;
        nombre = n;
        this.mazo=mazo;
    }   
*/
    Casilla( TipoCasilla tipo , String nombre)
    {
        init();
        this.casilla = tipo;
        this.nombre = nombre;
    }

// Constructor para casillas de tipo CALLE
    Casilla( TipoCasilla tipo, String nombre, float precioCompra, 
        float precioEdificar, float precioBaseAlquiler)
    {
        init();
        this.casilla = tipo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        this.precioBaseAlquiler = precioBaseAlquiler;
    }

// Constructor para casillas de tipo SORPRESA
    Casilla( TipoCasilla tipo, String nombre, MazoSorpresas mazo)
    {
        init();
        this.casilla = tipo;
        this.nombre = nombre;
        this.mazo = mazo;
    }
    private void init(){
        precioCompra = 0;
        precioEdificar = 0;
        precioBaseAlquiler = 0;
        numCasas = 0;
        numHoteles = 0;
        propietario = new Jugador("");
    }

    public int cantidadCasasHoteles(){

        return(numCasas+numHoteles);
    }

    public TipoCasilla getTipoCasilla(){
        return casilla;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public float getPrecioCompra(){
        return precioCompra;
    }
    
    public float getPrecioEdificar(){
        return precioEdificar;
    }
    
    public float getPrecioAlquilerCompleto(){
        float precio;
        precio = precioBaseAlquiler * (1 + numCasas + (numHoteles * 4));
        return precio;
    }
    
    public int getNumCasas(){
        return numCasas;
    }
    
    public int getNumHoteles(){
        return numHoteles;
    }
    public String toString(){
        String casilla_s = nombre;
        casilla_s += " Compra: " + precioCompra + ", Edificar: " + precioEdificar
                    +", Alquiler base: " + precioBaseAlquiler + ", Casas: "
                    + numCasas + ", Hoteles: " + numHoteles + ", Tipo: " + casilla;
        return casilla_s;
    }
    public void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario() && !this.esEsteElPropietario(jugador))
         {   jugador.pagaAlquiler(this.getPrecioAlquilerCompleto());
            propietario.recibe(this.getPrecioAlquilerCompleto());
         }
    }

    public boolean esEsteElPropietario(Jugador jugador){
        if(jugador == propietario)
            return true;
        else 
            return false;

    }
    public boolean tienePropietario(){
        if(propietario == null)
            return false;
        else
            return true;
    }

    boolean comprar(Jugador jugador){

        propietario=jugador;

        return jugador.paga(precioCompra);


    }


    boolean derruirCasas (int n, Jugador jugador){

        boolean condicion=false;

        if(this.esEsteElPropietario(jugador) && numCasas>=n){
            numCasas=numCasas-n;
            condicion=true;
        }
        return condicion;

    }


    boolean construirHotel(Jugador jugador){

        jugador.paga(precioEdificar);

        numHoteles++;

        return true;
    }


    boolean construirCasa(Jugador jugador){

        jugador.paga(this.getPrecioEdificar());
        numCasas++;
        return true;
    }

    void informe (int actual, ArrayList<Jugador>todos){

        Diario.getInstance().ocurreEvento(todos.get(actual) + 
                                          "ha caido en esta casilla cuyos datos son: "
                                           + this.toString());
    }

    void recibeJugador_calle(int iactual, ArrayList<Jugador>todos){
        informe(iactual, todos);
        Jugador jugador = todos.get(iactual);
        if(!tienePropietario()){
            jugador.puedeComprarCasilla();
        }
        else tramitarAlquiler(jugador);

    }

    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(casilla == TipoCasilla.CALLE){
            recibeJugador_calle(iactual, todos);
        }
        else if(casilla == TipoCasilla.SORPRESA){
            recibeJugador_sorpresa(iactual, todos);
        }
        else if(casilla == TipoCasilla.DESCANSO){
            informe(iactual, todos);
        }
    }

    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos)
    {
        // 1
        Sorpresa sorpresa = mazo.siguiente();
        // 2
        informe(iactual,todos);
        // 3
        sorpresa.aplicarAJugador(iactual,todos);
        
    }

} 

