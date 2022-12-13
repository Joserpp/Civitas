package civitas;

import java.util.Random;

public class Dado {
    
    Random random = new Random();
    
    int ultimoResultado;
    
    boolean debug;
    
    static final private Dado instance = new Dado();      //Unica instancia de la propia clase
    
    //Constructor privado

    private Dado (){
        debug = false;
        ultimoResultado = 0;
    }
    
    static public Dado getInstance(){
        
        return instance;
    }

    int getUltimoResultado(){
        
        return ultimoResultado;
    }
    
    int tirar()
    {
        if(debug==false) 
            ultimoResultado = random.nextInt(6) + 1;
        else ultimoResultado = 1;
        
        return ultimoResultado;
    }
    
    int quienEmpieza(int n){
        
        return random.nextInt(n);
    }
    
    void setDebug(boolean d){
        
        debug = d;
    }
}

