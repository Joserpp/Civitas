package controladorCivitas;

import civitas.CivitasJuego;
import vistaTextualCivitas.Vista;
import vistaTextualCivitas.VistaTextual;
import civitas.OperacionJuego;
import civitas.OperacionInmobiliaria;

public class Controlador {

    private CivitasJuego juego;
    private VistaTextual vista;

    Controlador(CivitasJuego j, VistaTextual v){
        juego = j;
        vista = v;
    }
    
    public void juega(){
        while(!juego.finalDelJuego()){
            vista.actualiza();
            vista.pausa();
            OperacionJuego operacion = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            if(operacion != OperacionJuego.PASAR_TURNO){
                vista.mostrarEventos();;
            }

            switch(operacion){
                case COMPRAR:
                    if(vista.comprar() == Respuesta.SI){
                        juego.comprar();
                    }
                    juego.siguientePasoCompletado(operacion);
                break;

                case GESTIONAR:
                    OperacionInmobiliaria opera = vista.elegirOperacion();
                    if(opera != OperacionInmobiliaria.TERMINAR){
                        int num = vista.elegirPropiedad();
                        if(opera == OperacionInmobiliaria.CONSTRUIR_CASA){
                            juego.construirCasa(num);
                        }
                        else 
                            juego.construirHotel(num);
                    }
                    else 
                        juego.siguientePasoCompletado(operacion);
                break;
            }
        } 
        juego.ranking();
        vista.actualiza();
    }
}
