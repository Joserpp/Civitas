package vistaTextualCivitas;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import civitas.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class VistaTextual implements Vista {
  
    
  private static String separador = "=====================";
  
  private Scanner in;
  
  CivitasJuego juegoModel;
  
  public VistaTextual (CivitasJuego juegoModel) {
    in = new Scanner (System.in);
    this.juegoModel=juegoModel;
  }
  
  
           
 public void pausa() {
    System.out.print ("\nPulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  public void actualiza(){

    System.out.println(juegoModel.getJugadorActual().toString());

    if(juegoModel.finalDelJuego())
      juegoModel.ranking();

  }

  public Respuesta comprar(){

    ArrayList<String> opciones= new ArrayList<>();
    opciones.add("SI");
    opciones.add("NO");

   int respuesta=menu("Quiere comprar la calle " + juegoModel.getTablero().getCasilla(juegoModel.getJugadorActual().getCasillaActual()).toString(),opciones); 
  
    return(Respuesta.values()[respuesta]);

  }

  public OperacionInmobiliaria elegirOperacion(){

    int operador=0;

    operador = menu("Numero de gestion inmobiliaria utilizado: ", new ArrayList<> (Arrays.asList("-> CONSTRUIR_CASA","-> CONSTRUIR_HOTEL","-> TERMINAR"))); 

    return(OperacionInmobiliaria.values()[operador]);
  }


  //IMPLEMENTADA POR MI

  public int elegirPropiedad(){
    
    ArrayList<String> propiedades= new ArrayList<>();

    for(int i=0;i<this.juegoModel.getJugadorActual().getPropiedades().size();i++)
      propiedades.add(this.juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());    
    
    int opcionOperacion =menu("¿Sobre qué propiedad quieres hacer la gestión? ", propiedades);
    
    return opcionOperacion;
  

  }

  public void mostrarSiguienteOperacion(OperacionJuego operacion){

    System.out.println(operacion.toString());


  }

  public void mostrarEventos(){

    while(Diario.getInstance().eventosPendientes())

      System.out.println(Diario.getInstance().leerEvento());

  }

}
