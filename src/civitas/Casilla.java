package civitas;

import java.util.ArrayList;

public class Casilla {
    
    
    private static float FACTORALQUILERCALLE = 1.0f;
    private static float FACTORALQUILERCASA = 1.0f;
    private static float FACTORALQUILERHOTEL = 4.0f;

    private String nombre;
    
    private float precioCompra;
    private float precioEdificar;
    private float precioBaseAlquiler;
    
    private int numCasas;
    private int numHoteles;
    
    private Jugador propietario;
    private MazoSorpresas mazo;
    private TipoCasilla tipo;
    private Sorpresa sorpresa;

    Casilla( TipoCasilla tipo , String nombre)
    {
        init();
        this.tipo = tipo;
        this.nombre = nombre;
    }

// Constructor para casillas de tipo CALLE
    Casilla( TipoCasilla tipo, String nombre, float precioCompra, 
        float precioEdificar, float precioBaseAlquiler)
    {
        init();
        this.tipo = tipo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        this.precioBaseAlquiler = precioBaseAlquiler;
    }

// Constructor para casillas de tipo SORPRESA
    Casilla( TipoCasilla tipo, String nombre, MazoSorpresas mazo)
    {
        init();
        this.tipo = tipo;
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

    //Consultores

    public int cantidadCasasHoteles(){

        return(numCasas+numHoteles);
    }

    public TipoCasilla getTipoCasilla(){
        return tipo;
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
        precio = precioBaseAlquiler * (FACTORALQUILERCALLE+
        numCasas*FACTORALQUILERCASA+
        numHoteles*FACTORALQUILERHOTEL);
        
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
                    + numCasas + ", Hoteles: " + numHoteles + ", Tipo: " + tipo;
        return casilla_s;
    }
    
    //Calculo
    
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
        return (!"".equals(propietario.getNombre()));
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

        Diario.getInstance().ocurreEvento(" El jugador: " + todos.get(actual).getNombre() + 
                                          " ha caido en esta casilla cuyos datos son: "
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
        if(tipo == TipoCasilla.CALLE){
            recibeJugador_calle(iactual, todos);
        }
        else if(tipo == TipoCasilla.SORPRESA){
            recibeJugador_sorpresa(iactual, todos);
        }
        else if(tipo == TipoCasilla.DESCANSO){
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

