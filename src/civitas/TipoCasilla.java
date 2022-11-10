package civitas;

/**
 *
 * @author carmenxufdz
 */

// Este enumerado representa todos los tipos de casillas del juego. Las casillas 
// de descanso son aquellas en las que al llegar a ellas al jugador no le ocurre 
// nada ni se produce ningún evento asociado a esa llegada. La salida y las zonas 
// de aparcamiento se considerarán que forman parte de este conjunto de casillas.

enum TipoCasilla {
    CALLE, 
    SORPRESA, 
    DESCANSO;
}