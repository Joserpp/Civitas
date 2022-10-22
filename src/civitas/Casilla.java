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
    private Jugador propietario=null;
    private static float FACTORALQUILERCALLE = 1.0f;
    private static float FACTORALQUILERCASA = 1.0f;
    private static float FACTORALQUILERHOTEL = 4.0f;
<<<<<<< HEAD
=======

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
>>>>>>> f0bb002 (ujj)
    
    public Casilla (String n, MazoSorpresas mazo){
        init();
        casilla = TipoCasilla.SORPRESA;
        nombre = n;
    }

    private void init(){
<<<<<<< HEAD

        casilla=null;
        nombre=" ";
        precioCompra=0;
        precioEdificar=0;
        precioBaseAlquiler=0;
        numCasas=0;
        numHoteles=0;

    }

    
    //Constructores de visibilidad de paquete


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
    }   

    
    void informe (int actual, ArrayList<Jugador>todos){

        Diario.getInstance().ocurreEvento(todos.get(actual)+ "ha caido en esta casilla cuyos datos son: "+this.toString());
=======
        nombre = " ";
        precioCompra = 0;
        precioEdificar = 0;
        precioBaseAlquiler = 0;
        numCasas = 0;
        numHoteles = 0;
    }
    
    //Constructores de visibilidad de paquete

    Casilla (TipoCasilla a,String nombre ){
            
            this.init();
            if(a==TipoCasilla.DESCANSO)
            casilla=a;

            else if(a==TipoCasilla.SORPRESA)
            casilla=a;
            this.nombre=nombre;
        casilla=TipoCasilla.CALLE;
>>>>>>> f0bb002 (ujj)
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
        if(jugador==propietario)
            return true;
        else 
            return false;

    }
    public boolean tienePropietario(){
        if(propietario==null)
            return false;
        else
            return true;
    }

    boolean derruirCasas (int n, Jugador jugador){

        boolean condicion=false;

        if(this.esEsteElPropietario(jugador) && numCasas>=n){
            numCasas=numCasas-n;
            condicion=true;
        }
        return condicion;

    }



} 

