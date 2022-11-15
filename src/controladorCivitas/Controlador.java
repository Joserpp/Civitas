package controladorCivitas;

import civitas.CivitasJuego;
import civitas.OperacionJuego;
import vistaTextualCivitas.VistaTextual;
import civitas.OperacionInmobiliaria;
import civitas.Jugador;
import java.util.ArrayList;

public class Controlador {
    private CivitasJuego juegoModel;
    private VistaTextual vista;
    
    public Controlador (CivitasJuego juegoModel, VistaTextual vista){
        this.juegoModel = juegoModel;
        this.vista = vista;
    }
    
    public void juega(){
        while(!juegoModel.finalDelJuego()){
            vista.actualiza();
            vista.pausa();
            OperacionJuego sigPaso = juegoModel.siguientePaso();
            vista.mostrarSiguienteOperacion(sigPaso);
            if(sigPaso != OperacionJuego.PASAR_TURNO)
              vista.mostrarEventos();
            

            switch(sigPaso){
                case COMPRAR:
                    if(vista.comprar()==Respuesta.SI){
                        
                        juegoModel.comprar();
                    }
                    
                    juegoModel.siguientePasoCompletado(sigPaso);
                break;
                
                case GESTIONAR:
                    OperacionInmobiliaria operacion = vista.elegirOperacion();
                    if (operacion != OperacionInmobiliaria.TERMINAR){
                        int numero = vista.elegirPropiedad();
                        if (operacion == OperacionInmobiliaria.CONSTRUIR_CASA)
                            juegoModel.construirCasa(numero);
                        else
                            juegoModel.construirHotel(numero);
                    }
                    else
                        juegoModel.siguientePasoCompletado(sigPaso);
                break;
            }                                     
        }
        
        System.out.println("\n---RANKING---ºn");
        ArrayList<Jugador> ranking = juegoModel.ranking();
        vista.actualiza();
    }
    
}