package ac.cr.ucr.ci1320;

import java.io.IOException;
import java.util.HashMap;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String args[])
    {
        Nodo servidor = julian();
        servidor.iniciar();
    }


    public static Nodo pablo(){
        HashMap<String,TablaDirecciones> tablaD = new HashMap<>();
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
            HashMap<String,String> tablaIP;
            String fake1 = "12.0.0.7";
            String fake2 = "12.0.20.2";
            String fake3 = "12.0.0.8";
            tablaIP=getIPtable(fake1, "000.000.0.000", fake2, IP, fake3, "000.000.0.000");
            nodoPablo = new Nodo(tablaD,IP,7777, tablaIP, fake1, fake2, fake3, 9999);
            return nodoPablo;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Nodo julian(){
        HashMap<String,TablaDirecciones> tablaD= new HashMap<>();

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
            HashMap<String,String> tablaIP;
            String fake1 = "12.0.0.7";
            String fake2 = "12.0.20.2";
            String fake3 = "12.0.0.8";
            tablaIP=getIPtable(fake1, "000.000.0.000", fake2, IP, fake3, "000.000.0.000");
            nodoJulian = new Nodo(tablaD,IP,7777, tablaIP, fake1, fake2, fake3, 9999);
            return nodoJulian;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Nodo sebastian(){
        HashMap<String,TablaDirecciones> tablaD= new HashMap<>();
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
            HashMap<String,String> tablaIP;
            String fake1 = "12.0.0.7";
            String fake2 = "12.0.20.2";
            String fake3 = "12.0.0.8";
            tablaIP=getIPtable(fake1, "000.000.0.000", fake2, IP, fake3, "000.000.0.000");
            nodoSebastian = new Nodo(tablaD,IP,7777, tablaIP, fake1, fake2, fake3, 9999);
            return nodoSebastian;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, String> getIPtable(String fake1, String real1, String fake2, String real2, String fake3, String real3)
    {
        HashMap<String,String> tablonIP = new HashMap<>();
        tablonIP.put(fake1,real1); // P
        tablonIP.put(fake2,real2); // S
        tablonIP.put(fake3,real3); // J
        return tablonIP;
    }
}
