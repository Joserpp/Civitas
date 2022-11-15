package civitas;
import java.util.ArrayList;

public class Jugador implements Comparable <Jugador> {
    
    protected static int CasasMax = 4;
    protected static int CasasPorHotel = 4;
    protected static int HotelesMax = 4;
    protected static float PasoPorSalida = 1000;
    
    private static float SaldoInicial = 7500;
    
    private int casillaActual;
    private String nombre;
    private boolean puedeComprar;
    private float saldo;
    private ArrayList<Casilla> propiedades;
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    int cantidadCasasHoteles()
    {
        int cantidad = 0;
        
        for (Casilla propiedad : propiedades){

            cantidad = cantidad + propiedad.getNumCasas() +
                        propiedad.getNumHoteles();
        }
        
        return cantidad;
    }
    
    @Override
    public int compareTo(Jugador otro)
    {
        return Float.compare(otro.saldo, saldo);
    }
    
    boolean comprar(Casilla titulo)
    {
        boolean result = false;
        
        puedeComprar=true;
        if(puedeComprar){
            // 1
            float precio = titulo.getPrecioCompra();
            if(puedoGastar(precio)){
                // 2
                result = titulo.comprar(this);
                // 3
                propiedades.add(titulo);
                
                // 4
                Diario.getInstance().ocurreEvento(("El jugador " + this.getNombre() + 
                        " compra la propiedad " + titulo.getNombre() ));
                // 5
                puedeComprar = false;
            }
            
            else{
                // 6
                Diario.getInstance().ocurreEvento("El jugador " + this +
                        " no tiene saldo para comprar la propiedad " + titulo );
            }
        }
            
        
        return result;
    }
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// CASAS Y HOTELES //
    boolean construirCasa(int ip)
    {
        // 1
        boolean result = false;
        // 2
        boolean existe = existeLaPropiedad(ip);
        if (existe){
            // 3
            Casilla propiedad = propiedades.get(ip);
            // 4
            boolean puedoEdificar = puedoEdificarCasa(propiedad);                
            if(puedoEdificar){
                // 5 
                result = propiedad.construirCasa(this);
                Diario.getInstance().ocurreEvento("El jugador " + nombre + 
                        " contruye casa en la propiedad " + ip);
                 
            }
            
        }
        return result;
    }
            
    boolean construirHotel(int ip)
    {
        boolean result = false;
        
        if(existeLaPropiedad(ip)){
            // 1
            Casilla propiedad = propiedades.get(ip);
            // 2
            boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);
            
            if(puedoEdificarHotel){
                // 3
                result = propiedad.construirHotel(this);
                // 4
                propiedad.derruirCasas(CasasPorHotel, this);
                // 5
                Diario.getInstance().ocurreEvento("El jugador " + getNombre() + 
                        " construye hotel en la propiedad " + ip);
                
            }   
        }
        return result; 
    }

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    boolean enBancarrota()
    {
        return saldo < 0;
    }
    
    private boolean existeLaPropiedad(int ip)
    {
        return 0 <= ip && ip <= propiedades.size();
    }

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// CONSULTORES //
    
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

    private float getPremioPasoSalida()
    {
        return PasoPorSalida;
    }
    
    public ArrayList<Casilla> getPropiedades()
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

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORES //    
    
    Jugador(String nombre)
    {
        this.nombre = nombre;
        propiedades = new ArrayList<>();
        saldo = SaldoInicial;
    }

    protected Jugador(Jugador otro)
    {
        this.nombre = otro.nombre;
        this.propiedades = otro.propiedades;
        this.saldo = otro.saldo;
    }

////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    boolean modificarSaldo(float cantidad)
    {
        saldo += cantidad;
        Diario diario = Diario.getInstance();
        if(cantidad > 0)    
            diario.ocurreEvento("  " + nombre + 
                                " ha incrementado su saldo: +" + cantidad);
        else
            diario.ocurreEvento("  " + nombre + 
                                " ha decrecrementado su saldo: " + cantidad); 
        return true;
    }
    
    boolean moverACasilla(int numCasilla)
    {
        casillaActual = numCasilla;
        puedeComprar = false;
        
        Diario.getInstance().ocurreEvento("  " + nombre + 
                        " se ha movido a la casilla nº " + (casillaActual+1));
        
        return true;
    }
    
    boolean paga(float cantidad)
    {
        return modificarSaldo(cantidad*(-1));
    }
    
    boolean pagaAlquiler(float cantidad)
    {
        return paga(cantidad);
    }
    
    boolean pasaPorSalida()
    {
        recibe(PasoPorSalida);
        
        Diario.getInstance().ocurreEvento("  " + nombre + " ha pasado por "
                            + "la salida");
        
        return true;
    }
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
   
    boolean puedeComprarCasilla()
    {
        puedeComprar = true;
        return puedeComprar;
    }
    
    
    private boolean puedoEdificarCasa(Casilla propiedad)
    {
        boolean puedoEdificarCasa = false;
        // 4.1
        float precioEdificar = propiedad.getPrecioEdificar();

        if (puedoGastar(precioEdificar) && 
                propiedad.getNumCasas() < getCasasMax())
            // 4.2
            puedoEdificarCasa = true;
        
        return puedoEdificarCasa;   
    }
    
    private boolean puedoEdificarHotel(Casilla propiedad)
    {
        // 2.1
        boolean puedoEdificarHotel = false;
        // 2.2
        float precio = propiedad.getPrecioEdificar();
            
        if (puedoGastar(precio))
            if(propiedad.getNumHoteles() < getHotelesMax() && 
                    propiedad.getNumCasas() >= getCasasPorHotel())
                // 2.3
                puedoEdificarHotel = true;
        
        return puedoEdificarHotel;
    }
    
    private boolean puedoGastar(float precio)
    {
        return (saldo >= precio);
    }
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
    boolean recibe(float cantidad)
    {
        return modificarSaldo(cantidad);
    }

    boolean tieneAlgoQueGestionar()
    {
        boolean tiene = true;
        
        if (propiedades == null)
            tiene = false;
        
        return tiene;
    }
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////    
// SERIALIZA //
    
    public String toString()
    {
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
}
