package civitas;

public class Diario {
    //Es un singleton. La propia clase almacena la referencia a la única instancia
    static final private Diario instance = new Diario();

    //Constructor privado para evitar que se puedan crear más instancias
    private Diario () {
    eventos = new ArrayList<>();
    }
    //Método de clase para obtener la instancia
    static public Diario getInstance() {
    return instance;
    }
    // el resto de métodos de la clase Diario
}
