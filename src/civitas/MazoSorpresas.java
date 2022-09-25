//////////////////////////////////////////
//Implementacion de la clase MazoSorpresas
//////////////////////////////////////////

package civitas;
import java.util.ArrayList;


public class MazoSorpresas {
    
    ArrayList<Sorpresa> sorpresas;
    
    boolean barajada;
    
    int usadas;
    
    boolean debug;
    
    //Metodo privado inicializador
    private void init (){
        sorpresas=new ArrayList<Sorpresa>(0);        
        barajada=false;
        usadas=0;
    }

    //Constructores
    MazoSorpresas (){
        this.init();
    }
    MazoSorpresas (boolean valor_debug){
        debug=valor_debug;
        this.init();
        if(debug==true)
            Diario.getInstance().ocurreEvento("Metodo debug activado");
        
    }
    void alMazo (Sorpresa s){
        if(barajada==false){              
            sorpresas.add(s);
        }
    }
    
    Sorpresa siguiente(){
        if(barajada==false || usadas==sorpresas.size() && debug==false){
            
            barajada=true;
            usadas=0;
        }
        
        usadas++;
        sorpresas.add(sorpresas.get(0));
        Sorpresa s=sorpresas.get(0);
        sorpresas.remove(0);
        
        return s;    
    }
}

}
