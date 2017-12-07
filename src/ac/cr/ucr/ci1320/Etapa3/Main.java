package ac.cr.ucr.ci1320;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[])
    {
        Servidor servidor = new Servidor(pablo());
        servidor.iniciar();
    }


    public static Nodo pablo(){
        Nodo nodoPablo = new Nodo("192.168.0.115");
        return nodoPablo;
    }
}


