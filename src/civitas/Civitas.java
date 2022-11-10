package civitas;

public class Civitas {

    public static void main(String[] args) {
        Casilla casilla1 = new Casilla("casillanueva", 1000, 1000, 1000);
        
        System.out.println(casilla1.toString());
        System.out.println(casilla1.getNombre());
    } 

}
