package civitas;

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

    private static float FACTORALQUILERCALLE = 1.0f;
    private static float FACTORALQUILERCASA = 1.0f;
    private static float FACTORALQUILERHOTEL = 4.0f;

    public Casilla(String n){
        init();
        casilla = TipoCasilla.DESCANSO;
        nombre = n;
    }
    
    private void init(){
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
    }

    

    Casilla (TipoCasilla tipo = TipoCasilla.CALLE,String titulo,float PrecioCompra, float precioEdificar, float precioBaseAlquiler){
        
        this.init();
        casilla=tipo;

    }

    private void init(){

        casilla=null;
        nombre=" ";
        precioCompra=0;
        precioEdificar=0;
        precioBaseAlquiler=0;
        numCasas=0;
        numHoteles=0;

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

    }
  
} 

