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
    
    int tirar (){
        int devuelve;
        random=new Random(6);
        if(debug==false)
            devuelve=1;
        else
            devuelve=(int)(random.nextDouble()*6+1);
        
        ultimoResultado=devuelve;
        
        return devuelve;
    }
    
    int quienEmpieza(int n){
        
        int empieza;
        empieza=(int)(random.nextDouble()*(n-1));

        
        return empieza;
    }
    
    void setDebug(boolean d){
        debug=d;
        
        if(debug==true)
            Diario.getInstance().ocurreEvento("Se ha puesto en modo aumenta 1 a 1");
        else 
            Diario.getInstance().ocurreEvento("Se ha puesto en modo aumenta mas de 1");
        
        }
    
    int getUltimoResultado(){
        
        return ultimoResultado;
    }
}

