package civitas;
import java.util.ArrayList;

public abstract class Jugador implements Comparable<Jugador>{
    protected static int CasasMax=4;
    protected static int CasasPorHotel=4;
    private int casillaActual;
    protected static int HotelesMax=4;
    private String nombre;
    protected static float PasoPorSalida=1000;
    private boolean puedeComprar;
    private float saldo;
    private static float SaldoInicial=7500;
    private ArrayList<Casilla> propiedades=new ArrayList<Casilla>(0); 
    
    
    private boolean existeLaPropiedad(int ip){



    }

    boolean puedeComprarCasilla(){

        puedeComprar=true;

        return puedeComprar;
    }

    boolean modificaSaldo(float cantidad){

        saldo+=cantidad;
        Diario.getInstance().ocurreEvento("El saldo se ha actualizado");

        return true;
    }

    boolean paga(float cantidad){

        float nueva_cantidad=cantidad * (-1);

        return modificaSaldo(nueva_cantidad);
    }

    boolean pagaAlquiler(float cantidad){
        return paga(cantidad);
    }

    boolean recibe(float cantidad){

        return modificaSaldo(cantidad);
    }

}
