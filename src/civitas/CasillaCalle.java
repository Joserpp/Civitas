package civitas;

import java.util.ArrayList;
public class CasillaCalle extends Casilla {
    
    private static float FACTORALQUILERCALLE = 1.0f,
                         FACTORALQUILERCASA = 1.0f,
                         FACTORALQUILERHOTEL = 4.0f;

    private float precioCompra,
                  precioEdificar,
                  precioBaseAlquiler;
    
    private int numCasas,
                numHoteles;
    
    private Jugador propietario;


    public CasillaCalle(String nombre, float precioCompra,float precioEdificar, float precioBaseAlquiler){
        
        super(nombre);
        this.precioCompra=precioCompra;
        this.precioEdificar=precioEdificar;
        this.precioBaseAlquiler=precioBaseAlquiler;
        this.numCasas=0;
        this.numHoteles=0;
        this.propietario=null;
    }
    
    public Jugador getPropietario(){
    
        return propietario;
    }

    public int cantidadCasasHoteles(){

        return (numCasas+numHoteles);
    }

    public int getNumCasas(){

        return numCasas;
    }

    public int getNumHoteles(){

        return numHoteles;

    }

    public float getPrecioCompra(){
        return precioCompra;
    }
    
    public float getPrecioEdificar(){
        return precioEdificar;
    }
    
    public float getPrecioAlquilerCompleto(){
        float PrecioAlquilerCompleto;
        PrecioAlquilerCompleto = precioBaseAlquiler * (FACTORALQUILERCALLE + (numCasas*FACTORALQUILERCASA) +
                                 (numHoteles*FACTORALQUILERHOTEL));
        
        return PrecioAlquilerCompleto;
    }
    
    public void tramitarAlquiler(Jugador jugador){
        
        if(tienePropietario() && !esEsteElPropietario(jugador)){
            
            jugador.pagaAlquiler(getPrecioAlquilerCompleto());
            propietario.recibe(getPrecioAlquilerCompleto());
        }
    }

    public boolean esEsteElPropietario(Jugador jugador){

        boolean espropietario = false;
        
        if(jugador == propietario)
            espropietario = true;

        return espropietario;
    }

    public boolean tienePropietario(){
        
        boolean tiene = false;

        if(propietario != null)
            tiene = true;

        return tiene;
    }

    boolean comprar(Jugador jugador){

        propietario = jugador;

        boolean puede = jugador.paga(precioCompra);

        return puede;
    }


    boolean derruirCasas (int n, Jugador jugador){

        boolean derruir = false;

        if(esEsteElPropietario(jugador) && numCasas >= n){
            
            numCasas -= n;
            derruir = true;
        }

        return derruir;
    }


    boolean construirHotel(Jugador jugador){

        jugador.paga(getPrecioEdificar());
        numHoteles++;
        return true;
    }


    boolean construirCasa(Jugador jugador){

        jugador.paga(getPrecioEdificar());
        numCasas++;
        return true;
    }

    void recibeJugador(int iactual, ArrayList<Jugador>todos){
        
        informe(iactual, todos);
        Jugador jugador = todos.get(iactual);

        if(!tienePropietario()){

            jugador.puedeComprarCasilla();
        }
        else 
            tramitarAlquiler(jugador);
    }


    @Override
    public String toString(){
        
        String cad;

        if(propietario == null){
            
            cad = "Calle: " + nombre + 
                  " Precio de Compra: " + precioCompra +
                  " Precio para edificar: " + precioEdificar + 
                  " Alquiler base: " + precioBaseAlquiler +
                  " Casas: " +numCasas + 
                  " Hoteles: " + numHoteles;
        }

        else
            cad = propietario.getNombre();

        return cad;
    }

    public void actualizaPropietarioPorConversion(JugadorEspeculador jugador){

        propietario = jugador;
    }
}


