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

    int cantidadCasasHoteles(){
        int cantidadcasashoteles=0;
        for(int i=0;i<propiedades.size();i++){
            cantidadcasashoteles+=propiedades.get(i).getNumCasas()+propiedades.get(i).getNumHoteles();

        }

        return cantidadcasashoteles;
    }

    public int compareTo(Jugador otro){
        return Float.compare(otro.saldo, saldo);
    }

    /*
    boolean comparar(Casilla titulo){

    }

    private boolean construirCasa(int ip){

    }

    private boolean construirHotel(int ip){

    }*/

    private boolean enBancarrota(){
        return (saldo < 0);
    }

    private boolean existeLaPropiedad(int ip){
        return (0 <= ip && ip <= propiedades.size());
    }

    private static int getCasasMax(){
        return CasasMax;
    }

    static int getCasaPorHotel(){
        return CasasPorHotel;
    }

    int getCasillaActual(){
        return casillaActual;
    }

    private static int getHotelesMax(){
        return HotelesMax;
    }

    protected String getNombre(){
        return nombre;
    }

    private static float getPremioPasoSalida(){
        return PasoPorSalida;
    }

    protected ArrayList<Casilla> getPropiedades(){
        return propiedades;
    }

    boolean PuedeComprar(){
        return puedeComprar;
    }

    protected float getSaldo(){
        return saldo;
    }

    void Jugador(String n){
        nombre = n;
        casillaActual = 0;
        puedeComprar = false;
        saldo = SaldoInicial;
    }

    protected void Jugador(Jugador otro){
        propiedades = otro.propiedades;
        casillaActual = otro.casillaActual;
        nombre = otro.nombre;
        puedeComprar = otro.puedeComprar;
        saldo = otro.saldo;
    }

    boolean modificarSaldo(float cantidad){

        saldo+=cantidad;
        Diario.getInstance().ocurreEvento("El saldo se ha actualizado");

        return true;
    }

    boolean moverACasilla(int nC){
        casillaActual = nC;
        puedeComprar = false;
        Diario.getInstance().ocurreEvento(nombre + " se ha movido a la casilla nº: " + (casillaActual+1));
        return true;
    }

    boolean paga(float cantidad){

        float nueva_cantidad=cantidad * (-1);

        return modificarSaldo(nueva_cantidad);
    }

    boolean pagaAlquiler(float cantidad){
        return paga(cantidad);
    }

    boolean pasaPorSalida(){
        recibe(PasoPorSallida);

        Diario.getInstance().ocurreEvento(nombre+);
    }

    boolean puedeComprarCasilla(){

        puedeComprar=true;

        return puedeComprar;
    }

    boolean recibe(float cantidad){

        return modificaSaldo(cantidad);
    }
}
