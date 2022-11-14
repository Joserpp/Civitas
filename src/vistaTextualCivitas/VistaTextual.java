package vistaTextualCivitas;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class VistaTextual implements Vista {
  
    
  private static String separador = "=====================";
  int iGestion   = -1;
  int iPropiedad = -1;
  private Scanner in;
  
  CivitasJuego juegoModel;
  
  public VistaTextual (CivitasJuego juegoModel) {
    in = new Scanner (System.in);
    this.juegoModel=juegoModel;
  }
  
  
           
 @Override
 public  void pausa() {
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
  
  @Override
  public void actualiza(){
      System.out.println(this.juegoModel.getJugadorActual().toString());
      if(this.juegoModel.finalDelJuego())
          this.juegoModel.ranking();
    
  }
      
     
  @Override
  public Respuesta comprar(){
    
    int indice = juegoModel.getJugadorActual().getCasillaActual();
    String casillaActual = juegoModel.getTablero().getCasilla(indice).toString();
      
    int opcion = menu ("Has llegado a la casilla\n" + casillaActual + "\n¿Quieres comprarla?",
                        new ArrayList<> (Arrays.asList("SI","NO") ) 
                      );
    return (Respuesta.values()[opcion]);
  }
  
  @Override
  public OperacionInmobiliaria elegirOperacion(){
   
      int opcionOperacion = menu ("¿Qué número de gestión inmobiliara quieres realizar?",
                        new ArrayList<> (Arrays.asList("-> CONSTRUIR_CASA", 
                                                       "-> CONSTRUIR_HOTEL",
                                                       "-> TERMINAR")
                        )
      );
      
      return OperacionInmobiliaria.values()[opcionOperacion];
  }
  
  @Override
  public int elegirPropiedad(){
    int numProps   = this.juegoModel.getJugadorActual().getPropiedades().size();
    
    int opcionProp = this.leeEntero(numProps, 
                       "¿Sobre qué propiedad quieres hacer la gestión? ", 
                        "Valor Erróneo");
    
   
    return opcionProp;
  }
  
  @Override
  public void mostrarSiguienteOperacion(OperacionJuego operacion){
      String opera = "Siguiente operacion: " + operacion.toString();
      System.out.println(opera);
  }
  
  @Override
  public void mostrarEventos(){
      while(Diario.getInstance().eventosPendientes())
          System.out.println(Diario.getInstance().leerEvento());
  }

}