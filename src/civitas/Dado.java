///////////////////////////////////////////////////
//Implementacion de la clase Dado
///////////////////////////////////////////////////

package civitas;
import java.util.Random;

//import javax.lang.model.util.ElementScanner6;


public class Dado {
    
    Random random=new Random();
    
    int ultimoResultado;
    
    boolean debug;
    
    static final private Dado instance=new Dado();      //Unica instancia de la propia clase
    
    //Constructor privado

    private Dado (){
        debug=false;
        ultimoResultado=0;
    }
    
    static public Dado getInstance(){
        
        return instance;
    }
    
    int tirar()
    {
        if(debug==false) 
            ultimoResultado = random.nextInt(6) + 1;
        else ultimoResultado = 1;
        
        return ultimoResultado;
    }
    
    int quienEmpieza(int n){
        
        int empieza;
        empieza=(int)(random.nextInt(n) );

        
        return empieza;
    }
    
    void setDebug(boolean d){
        debug=d;
        
        if(debug==true)
            Diario.getInstance().ocurreEvento("Se ha activado el modo debug del dado");
        else 
            Diario.getInstance().ocurreEvento("Se ha desactivado el modo debug del dado");
        
        }
    
    int getUltimoResultado(){
        
        return ultimoResultado;
    }
}

