package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[])
    {
        Client controlador;
        controlador = new Client();
        try{
            controlador.startClient("Hola Julian");
        }
        catch(Exception ex)
        {
            System.out.println("Message was not sent. \n");
        }
    }


    public static void pablo(){
        Map<String,TablaDirecciones> tablaD= new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,3333);
        TablaDirecciones tabla2 = new TablaDirecciones("CRR2","165.8.6.25",0,5555);
        TablaDirecciones tabla3 = new TablaDirecciones("CRR3","165.8.6.25",0,5555);
        TablaDirecciones tabla4 = new TablaDirecciones("TELO1","165.8.6.25",1,5555);
        TablaDirecciones tabla5 = new TablaDirecciones("LEOG2","165.8.6.25",2,5555);
        TablaDirecciones tabla6 = new TablaDirecciones("Bolinchas.Jorge","12.0.20.2",2,2222);
        TablaDirecciones tabla7 = new TablaDirecciones("P2","12.0.20.2",1,2222);
        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("165.8.2.0",tabla2);
        tablaD.put("165.8.48.2",tabla3);
        tablaD.put("25.0.2.5",tabla4);
        tablaD.put("201.6.0.2",tabla5);
        tablaD.put("140.90.0.20",tabla6);
        tablaD.put("200.5.0.2",tabla7);
        Nodo nodoPablo = new Nodo(tablaD,"",7777);
    }

}


