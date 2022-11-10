/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package civitas;

import controladorCivitas.Controlador;
import vistaTextualCivitas.VistaTextual;
import java.util.ArrayList;
/**
 *
 * @author carme
 */
public class JuegoTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<String> jugadores = new ArrayList<>();
        jugadores.add("A");
        jugadores.add("B");
        jugadores.add("C");
        jugadores.add("D");
        CivitasJuego juego = new CivitasJuego(jugadores, false);
        
        VistaTextual vista = new VistaTextual(juego);
        
        Controlador controlador = new Controlador(juego, vista);
        


        controlador.juega();
        
    }
    
}