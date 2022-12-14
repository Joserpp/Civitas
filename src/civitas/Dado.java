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

    public int getUltimoResultado(){
        
        return ultimoResultado;
    }

    public boolean getDebug() {
        return debug;
    }
    
    public void setDebug(boolean dbg){
    
        debug = dbg;
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
}

