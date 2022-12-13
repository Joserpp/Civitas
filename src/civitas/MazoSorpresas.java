package civitas;

import java.util.ArrayList;
import java.util.Collections;

public class MazoSorpresas {
    
    ArrayList<Sorpresa> sorpresas;
    
    boolean barajada;
    
    int usadas;
    
    boolean debug;
    
    private void init (){

        sorpresas = new ArrayList<Sorpresa>(0);        
        barajada = false;
        usadas = 0;
    }

    MazoSorpresas (){

        init();
    }

    MazoSorpresas (boolean valor_debug){

        debug = valor_debug;

        init();

        if(debug == true)
            Diario.getInstance().ocurreEvento("Debug del mazo activado");
        
    }
    void alMazo (Sorpresa s){

        if(!barajada)             
            sorpresas.add(s);
    }
    
    Sorpresa siguiente(){

        if(!barajada || usadas == sorpresas.size() && !debug){

            Collections.shuffle(sorpresas);
            
            barajada = true;
            usadas = 0;
        }
        
        usadas++;

        Sorpresa sorpre = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(sorpre);

        return sorpre;
    }
}