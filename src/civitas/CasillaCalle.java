package civitas;

import java.util.ArrayList;

public class CasillaCalle extends Casilla {
    
    private static float FACTORALQUILERCALLE = 1.0f;
    private static float FACTORALQUILERCASA = 1.0f;
    private static float FACTORALQUILERHOTEL = 4.0f;

    private float precioCompra;
    private float precioEdificar;
    private float precioBaseAlquiler;
    
    private int numCasas;
    private int numHoteles;
    
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

    public int cantidadCasasHoteles(){

        return numCasas+numHoteles;
    }

    public int getNumcasas(){

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
        float precio;
        precio = precioBaseAlquiler * (FACTORALQUILERCALLE+
        numCasas*FACTORALQUILERCASA+
        numHoteles*FACTORALQUILERHOTEL);
        
        return precio;
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

    void recibeJugador(int iactual, ArrayList<Jugador>todos){
        informe(iactual, todos);
        Jugador jugador = todos.get(iactual);
        if(!tienePropietario()){
            jugador.puedeComprarCasilla();
        }
        else tramitarAlquiler(jugador);

    }


    @Override
    public String toString(){
        
        String cad;

        if(propietario==null){
            cad= "Calle" + nombre + "Precios de Compra: " + precioCompra +
            "Precio para edificar: " + precioEdificar + "Alquiler base: "+precioBaseAlquiler +
            "Casas: " +numCasas + "Hoteles: " + numHoteles;
        }

        else{

            cad=propietario.getNombre();

        }

        return cad;
    }

    public void actualizaPropietarioPorConversion(JugadorEspeculador jugador){

        propietario = jugador;
    }
}


