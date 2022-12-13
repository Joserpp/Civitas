package civitas;

public class GestionInmobiliaria {
    
    private int propiedad;
    private OperacionInmobiliaria operacion;


    GestionInmobiliaria(OperacionInmobiliaria operacion, int propiedad){

        this.propiedad = propiedad;
        this.operacion = operacion;
    }
    

    public OperacionInmobiliaria getOperacion(){

        return operacion;
    }

    public int getPropiedad(){
        
        return propiedad;
    }

}
