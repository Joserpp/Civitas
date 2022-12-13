package civitas;
import java.util.ArrayList;

public class Jugador implements Comparable <Jugador> {
    
    protected static int CasasMax = 4,
                         CasasPorHotel = 4,
                         HotelesMax = 4;

    protected static float PasoPorSalida = 1000;
    
    private static float SaldoInicial = 7500;
    
    private int casillaActual;
    private String nombre;
    private boolean puedeComprar;
    private float saldo;
    ArrayList<CasillaCalle> propiedades;

    Jugador(String nombre)
    {
        this.nombre = nombre;
        propiedades = new ArrayList<>();
        saldo = SaldoInicial;
        puedeComprar = true;
    }

    protected Jugador(Jugador otro)
    {
        this.nombre = otro.nombre;
        this.propiedades = otro.propiedades;
        this.saldo = otro.saldo;
        this.puedeComprar = otro.puedeComprar;
    }

    boolean enBancarrota()
    {
        return saldo < 0;
    }
    
    private boolean existeLaPropiedad(int ip)
    {
        return ((0 <= ip) && (ip <= propiedades.size()));
    }
    
    private static int getCasasMax()
    {
        return CasasMax;
    }

    static int getCasasPorHotel() 
    {
        return CasasPorHotel;
    }

    public int getCasillaActual() 
    {
        return casillaActual;
    }
        
    private static int getHotelesMax() 
    {
        return HotelesMax;
    }

    public String getNombre() 
    {
        return nombre;
    }
    
    public ArrayList<CasillaCalle> getPropiedades()
    {
        return propiedades;
    }
    
    boolean getPuedeComprar() 
    {
        return puedeComprar;
    }

    protected float getSaldo() 
    {
        return saldo;
    }
    
    int cantidadCasasHoteles()
    {
        int cantidadCH = 0;
        
        for (Casilla propiedad : propiedades){

            cantidadCH += ((CasillaCalle)propiedad).getNumCasas() +
                          ((CasillaCalle)propiedad).getNumHoteles();
        }
        
        return cantidadCH;
    }
    
    public int compareTo(Jugador otro){

        return Float.compare(otro.saldo, saldo);
    }
    
    boolean comprar(Casilla titulo){

        boolean puede = false;

        puedeComprar = true;
        
        if(puedeComprar){
            
            float precio = ((CasillaCalle)titulo).getPrecioCompra();

            if(puedoGastar(precio)){
                
                puede = ((CasillaCalle)titulo).comprar(this);
                
                propiedades.add((CasillaCalle)titulo);
                
                Diario.getInstance().ocurreEvento(("El jugador " + this.getNombre() + 
                        " ha comprado la propiedad: " + titulo.getNombre() ));

                puedeComprar = false;
            }
            
            else{
                
                Diario.getInstance().ocurreEvento("El jugador " + this +
                        " no tiene saldo para comprar la propiedad: " + titulo );
            }
        }
        
        return puede;
    }
    
    boolean construirCasa(int ip){

        boolean puede = false;

        if (existeLaPropiedad(ip)){
            
            Casilla propiedad = propiedades.get(ip);    

            if(puedoEdificarCasa(propiedad)){
                
                puede = ((CasillaCalle)propiedad).construirCasa(this);

                Diario.getInstance().ocurreEvento("El jugador " + nombre + 
                        " ha construido una casa en la propiedad: " + ip);
                 
            }
        }

        return puede;
    }
            
    boolean construirHotel(int ip){

        boolean puede = false;
        
        if(existeLaPropiedad(ip)){
            
            Casilla propiedad = propiedades.get(ip);
            
            if(puedoEdificarHotel(propiedad)){
                
                puede = ((CasillaCalle)propiedad).construirHotel(this);
                
                ((CasillaCalle)propiedad).derruirCasas(CasasPorHotel, this);
               
                Diario.getInstance().ocurreEvento("El jugador " + getNombre() + 
                        " ha construido un hotel en la propiedad: " + ip);
                
            }   
        }
        return puede; 
    }
    
    boolean modificarSaldo(float cantidad){

        saldo += cantidad;

        Diario diario = Diario.getInstance();

        if(cantidad > 0)    
            diario.ocurreEvento("El jugador " + nombre + 
                                " ha incrementado su saldo: +" + cantidad);
        else
            diario.ocurreEvento("El jugador " + nombre + 
                                " ha decrecrementado su saldo: " + cantidad);

        return true;
    }
    
    boolean moverACasilla(int numCasilla){

        casillaActual = numCasilla;
        puedeComprar = false;
        
        Diario.getInstance().ocurreEvento("El jugador " + nombre + 
                        " se ha movido a la casilla nº " + (casillaActual+1));
        
        return true;
    }
    
    boolean paga(float cantidad){

        return modificarSaldo(cantidad*(-1));
    }
    
    boolean pagaAlquiler(float cantidad){

        return paga(cantidad);
    }
    
    boolean pasaPorSalida(){

        recibe(PasoPorSalida);
        
        Diario.getInstance().ocurreEvento("El jugador " + nombre + " ha pasado por la salida");
        
        return true;
    }
   
    boolean puedeComprarCasilla(){

        puedeComprar = true;
        return puedeComprar;
    }
    
    
    private boolean puedoEdificarCasa(Casilla propiedad){

        boolean puedoEdificarCasa = false;
        
        float precioEdificar = ((CasillaCalle)propiedad).getPrecioEdificar();

        if (puedoGastar(precioEdificar) && (((CasillaCalle)propiedad).getNumCasas() < getCasasMax()))
            puedoEdificarCasa = true;
        
        return puedoEdificarCasa;   
    }
    
    private boolean puedoEdificarHotel(Casilla propiedad){
        
        boolean puedoEdificarHotel = false;
        
        float precio = ((CasillaCalle)propiedad).getPrecioEdificar();
            
        if (puedoGastar(precio) && (((CasillaCalle)propiedad).getNumHoteles() < getHotelesMax()) 
            && (((CasillaCalle)propiedad).getNumCasas() >= getCasasPorHotel()))

                puedoEdificarHotel = true;
        
        return puedoEdificarHotel;
    }
    
    private boolean puedoGastar(float precio){

        return (saldo >= precio);
    }
    
    boolean recibe(float cantidad){

        return modificarSaldo(cantidad);
    }

    boolean tieneAlgoQueGestionar(){

        boolean tiene = true;
        
        if (propiedades == null)
            tiene = false;
        
        return tiene;
    }
    
    public String toString(){

        String cadena = "\nJugador actual: " + this.nombre + "\n";
        cadena += "Propiedades: ";

        if(propiedades.isEmpty())
            cadena += "no tiene propiedades";
        else for(Casilla propiedad: propiedades)
            cadena += "\t"+ propiedad.getNombre()+ "\n";
        
        cadena += "\nPresupuesto: " + this.saldo;    
        cadena += "\nCasilla actual: "+(this.casillaActual+1);
        
        return cadena;
    }

    protected Jugador convertir(){

        JugadorEspeculador jugador = new JugadorEspeculador(this);

        return jugador;
    }
}