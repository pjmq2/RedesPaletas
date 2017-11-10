package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String args[])
    {
        Nodo node = julian();
        node.iniciar();
    }


    public static Nodo pablo(){
        Map<String,TablaDirecciones> tablaD= new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,3333);
        TablaDirecciones tabla2 = new TablaDirecciones("CRR2","165.8.6.25",0,5555);
        TablaDirecciones tabla3 = new TablaDirecciones("CRR3","165.8.6.25",0,5555);
        TablaDirecciones tabla4 = new TablaDirecciones("TELO1","165.8.6.25",1,5555);
        TablaDirecciones tabla5 = new TablaDirecciones("LEOG2","165.8.6.25",2,5555);
        TablaDirecciones tabla6 = new TablaDirecciones("Bolinchas.Jorge","12.0.20.2",2,7777);
        TablaDirecciones tabla7 = new TablaDirecciones("P2","12.0.20.2",1,7777);
        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("165.8.2.0",tabla2);
        tablaD.put("165.8.48.2",tabla3);
        tablaD.put("25.0.2.5",tabla4);
        tablaD.put("201.6.0.2",tabla5);
        tablaD.put("140.90.0.20",tabla6);
        tablaD.put("200.5.0.2",tabla7);

        Map<String,String> tablaIP = new HashMap<>();
        tablaIP.put("12.0.0.7","192.168.0.161"); // P
        tablaIP.put("12.0.20.2","10.1.130.211"); // S
        tablaIP.put("12.0.0.8","10.1.130.84"); // J

        Nodo nodoPablo = new Nodo(tablaD,"192.168.0.121",7777,tablaIP);
        return nodoPablo;
    }

    public static Nodo julian(){
        Map<String,TablaDirecciones> tablaD= new HashMap<>();

        TablaDirecciones tabla2 = new TablaDirecciones("CRR2","12.0.0.7",1,5555); // P
        TablaDirecciones tabla3 = new TablaDirecciones("CRR3","12.0.0.7",1,5555); // P
        TablaDirecciones tabla4 = new TablaDirecciones("TELO1","12.0.0.7",2,5555); // P
        TablaDirecciones tabla5 = new TablaDirecciones("LEOG2","12.0.20.2",3,5555); // S
        TablaDirecciones tabla6 = new TablaDirecciones("Bolinchas.Jorge","12.0.20.2",2,7777); // S
        TablaDirecciones tabla7 = new TablaDirecciones("P2","12.0.20.2",1,7777); // S

        tablaD.put("165.8.2.0",tabla2); // P
        tablaD.put("165.8.48.2",tabla3); // P
        tablaD.put("25.0.2.5",tabla4); // P
        tablaD.put("201.6.0.2",tabla5); // S
        tablaD.put("140.90.0.20",tabla6); // S
        tablaD.put("200.5.0.2",tabla7); // S

        Map<String,String> tablaIP = new HashMap<>();
        tablaIP.put("12.0.0.7","192.168.0.161"); // P
        tablaIP.put("12.0.20.2","10.1.130.211"); // S
        tablaIP.put("12.0.0.8","10.1.130.84"); // J

        InetAddress ipAddr;

        Nodo nodoJulian;

        try {
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            nodoJulian = new Nodo(tablaD,IP,7777, tablaIP);
            return nodoJulian;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Nodo sebastian(){
        Map<String,TablaDirecciones> tablaD= new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,3333);
        TablaDirecciones tabla2 = new TablaDirecciones("CRR2","12.0.0.7",1,5555);
        TablaDirecciones tabla3 = new TablaDirecciones("CRR3","12.0.0.7",1,5555);
        TablaDirecciones tabla4 = new TablaDirecciones("TELO1","12.0.0.7",2,5555);
        TablaDirecciones tabla5 = new TablaDirecciones("LEOG2","200.5.0.1",2,5555);
        TablaDirecciones tabla6 = new TablaDirecciones("Bolinchas.Jorge","200.5.0.1",1,2222);
        TablaDirecciones tabla7 = new TablaDirecciones("P2","200.5.0.1",0,2222);
        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("165.8.2.0",tabla2);
        tablaD.put("165.8.48.2",tabla3);
        tablaD.put("25.0.2.5",tabla4);
        tablaD.put("201.6.0.2",tabla5);
        tablaD.put("140.90.0.20",tabla6);
        tablaD.put("200.5.0.2",tabla7);

        Map<String,String> tablaIP = new HashMap<>();
        tablaIP.put("12.0.0.7","192.168.0.161"); // P
        tablaIP.put("12.0.20.2","192.168.0.166"); // S
        tablaIP.put("12.0.0.8","192.168.0.136"); // J

        Nodo nodoSebastian = new Nodo(tablaD,"192.168.0.121",7777,tablaIP);

        return nodoSebastian;
    }
}