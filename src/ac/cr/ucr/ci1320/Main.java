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
        Dispatcher dispatcher = new Dispatcher();
        Map<String,TablaIp> tablaIP;
        tablaIP=dispatcher.getTablaIP();
        Nodo nodoPablo = new Nodo(tablaIP,"192.168.0.115");
        return nodoPablo;
    }
}


