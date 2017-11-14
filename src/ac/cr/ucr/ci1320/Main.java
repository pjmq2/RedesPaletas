package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String args[])
    {
        Servidor servidor = new Servidor(pablo());
        servidor.iniciar();
    }


    public static Nodo pablo(){
        Map<String,TablaDirecciones> tablaD = new HashMap<>();
        TablaDirecciones tabla1 = new TablaDirecciones("Julian","12.0.0.8",0,3333);
        TablaDirecciones tabla2 = new TablaDirecciones("Sebastian","12.0.20.2",0,5555);
        TablaDirecciones tabla3 = new TablaDirecciones("Carrito","165.8.6.25",0,5555);
        TablaDirecciones tabla4 = new TablaDirecciones("Paletas","165.8.6.25",1,5555);
        TablaDirecciones tabla5 = new TablaDirecciones("Luces","165.8.6.25",2,5555);
        TablaDirecciones tabla6 = new TablaDirecciones("Legos","12.0.20.2",2,2222);
        TablaDirecciones tabla7 = new TablaDirecciones("Bolinchas","12.0.20.2",1,2222);

        tablaD.put("12.0.0.8",tabla1);
        tablaD.put("12.0.20.2",tabla2);
        tablaD.put("165.8.0.0",tabla3);
        tablaD.put("200.5.0.0",tabla4);
        tablaD.put("25.0.0.0",tabla5);
        tablaD.put("201.6.0.0",tabla6);
        tablaD.put("140.90.0.0",tabla7);

        InetAddress ipAddr;
        Nodo nodoPablo;

        try {
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            Map<String,String> tablaIP = new HashMap<>();
            Dispatcher dispatcher = new Dispatcher(IP);
            tablaIP=dispatcher.getTablaIP();
            nodoPablo = new Nodo(tablaD,IP,7777, tablaIP);
            return nodoPablo;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Nodo julian(){
        Map<String,TablaDirecciones> tablaD= new HashMap<>();

        TablaDirecciones tabla2 = new TablaDirecciones("CRR2","12.0.0.7",1,5555);
        TablaDirecciones tabla3 = new TablaDirecciones("CRR3","12.0.0.7",1,5555);
        TablaDirecciones tabla4 = new TablaDirecciones("TELO1","12.0.0.7",2,5555);
        TablaDirecciones tabla5 = new TablaDirecciones("LEOG2","12.0.20.2",3,5555);
        TablaDirecciones tabla6 = new TablaDirecciones("Bolinchas.Jorge","12.0.20.2",2,2222);
        TablaDirecciones tabla7 = new TablaDirecciones("P2","12.0.20.2",1,2222);

        tablaD.put("165.8.2.0",tabla2);
        tablaD.put("165.8.48.2",tabla3);
        tablaD.put("25.0.2.5",tabla4);
        tablaD.put("201.6.0.2",tabla5);
        tablaD.put("140.90.0.20",tabla6);
        tablaD.put("200.5.0.2",tabla7);

        InetAddress ipAddr;
        Nodo nodoJulian;

        try {
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            Map<String,String> tablaIP = new HashMap<>();
            Dispatcher dispatcher = new Dispatcher(IP);
            tablaIP=dispatcher.getTablaIP();
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

        InetAddress ipAddr;

        Nodo nodoSebastian;

        try {
            ipAddr = InetAddress.getLocalHost();
            String IP = ipAddr.getHostAddress();
            Map<String,String> tablaIP = new HashMap<>();
            Dispatcher dispatcher = new Dispatcher(IP);
            tablaIP=dispatcher.getTablaIP();
            nodoSebastian = new Nodo(tablaD,IP,7777, tablaIP);
            return nodoSebastian;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}


