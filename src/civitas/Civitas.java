/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package civitas;
import java.util.ArrayList;
/**
 *
 * @author carmenxufdz
 */
public class Civitas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> jugadores = new ArrayList<>();
        jugadores.add("A");
        jugadores.add("B");
        jugadores.add("C");
        jugadores.add("D");
          
        CivitasJuego juego = new CivitasJuego(jugadores, false);
        
    }
    
}